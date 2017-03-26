package sheva.economicprovider.mvp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.ui.interfaces.INewsActivityView;

public class NewsActivity extends AppCompatActivity implements INewsActivityView{
    @BindView(R.id.btnRefresh)
    Button btnRefresh;
    @BindView(R.id.rvNewsList)
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
    }
}
