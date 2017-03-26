package sheva.economicprovider.mvp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.ui.interfaces.INewsItemActivityView;

public class NewsItemActivity extends AppCompatActivity implements INewsItemActivityView{
    @BindView(R.id.tbItem)
    Toolbar toolbar;
    @BindView(R.id.tvNewsText)
    TextView tvNewsText;
    @BindView(R.id.tvNewsTitle)
    TextView tvNewsTitle;
    @BindView(R.id.ivTarget)
    ImageView ivTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }
}
