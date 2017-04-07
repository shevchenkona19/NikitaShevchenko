package sheva.newsprovider.mvp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;

public class NewsItem extends MvpAppCompatActivity {
    @BindView(R.id.tbNewsItemToolbar)
    Toolbar tbToolbar;
    @BindView(R.id.ivNewsItemHeaderIMG)
    ImageView ivHeader;
    @BindView(R.id.tvNewsItemHeadline)
    TextView tvHeadline;
    @BindView(R.id.wvNewsItemWeb)
    WebView wvWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        ButterKnife.bind(this);
        setSupportActionBar(tbToolbar);
    }
}
