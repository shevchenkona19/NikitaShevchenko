package sheva.filmfinder.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.filmfinder.mvp.datamanager.DataManager;
import sheva.filmfinder.mvp.model.repositories.KinoRepository;

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
    public KinoRepository provideKinoRepository() {
        return new KinoRepository();
    }
}
