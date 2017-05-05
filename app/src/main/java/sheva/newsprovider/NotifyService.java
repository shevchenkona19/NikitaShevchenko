package sheva.newsprovider;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.ui.activities.LoginActivity;

public class NotifyService extends Service {
    @Inject
    DataManager manager;

    @Override
    public void onCreate() {
        App.get().getAppComponent().inject(this);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Article[] article1 = {null};
        manager.getNewsObservable(IConstants.Sources.politics[0], "top")
                .take(1)
                .subscribe(new Subscriber<Article>() {
                    @Override
                    public void onCompleted() {
                        showNews(article1[0]);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MY" , e.getMessage());
                    }

                    @Override
                    public void onNext(Article article) {
                        article1[0] = article;
                    }
                });
        return START_NOT_STICKY;
    }

    private void showNews(final Article article) {
        Picasso.with(getApplicationContext())
                .load(article.getUrlToImage())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Intent resultIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                        stackBuilder.addParentStack(LoginActivity.class);
                        stackBuilder.addNextIntent(resultIntent);
                        PendingIntent resultPendingIntent =
                                stackBuilder.getPendingIntent(
                                        0,
                                        PendingIntent.FLAG_UPDATE_CURRENT
                                );
                        Notification notification = new Notification.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.news)
                                .setContentTitle(article.getTitle())
                                .setLargeIcon(bitmap)
                                .setContentIntent(resultPendingIntent)
                                .setStyle( new Notification.BigTextStyle()
                                .bigText(article.getDescription().substring(0, 50) + "..."))
                                .build();
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(1020, notification);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
