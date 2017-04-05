package sheva.economicprovider;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import sheva.economicprovider.dagger.component.AppComponent;
import sheva.economicprovider.dagger.component.DaggerAppComponent;
import sheva.economicprovider.dagger.component.RetrofitComponent;
import sheva.economicprovider.dagger.module.AppModule;
import sheva.economicprovider.dagger.module.RetrofitModule;
import sheva.economicprovider.mvp.ui.activities.DialogActivity;


public class App extends Application {
    protected static App instance;

    public static App get() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent appComponent;
    private RetrofitComponent retrofitComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }

    public RetrofitComponent plusRetrofitComponent() {
        if (retrofitComponent == null) {
            retrofitComponent = appComponent.plusRetrofitComponent(new RetrofitModule());
        }
        return retrofitComponent;
    }

    public void clearRetrofitComponent() {
        retrofitComponent = null;
    }

    public static class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo infoMobile = connectivityManager.getActiveNetworkInfo();
            if (infoMobile.getType() != ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, "WIFI DISCONNECTED", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, DialogActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            } else if(infoMobile.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(context, "Mobile Network", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
