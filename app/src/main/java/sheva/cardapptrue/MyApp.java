package sheva.cardapptrue;

import android.app.Application;

import sheva.cardapptrue.component.AppComponent;
import sheva.cardapptrue.component.DaggerAppComponent;
import sheva.cardapptrue.module.AppModule;

/**
 * Created by shevc on 15.03.2017.
 */

public class MyApp extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent =
                DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
