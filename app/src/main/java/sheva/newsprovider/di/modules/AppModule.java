package sheva.newsprovider.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.repositories.NetworkRepository;
import sheva.newsprovider.mvp.model.repositories.UserRepository;

/**
 * Created by shevc on 09.04.2017.
 * s
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public DataManager provideDataManager(){
        return new DataManager();
    }

    @Provides
    @Singleton
    public UserRepository provideUserRepository() {
        return new UserRepository();
    }

    @Provides
    @Singleton
    public NetworkRepository provideNetworkRepository() {
        return new NetworkRepository();
    }
}
