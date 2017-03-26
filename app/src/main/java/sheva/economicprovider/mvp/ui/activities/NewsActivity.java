package sheva.economicprovider.mvp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sheva.economicprovider.R;
import sheva.economicprovider.mvp.ui.interfaces.INewsActivityView;

public class NewsActivity extends AppCompatActivity implements INewsActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }
}
