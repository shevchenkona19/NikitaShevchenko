package sheva.newsprovider.mvp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;

public class InterestsActivity extends MvpAppCompatActivity {
    @BindView(R.id.tbInterestsToolbar)
    Toolbar tbInterestsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        ButterKnife.bind(this);
        setSupportActionBar(tbInterestsToolbar);
    }
}
