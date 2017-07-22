package sheva.filmfinder;

import android.app.Application;
import android.util.Log;
import sheva.filmfinder.di.component.AppComponent;
import sheva.filmfinder.di.component.DaggerAppComponent;
import sheva.filmfinder.di.module.AppModule;
import timber.log.Timber;


public class App extends Application {
    private static App instance;
    private AppComponent appComponent;
    public static App get() {
        return instance;
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.Tree() {
            @Override
            protected void log(int priority, String tag, String message, Throwable t) {
                Log.d(tag, "P:" + priority + "\n" + message + "T:" + t.getMessage());
            }
        });
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
}
