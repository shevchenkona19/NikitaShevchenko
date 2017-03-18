package sheva.bank.dagger.component;

import javax.inject.Singleton;

import dagger.Subcomponent;
import sheva.bank.dagger.module.RetrofitModule;
import sheva.bank.mvp.presenter.MainActivityPresenter;

/**
 * Created by shevc on 18.03.2017.
 */
@Subcomponent(modules = RetrofitModule.class)
@Singleton
public interface RetrofitComponent {
    //todo injects!!!
    void inject(MainActivityPresenter activity);
}
