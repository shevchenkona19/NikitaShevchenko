package sheva.economicprovider;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.entities.NBCurrency;
import sheva.economicprovider.mvp.ui.activities.MainActivity;


public class NotifyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        List<NBCurrency> list;
        if (intent != null) {
            list = intent.getBundleExtra("CURRENCY").getParcelableArrayList("CURRENCY");
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.InboxStyle inboxStyle =
                    new NotificationCompat.InboxStyle();
            inboxStyle.setBigContentTitle("Exchange rates: ");
            for (int i = 0; i < 10; i++) {
                inboxStyle.addLine(list.get(i).getCc() + ": " + String.valueOf(list.get(i).getRate()));
            }
            android.support.v4.app.NotificationCompat.Builder builder =
                    new android.support.v4.app.NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.cash_multiple)
                            .setContentTitle("Exchange rate from National Bank")
                            .setContentText("pull to expand.")
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setVibrate(new long[]{200, 300, 200, 300, 200})
                            .setWhen(System.currentTimeMillis())
                            .setStyle(inboxStyle);
            Intent notifyIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0);
            builder.setContentIntent(pendingIntent);

            manager.notify(101, builder.build());
        } else {
            List<NBCurrency> finalList = new ArrayList<>();
            DataManager manager = new DataManager();
            manager.getNBCurrencyList()
                    .subscribe(new Subscriber<List<NBCurrency>>() {
                        @Override
                        public void onCompleted() {
                            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            NotificationCompat.InboxStyle inboxStyle =
                                    new NotificationCompat.InboxStyle();
                            inboxStyle.setBigContentTitle("Exchange rates: ");
                            for (int i = 0; i < 10; i++) {
                                inboxStyle.addLine(finalList.get(i).getCc() + ": " + String.valueOf(finalList.get(i).getRate()));
                            }
                            android.support.v4.app.NotificationCompat.Builder builder =
                                    new android.support.v4.app.NotificationCompat.Builder(NotifyService.this)
                                            .setSmallIcon(R.drawable.cash_multiple)
                                            .setContentTitle("Exchange rate from National Bank")
                                            .setContentText("pull to expand.")
                                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                            .setVibrate(new long[]{200, 300, 200, 300, 200})
                                            .setWhen(System.currentTimeMillis())
                                            .setStyle(inboxStyle);
                            Intent notifyIntent = new Intent(NotifyService.this, MainActivity.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(NotifyService.this, 0, notifyIntent, 0);
                            builder.setContentIntent(pendingIntent);
                            manager.notify(101, builder.build());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(NotifyService.class.getSimpleName(), e.getMessage());
                        }

                        @Override
                        public void onNext(List<NBCurrency> list) {
                            list.addAll(finalList);
                        }
                    });
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
