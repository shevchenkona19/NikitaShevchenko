package sheva.economicprovider.dagger.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.economicprovider.mvp.model.datamanager.DataManager;

/**
 * Created by shevc on 18.03.2017.
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

}
