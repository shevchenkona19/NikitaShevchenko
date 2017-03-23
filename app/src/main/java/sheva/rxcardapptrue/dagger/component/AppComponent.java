package sheva.rxcardapptrue.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.rxcardapptrue.dagger.module.AppModule;
import sheva.rxcardapptrue.mvp.model.manager.DataManager;
import sheva.rxcardapptrue.mvp.model.repositories.NewsRep;
import sheva.rxcardapptrue.mvp.presenter.MainActivityPresenter;
import sheva.rxcardapptrue.mvp.view.ui.ItemActivity;
import sheva.rxcardapptrue.mvp.view.ui.MainActivity;

/**
 * Created by shevc on 22.03.2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(NewsRep rep);
    void inject(DataManager manager);
    void inject(MainActivityPresenter presenter);
    void inject(ItemActivity activity);
    void inject(MainActivity activity);
}
