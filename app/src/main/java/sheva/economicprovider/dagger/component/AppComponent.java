package sheva.economicprovider.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.economicprovider.dagger.module.AppModule;
import sheva.economicprovider.dagger.module.RetrofitModule;
import sheva.economicprovider.mvp.presenter.CurrencyItemFragmentPresenter;
import sheva.economicprovider.mvp.presenter.NewsActivityPresenter;

/**
 * Created by shevc on 18.03.2017.
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    RetrofitComponent plusRetrofitComponent(RetrofitModule rfModule);

    void inject(CurrencyItemFragmentPresenter presenter);
    void inject(NewsActivityPresenter presenter);
}
