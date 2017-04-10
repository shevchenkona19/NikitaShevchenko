package sheva.newsprovider.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.newsprovider.App;
import sheva.newsprovider.di.module.AppModule;
import sheva.newsprovider.di.module.SharedPreferencesModule;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.repositories.SharedPreferencesRepository;
import sheva.newsprovider.mvp.presenters.activities.LoginActivityPresenter;
import sheva.newsprovider.mvp.presenters.activities.MainActivityPresenter;
import sheva.newsprovider.mvp.presenters.activities.RegisterActivityPresenter;
import sheva.newsprovider.utils.Navigator;

/**
 * Created by shevc on 09.04.2017.
 * s
 */
@Component(modules = {AppModule.class, SharedPreferencesModule.class})
@Singleton
public interface AppComponent {
    void inject(SharedPreferencesRepository repository);
    void inject(DataManager manager);
    void inject(RegisterActivityPresenter presenter);
    void inject(LoginActivityPresenter presenter);
    void inject(MainActivityPresenter presenter);
    void inject(App app);
    void inject(Navigator navigator);
}
