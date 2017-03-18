package sheva.bank.dagger.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.bank.mvp.presenter.MainActivityPresenter;

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
    public MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter();
    }
}
