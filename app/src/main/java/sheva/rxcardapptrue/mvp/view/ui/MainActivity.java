package sheva.rxcardapptrue.mvp.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.rxcardapptrue.App;
import sheva.rxcardapptrue.R;
import sheva.rxcardapptrue.adapter.Adapter;
import sheva.rxcardapptrue.mvp.model.entities.NewsEntity;
import sheva.rxcardapptrue.mvp.presenter.MainActivityPresenter;
import sheva.rxcardapptrue.mvp.view.interfaces.IMainActivityView;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    @Inject
    MainActivityPresenter presenter;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);
        presenter.bind(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        adapter = new Adapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
        presenter.updateList();

    }

    @Override
    public void updateList(List<NewsEntity> list) {
        adapter.updateList(list);
    }
}
