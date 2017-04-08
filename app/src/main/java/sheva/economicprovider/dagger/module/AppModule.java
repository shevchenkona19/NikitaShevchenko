package sheva.economicprovider.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.repositories.SharedPreferencesRepository;

/**
 * Created by shevc on 18.03.2017.
 *
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public DataManager provideDataManager(){
        return new DataManager();
    }

    @Provides
    @Singleton
    public SharedPreferencesRepository provideSharedPreferencesRepository(){
        return new SharedPreferencesRepository();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(){
        return application.getSharedPreferences("NOTIFICATIONS", Context.MODE_PRIVATE);
    }
}
