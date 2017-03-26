package sheva.economicprovider.mvp.ui.activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.ui.interfaces.INewsItemActivityView;

public class NewsItemActivity extends AppCompatActivity implements INewsItemActivityView {
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
        Article article = (Article) getIntent().getBundleExtra("BUNDLE").get("OBJECT");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivTarget.setTransitionName("animation1");
        }
        Picasso.with(this)
                .load(article.getUrlToImage())
                .resize(450, 201)
                .centerCrop()
                .into(ivTarget);
        tvNewsText.setText(article.getDescription());
        tvNewsTitle.setText(article.getTitle());
    }
}
