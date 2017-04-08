package sheva.economicprovider.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.economicprovider.dagger.module.AppModule;
import sheva.economicprovider.dagger.module.DBModule;
import sheva.economicprovider.dagger.module.RetrofitModule;
import sheva.economicprovider.mvp.model.repositories.SharedPreferencesRepository;
import sheva.economicprovider.mvp.presenter.CurrencyItemFragmentPresenter;
import sheva.economicprovider.mvp.presenter.NewsActivityPresenter;
import sheva.economicprovider.mvp.presenter.SettingsActivityPresenter;

/**
 *
 * Created by shevc on 18.03.2017.
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    RetrofitComponent plusRetrofitComponent(RetrofitModule rfModule);
    DBComponent plusDBComponent(DBModule dbModule);
    void inject(CurrencyItemFragmentPresenter presenter);
    void inject(NewsActivityPresenter presenter);
    void inject(SettingsActivityPresenter presenter);
    void inject(SharedPreferencesRepository repository);
}
