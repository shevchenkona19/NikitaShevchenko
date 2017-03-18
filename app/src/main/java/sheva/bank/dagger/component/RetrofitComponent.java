package sheva.bank.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.bank.dagger.module.RetrofitModule;

/**
 * Created by shevc on 18.03.2017.
 */
@Component(modules = RetrofitModule.class)
@Singleton
public interface RetrofitComponent {
    //todo injects!!!
}
