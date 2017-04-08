package sheva.economicprovider.mvp.ui.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.Utils.ItemClickSupport;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.presenter.NewsActivityPresenter;
import sheva.economicprovider.mvp.ui.adapters.NewsItemAdapter;
import sheva.economicprovider.mvp.ui.interfaces.INewsActivityView;

public class NewsActivity extends MvpAppCompatActivity implements INewsActivityView {
    @BindView(R.id.btnRefresh)
    ImageButton btnRefresh;
    @BindView(R.id.rvNewsList)
    RecyclerView rvList;
    @BindView(R.id.tbNewsListToolbar)
    Toolbar toolbar;
    @BindView(R.id.swNewsSearch)
    SearchView swNews;
    @BindView(R.id.srlNewsRefresh)
    SwipeRefreshLayout srlRefresh;
    private NewsItemAdapter adapter;
    @InjectPresenter
    NewsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            srlRefresh.setColorSchemeColors(getColor(R.color.colorAccent));
        } else {
            srlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        }
        setSupportActionBar(toolbar);
        adapter = new NewsItemAdapter(this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        btnRefresh.setOnClickListener(view -> presenter.updateList());
        ItemClickSupport.addTo(rvList).setOnItemClickListener((recyclerView, position, v) ->
            presenter.startActivity(position, v));
        swNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        srlRefresh.setOnRefreshListener(() -> {
            srlRefresh.setRefreshing(true);
            presenter.getNews();
        });
    }

    @Override
    public void updateList(List<Article> list) {
        Log.d("MY", "update List in NewsActivity");
        adapter.addList(list);
    }

    @Override
    public void startItemActivity(int position, View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions transitionActivityOptions =
                    ActivityOptions.makeSceneTransitionAnimation(NewsActivity.this,
                            v.findViewById(R.id.ivFirstImage),
                    "animation1");
            Intent intent = new Intent(NewsActivity.this, NewsItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("OBJECT", adapter.getItemAtPosition(position));
            intent.putExtra("BUNDLE", bundle);
            startActivity(intent, transitionActivityOptions.toBundle());
        }
    }

    @Override
    public void hideRefresh() {
        srlRefresh.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        ItemClickSupport.removeFrom(rvList);
        super.onDestroy();
    }
}