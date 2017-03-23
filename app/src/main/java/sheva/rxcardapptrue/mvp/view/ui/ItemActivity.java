package sheva.rxcardapptrue.mvp.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.rxcardapptrue.App;
import sheva.rxcardapptrue.R;
import sheva.rxcardapptrue.mvp.presenter.ItemActivityPresenter;
import sheva.rxcardapptrue.mvp.view.interfaces.IItemActivityView;

/**
 * Created by shevc on 22.03.2017.
 */

public class ItemActivity extends AppCompatActivity implements IItemActivityView{
    @BindView(R.id.tvTextItem)
    TextView tvTextItem;
    @BindView(R.id.tvTitleItem)
    TextView tvTitleItem;
    @BindView(R.id.ivTarget)
    ImageView ivTarget;
    @BindView(R.id.tbItem)
    Toolbar toolbar;
    @Inject
    ItemActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar );
        App.getComponent().inject(this);
        presenter.bind(this);
        presenter.receiveTransition();
    }

    @Override
    public void receiveTransition() {

    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }
}
