package sheva.cardapptrue;

import android.app.Application;
import android.util.Log;

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
        if(appComponent == null){
            Log.d("MY", "Null in appComponent");
        }
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
