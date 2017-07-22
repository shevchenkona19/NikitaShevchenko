package sheva.filmfinder.mvp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sheva.filmfinder.IConstants;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.kinoload.Result;
import sheva.filmfinder.mvp.model.entities.kinoload.Session;
import sheva.filmfinder.mvp.model.entities.sessions.HallTimeEntity;
import sheva.filmfinder.mvp.model.entities.sessions.SessionEntity;
import sheva.filmfinder.mvp.ui.adapters.SessionsAdapter;
import sheva.filmfinder.utils.ItemClickSupport;
import sheva.filmfinder.utils.StringUtils;

public class GoingFilmActivity extends MvpAppCompatActivity {
    @BindView(R.id.ivGoingFilmPoster)
    ImageView ivPoster;
    @BindView(R.id.tvGoingFilmIMDbScores)
    TextView tvIMDbScores;
    @BindView(R.id.tvGoingFilmPremier)
    TextView tvPremier;
    @BindView(R.id.tvGoingFilmDirector)
    TextView tvDirector;
    @BindView(R.id.tvGoingFilmCountries)
    TextView tvCountries;
    @BindView(R.id.tvGoingFilmActors)
    TextView tvActors;
    @BindView(R.id.rvGoingFilmSessionsList)
    RecyclerView rvSessions;
    @BindView(R.id.supSessionsLayout)
    SlidingUpPanelLayout supSessions;
    @BindView(R.id.tvGoingFilmName)
    TextView tvFilmName;
    @BindView(R.id.tbGoingFilmToolbar)
    Toolbar toolbar;
    @BindView(R.id.tbSlidingToolbar)
    Toolbar slidingToolbar;
    private Result entity;
    private SessionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_going_film);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        entity = getIntent().getParcelableExtra(IConstants.keys.RESULT_ENTITY);
        ivPoster.setTransitionName(IConstants.keys.ANIMATION_GOING_FILM);
        Picasso.with(this)
                .load(IConstants.BASE_URL + entity.getImage().replaceFirst("sm", "bp"))
                .into(ivPoster);
        tvFilmName.setText(entity.getName());
        tvIMDbScores.setText(entity.getImdb());
        tvCountries.setText(entity.getCountries());
        tvPremier.setText(entity.getPremierUa());
        tvActors.setText(StringUtils.getActors(entity.getActors()));
        tvDirector.setText(StringUtils.getDirector(entity.getRejisser()));
        adapter = new SessionsAdapter(this, rvSessions);
        rvSessions.setLayoutManager(new LinearLayoutManager(this));
        rvSessions.setAdapter(adapter);
        final List<SessionEntity> list = new ArrayList<>();
        final SessionEntity[] entity1 = new SessionEntity[2];
        Observable.from(entity.getSessions())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Session, SessionEntity>() {
                    @Override
                    public SessionEntity call(Session session) {
                        SessionEntity sessionEntity = entity1[1];
                        if (session.getKName() !=null) {
                             sessionEntity = new SessionEntity(session.getKName(),
                                    session.getKBron(),
                                    new ArrayList<HallTimeEntity>(),
                                    false);
                            sessionEntity.getList().add(new HallTimeEntity(session.getHName(), session.getSessions(), false));
                            entity1[0] = entity1[1];
                            entity1[1] = sessionEntity;
                            return entity1[0];
                        }
                        sessionEntity.getList().add(new HallTimeEntity(session.getHName(), session.getSessions(), false));
                        entity1[1] = sessionEntity;
                        return null;
                    }
                }).subscribe(new Subscriber<SessionEntity>() {
            @Override
            public void onCompleted() {
                adapter.updateList(list);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(SessionEntity entity) {
                if (entity!=null) {
                    list.add(entity);
                }
            }
        });
        supSessions.setDragView(slidingToolbar);
    }
}
