package sheva.rxcardapptrue.dagger.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sheva.rxcardapptrue.mvp.model.manager.DataManager;
import sheva.rxcardapptrue.mvp.model.repositories.NewsRep;
import sheva.rxcardapptrue.mvp.presenter.ItemActivityPresenter;
import sheva.rxcardapptrue.mvp.presenter.MainActivityPresenter;

/**
 * Created by shevc on 22.03.2017.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Application application){
        context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public NewsRep provideNewsRep(){
        return new NewsRep();
    }

    @Provides
    @Singleton
    public DataManager provideDataManager(){
        return new DataManager();
    }

    @Provides
    public ItemActivityPresenter provideItemActivityPresenter(){
        return new ItemActivityPresenter();
    }

    @Provides
    public MainActivityPresenter provideMainActivityPresenter(){
        return new MainActivityPresenter();
    }
}
