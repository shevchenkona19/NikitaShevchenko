package sheva.economicprovider.mvp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.presenter.NewsActivityPresenter;
import sheva.economicprovider.mvp.ui.adapters.NewsItemAdapter;
import sheva.economicprovider.mvp.ui.interfaces.INewsActivityView;

public class NewsActivity extends AppCompatActivity implements INewsActivityView {
    @BindView(R.id.btnRefresh)
    Button btnRefresh;
    @BindView(R.id.rvNewsList)
    RecyclerView rvList;
    private NewsItemAdapter adapter;
    private NewsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        presenter = new NewsActivityPresenter();
        presenter.bind(this);
        adapter = new NewsItemAdapter(this);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        btnRefresh.setOnClickListener(view -> presenter.updateList());
    }

    @Override
    public void updateList(List<Article> list) {
        adapter.addList(list);
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }
}
