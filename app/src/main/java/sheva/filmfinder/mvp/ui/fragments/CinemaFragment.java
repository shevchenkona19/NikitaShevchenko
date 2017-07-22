package sheva.filmfinder.mvp.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sheva.filmfinder.IConstants;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.cinemas.Unmain;
import sheva.filmfinder.mvp.presenter.fragments.CinemaFragmentPresenter;
import sheva.filmfinder.mvp.ui.adapters.CinemaAdapter;
import sheva.filmfinder.mvp.ui.interfaces.ICinemaFragmentView;
import sheva.filmfinder.utils.ItemClickSupport;

public class CinemaFragment extends MvpAppCompatFragment implements ICinemaFragmentView{
    @BindView(R.id.ibCinemaCardView)
    ImageButton ibCardView;
    @BindView(R.id.ibCinemaListView)
    ImageButton ibGridView;
    @BindView(R.id.pbCinemaLoading)
    ProgressBar pbLoading;
    @BindView(R.id.rvCinemaList)
    RecyclerView rvCinemaList;
    private CinemaAdapter adapter;
    private Unbinder unbinder;
    @InjectPresenter
    CinemaFragmentPresenter presenter;
    private ItemClickSupport clickSupport;
    private ITransitionListener transitionListener;

    public interface ITransitionListener {
        void transite(CardView cardView, Unmain entity);
    }


    public static CinemaFragment newInstance() {
        return new CinemaFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ITransitionListener) {
            transitionListener = (ITransitionListener) context;
        } else {
            throw new IllegalStateException("must implement " +
                    ITransitionListener.class.getSimpleName());
        }
    }

    public CinemaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cinemas, container, false);
        unbinder = ButterKnife.bind(this, v);
        adapter = new CinemaAdapter(this.getContext());
        rvCinemaList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvCinemaList.setAdapter(adapter);
        presenter.updateList();
        ibCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setShowType(IConstants.viewType.CARDS);
                rvCinemaList.setLayoutManager(new LinearLayoutManager(CinemaFragment.this.getContext()));
                rvCinemaList.setAdapter(adapter);
            }
        });
        ibGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setShowType(IConstants.viewType.GRID);
                rvCinemaList.setLayoutManager(new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL));
                rvCinemaList.setAdapter(adapter);
            }
        });
        clickSupport = ItemClickSupport.addTo(rvCinemaList);
        clickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                CardView ivPoster = null;
                switch (adapter.getShowType()){
                    case IConstants.viewType.CARDS:
                        ivPoster = (CardView) v.findViewById(R.id.cvCinemaCard);
                        break;
                    case IConstants.viewType.GRID:
                        ivPoster = (CardView) v.findViewById(R.id.cvCinemaGrid);
                        break;
                }
                transitionListener.transite(ivPoster, adapter.getItemAtPosition(position));
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        ItemClickSupport.removeFrom(rvCinemaList);
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
        rvCinemaList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListUpdate(List<Unmain> list) {
        adapter.onUpdate(list);
    }
}
