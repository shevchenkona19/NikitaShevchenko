package sheva.newsprovider;

import android.app.Application;

import java.security.Permission;

import javax.inject.Inject;

import sheva.newsprovider.di.component.AppComponent;
import sheva.newsprovider.di.component.DaggerAppComponent;
import sheva.newsprovider.di.module.AppModule;
import sheva.newsprovider.mvp.model.repositories.SharedPreferencesRepository;
import sheva.newsprovider.utils.Navigator;

/**
 * Created by shevc on 06.04.2017.
 * s
 */

public class App extends Application {
    protected static App instance;
    private AppComponent appComponent;
    @Inject
    SharedPreferencesRepository sharedPreferencesRepository;
    @Inject
    Navigator navigator;


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
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
        appComponent.inject(this);
        if (sharedPreferencesRepository.isRegistered()) {
            navigator.launchLoginActivity();
        } else {
            navigator.launchRegisterActivity();
        }
    }
}
