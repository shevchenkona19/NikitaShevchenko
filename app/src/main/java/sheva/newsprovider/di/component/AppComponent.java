package sheva.newsprovider.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.newsprovider.App;
import sheva.newsprovider.NotifyService;
import sheva.newsprovider.Receiver;
import sheva.newsprovider.di.modules.AppModule;
import sheva.newsprovider.di.modules.RetrofitModule;
import sheva.newsprovider.di.modules.SharedPreferencesModule;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.repositories.NetworkRepository;
import sheva.newsprovider.mvp.model.repositories.NewsRepository;
import sheva.newsprovider.mvp.model.repositories.SharedPreferencesRepository;
import sheva.newsprovider.mvp.model.repositories.WeatherRepository;
import sheva.newsprovider.mvp.presenters.activities.InterestsActivityPresenter;
import sheva.newsprovider.mvp.presenters.activities.LoginActivityPresenter;
import sheva.newsprovider.mvp.presenters.activities.MainActivityPresenter;
import sheva.newsprovider.mvp.presenters.activities.NewsItemActivityPresenter;
import sheva.newsprovider.mvp.presenters.activities.RegisterActivityPresenter;
import sheva.newsprovider.mvp.presenters.activities.SettingsActivityPresenter;
import sheva.newsprovider.mvp.presenters.fragments.FavoriteFragmentPresenter;
import sheva.newsprovider.mvp.presenters.fragments.FeedFragmentPresenter;
import sheva.newsprovider.mvp.presenters.fragments.NewsFragmentPresenter;

/**
 * Created by shevc on 09.04.2017.
 * s
 */
@Component(modules = {AppModule.class, SharedPreferencesModule.class, RetrofitModule.class})
@Singleton
public interface AppComponent {

    //Repositories
    void inject(SharedPreferencesRepository repository);
    void inject(NewsRepository repository);
    void inject(WeatherRepository repository);

    //Datamanager
    void inject(DataManager manager);

    //Presenters
    void inject(RegisterActivityPresenter presenter);
    void inject(LoginActivityPresenter presenter);
    void inject(MainActivityPresenter presenter);
    void inject(SettingsActivityPresenter presenter);
    void inject(InterestsActivityPresenter presenter);
    void inject(NewsFragmentPresenter presenter);
    void inject(NewsItemActivityPresenter presenter);
    void inject(FeedFragmentPresenter presenter);
    void inject(FavoriteFragmentPresenter presenter);

    //Utils
    void inject(NotifyService service);
    void inject(Receiver receiver);
    void inject(NetworkRepository repository);
    void inject(App app);
}