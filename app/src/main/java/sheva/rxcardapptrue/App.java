package sheva.rxcardapptrue;

import android.app.Application;
import android.util.Log;

import sheva.rxcardapptrue.dagger.component.AppComponent;
import sheva.rxcardapptrue.dagger.component.DaggerAppComponent;
import sheva.rxcardapptrue.dagger.module.AppModule;

/**
 * Created by shevc on 22.03.2017.
 */

public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MY", "onCrApp");
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        Log.d("MY", "COMPONENT: " + appComponent);
        if (appComponent == null){
            Log.d("MY", "component is null");
        }
    }
}
