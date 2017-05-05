package sheva.newsprovider.mvp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.presenters.activities.NewsItemActivityPresenter;
import sheva.newsprovider.mvp.ui.base.INewsItemActivityView;

public class NewsItem extends MvpAppCompatActivity implements INewsItemActivityView{
    @BindView(R.id.tbNewsItemToolbar)
    Toolbar tbToolbar;
    @BindView(R.id.wvNewsItemWeb)
    WebView wvWeb;
    private Article article;
    @InjectPresenter
    NewsItemActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        ButterKnife.bind(this);
        setSupportActionBar(tbToolbar);
        article = getIntent().getParcelableExtra("OBJECT");
        getSupportActionBar().setTitle(article.getTitle().substring(0, 15));
        presenter.showNews();
    }

    @Override
    public void showWebView() {
        wvWeb.loadUrl(article.getUrl());
        wvWeb.computeScroll();
        wvWeb.invokeZoomPicker();
    }

    @Override
    public void showBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        startActivity(browserIntent);
        finish();
    }
}
