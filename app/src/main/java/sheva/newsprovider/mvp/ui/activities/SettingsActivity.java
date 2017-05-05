package sheva.newsprovider.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Switch;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.presenters.activities.SettingsActivityPresenter;
import sheva.newsprovider.mvp.ui.base.ISettingsActivityView;

public class SettingsActivity extends MvpAppCompatActivity implements ISettingsActivityView {
    @BindView(R.id.tbSettingsToolbar)
    Toolbar tbSettings;
    @BindView(R.id.swSettingsNotify)
    Switch swNotify;
    @BindView(R.id.swPhoneBrowser)
    Switch swBrowser;
    @BindView(R.id.btnSettingsChangeInt)
    Button btnChangeInt;
    @BindView(R.id.btnSettingsExit)
    Button btnExit;
    @BindView(R.id.swSettingsFarenheit)
    Switch swFarenheit;
    @InjectPresenter
    SettingsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(tbSettings);
        presenter.setSwitchBrowser();
        presenter.setSwitchFahrenheit();
        presenter.setSwitchNotify();
        btnExit.setOnClickListener(view -> presenter.exit());
        btnChangeInt.setOnClickListener(view -> presenter.changeInterests());
        swBrowser.setOnCheckedChangeListener((compoundButton, b) -> presenter.usePhoneBrowser(b));

        swFarenheit.setOnCheckedChangeListener((compoundButton, b) -> presenter.useFahrenheit(b));

        swNotify.setOnCheckedChangeListener((compoundButton, b) -> presenter.useNotify(b));
    }

    @Override
    public void exit() {
         Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void changeInterests() {
        Intent intent = new Intent(getApplicationContext(), InterestsActivity.class);
        intent.putExtra("CHANGE", true);
        startActivity(intent);
    }

    @Override
    public void setSwitchBrowser(boolean b) {
        swBrowser.setChecked(b);
    }

    @Override
    public void setSwitchFarenheit(boolean b) {
        swFarenheit.setChecked(b);
    }

    @Override
    public void setSwitchNotifications(boolean b) {
        swNotify.setChecked(b);
    }
}
