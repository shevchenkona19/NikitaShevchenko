package sheva.newsprovider.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.model.entities.WeatherEntity;
import sheva.newsprovider.mvp.presenters.fragments.FeedFragmentPresenter;
import sheva.newsprovider.mvp.ui.adapters.FeedFragmentAdapter;
import sheva.newsprovider.mvp.ui.base.IFeedFragmentView;

/**
 * Created by shevc on 06.04.2017.
 * d
 */

public class FeedFragment extends MvpAppCompatFragment implements IFeedFragmentView, FeedFragmentAdapter.IUpdateList {
    @BindView(R.id.rvFeedList)
    RecyclerView rvList;
    @BindView(R.id.srlFeedRefresh)
    SwipeRefreshLayout srlRefresh;
    private FeedFragmentAdapter adapter;
    @InjectPresenter
    FeedFragmentPresenter presenter;
    private static FeedFragmentAdapter.ILaunchFragment launch;
    private Unbinder unbinder;

    public FeedFragment() {
    }

    public static FeedFragment newInstance(FeedFragmentAdapter.ILaunchFragment launch1) {
        launch = launch1;
        return new FeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        unbinder = ButterKnife.bind(this, v);
        adapter = new FeedFragmentAdapter(getContext(), launch, this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.setUserInterestsForAdapter();
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.newsColor),
                getResources().getColor(R.color.colorRed),
                getResources().getColor(R.color.colorPrimary));
        srlRefresh.setOnRefreshListener(() -> {
            srlRefresh.setRefreshing(true);
            presenter.updateList();
        });
        srlRefresh.setRefreshing(true);
        presenter.updateList();
        return v;
    }

    @Override
    public void getArticlesForInterest(List<Interest> interests) {
        presenter.getArticle(interests);
    }

    @Override
    public void getWeather() {
        presenter.getWeather();
    }

    @Override
    public void hideRefresh() {
        srlRefresh.setRefreshing(false);
    }

    @Override
    public void addToFavorite(Article article) {
        presenter.addToFavorite(article);
    }

    @Override
    public void deleteLastFromDB() {
        presenter.deleteLastFromDb();
    }

    @Override
    public void receiveArticles(List<List<Article>> articleList) {
        adapter.updateListWithArticles(articleList);
    }

    @Override
    public void receiveWeather(List<WeatherEntity> weatherList) {
        adapter.updateListWithWeather(weatherList);
    }

    @Override
    public void setUserInterests(List<Interest> interestList) {
        adapter.setInterestList(interestList);
    }

    @Override
    public void updateList() {
        adapter.updateAllList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
