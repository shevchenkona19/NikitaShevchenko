package sheva.newsprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import javax.inject.Inject;

import sheva.newsprovider.mvp.model.datamanager.DataManager;


public class Receiver extends BroadcastReceiver {
    @Inject
    DataManager manager;

    public Receiver() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (manager.isNotify()) {
            final ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            final android.net.NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifi.isAvailable()) {
                context.startService(new Intent(context, NotifyService.class));
            }
        }
    }
}
