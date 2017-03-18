package sheva.bank.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.bank.dagger.module.AppModule;
import sheva.bank.dagger.module.RetrofitModule;
import sheva.bank.mvp.view.MainActivity;

/**
 * Created by shevc on 18.03.2017.
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    RetrofitComponent plusRetrofitComponent(RetrofitModule rfModule);

    void inject(MainActivity activity);
}
