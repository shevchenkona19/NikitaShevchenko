package sheva.newsprovider.mvp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.presenters.fragments.NewsFragmentPresenter;
import sheva.newsprovider.mvp.ui.activities.NewsItem;
import sheva.newsprovider.mvp.ui.adapters.NewsFragmentAdapter;
import sheva.newsprovider.mvp.ui.base.INewsFragmentView;
import sheva.newsprovider.utils.ItemClickSupport;

public class NewsFragment extends MvpAppCompatFragment implements INewsFragmentView, NewsFragmentAdapter.IAdapterUtils {
    @BindView(R.id.rvNewsList)
    RecyclerView rvList;
    @BindView(R.id.srlNewsRefresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.cbNewsSort)
    CheckBox cbSort;
    private NewsFragmentAdapter adapter;
    private boolean sortByTime;
    private static String source;
    private ItemClickSupport support;
    @InjectPresenter
    NewsFragmentPresenter presenter;
    private Unbinder unbinder;

    public NewsFragment() {
    }

    public static NewsFragment newInstance(String source1) {
        source = source1;
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, v);
        cbSort.setOnCheckedChangeListener((compoundButton, b) -> {
            srlRefresh.setRefreshing(true);
            sortByTime = b;
            presenter.updateList(source, sortByTime);
        });
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.newsColor),
                getResources().getColor(R.color.colorRed),
                getResources().getColor(R.color.colorPrimary));
        srlRefresh.setOnRefreshListener(() -> {
            srlRefresh.setRefreshing(true);
            presenter.updateList(source, sortByTime);
        });
        adapter = new NewsFragmentAdapter(v.getContext(), this);
        rvList.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rvList.setAdapter(adapter);
        support = ItemClickSupport.addTo(rvList);
        support.setOnItemClickListener((recyclerView, position, v1) -> {
                Intent intent = new Intent(getActivity(), NewsItem.class);
                intent.putExtra("OBJECT", adapter.getList().get(position));
                startActivity(intent);
        });
        presenter.updateList(source, sortByTime);
        return v;
    }

    @Override
    public void onDestroyView() {
        ItemClickSupport.removeFrom(rvList);
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void updateList(List<Article> list) {
        adapter.updateList(list);
    }

    @Override
    public void hideRefresh() {
        srlRefresh.setRefreshing(false);
    }

    @Override
    public void showWarning(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    //IAdapterUtils
    @Override
    public void addToFavorite(Article article) {
        presenter.addToFavorite(article);
    }

    @Override
    public void deleteLast() {
        presenter.deleteLast();
    }
}
