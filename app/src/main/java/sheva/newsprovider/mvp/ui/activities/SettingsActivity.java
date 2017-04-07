package sheva.newsprovider.mvp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Switch;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;

public class SettingsActivity extends MvpAppCompatActivity {
    @BindView(R.id.tbSettingsToolbar)
    Toolbar tbSettings;
    @BindView(R.id.swSettingsNotify)
    Switch swNotify;
    @BindView(R.id.btnSettingsChangeInt)
    Button btnChangeInt;
    @BindView(R.id.btnSettingsExit)
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(tbSettings);
    }
}
