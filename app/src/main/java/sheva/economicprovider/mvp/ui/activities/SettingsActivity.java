package sheva.economicprovider.mvp.ui.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.App;
import sheva.economicprovider.R;
import sheva.economicprovider.NotifyService;
import sheva.economicprovider.mvp.model.entities.NBCurrency;
import sheva.economicprovider.mvp.presenter.SettingsActivityPresenter;
import sheva.economicprovider.mvp.ui.interfaces.ISettingsActivityView;

public class SettingsActivity extends AppCompatActivity implements ISettingsActivityView{
    @BindView(R.id.swNotification)
    Switch swNotification;
    private SettingsActivityPresenter presenter;
    private boolean state;
    private AlarmManager manager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        presenter = new SettingsActivityPresenter();
        presenter.bind(this);
        presenter.getNotify();
        manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        swNotification.setOnCheckedChangeListener((compoundButton, b) -> {
            state = b;
            presenter.setNotify();
            if (b){
                presenter.prepareNotification();
            } else {
                manager.cancel(pendingIntent);
            }
        });
        presenter.prepareNotification();

    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public void changeSwitchState(boolean b) {
        swNotification.setChecked(b);
    }

    @Override
    public boolean getSwitchState() {
        return state;
    }

    @Override
    public void prepareNotify(List<NBCurrency> list) {
        Bundle b = new Bundle();
        Intent intent = new Intent(SettingsActivity.this, NotifyService.class);
        b.putParcelableArrayList("CURRENCY", new ArrayList<>(list));
        intent.putExtra("CURRENCY", b);
        pendingIntent = PendingIntent.getService(App.get(), 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        startService(intent);
    }
}
