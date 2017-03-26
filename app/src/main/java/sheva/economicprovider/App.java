package sheva.economicprovider;

import android.app.Application;

import sheva.economicprovider.dagger.component.AppComponent;
import sheva.economicprovider.dagger.component.DaggerAppComponent;
import sheva.economicprovider.dagger.component.RetrofitComponent;
import sheva.economicprovider.dagger.module.AppModule;
import sheva.economicprovider.dagger.module.RetrofitModule;

/**
 * Created by shevc on 23.03.2017.
 */

public class App extends Application {
    protected static App instance;

    public static App get() {
        return instance;
    }
    public AppComponent getAppComponent(){
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

    public void clearRetrofitComponent(){
        retrofitComponent = null;
    }
}
