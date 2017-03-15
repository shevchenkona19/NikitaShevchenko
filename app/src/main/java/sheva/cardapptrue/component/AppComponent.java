package sheva.cardapptrue.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.cardapptrue.MainActivity;
import sheva.cardapptrue.module.AppModule;

/**
 * Created by shevc on 15.03.2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
}
