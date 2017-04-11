package sheva.newsprovider.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.presenters.activities.SettingsActivityPresenter;
import sheva.newsprovider.mvp.ui.base.ISettingsActivityView;

public class SettingsActivity extends MvpAppCompatActivity implements ISettingsActivityView{
    @BindView(R.id.tbSettingsToolbar)
    Toolbar tbSettings;
    @BindView(R.id.swSettingsNotify)
    Switch swNotify;
    @BindView(R.id.btnSettingsChangeInt)
    Button btnChangeInt;
    @BindView(R.id.btnSettingsExit)
    Button btnExit;
    @InjectPresenter
    SettingsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(tbSettings);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.exit();
            }
        });
    }

    @Override
    public void exit() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
