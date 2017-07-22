package sheva.filmfinder.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.filmfinder.di.module.AppModule;
import sheva.filmfinder.di.module.RetrofitModule;
import sheva.filmfinder.mvp.datamanager.DataManager;
import sheva.filmfinder.mvp.model.repositories.KinoRepository;
import sheva.filmfinder.mvp.presenter.fragments.CinemaFragmentPresenter;
import sheva.filmfinder.mvp.presenter.fragments.NowFragmentPresenter;
import sheva.filmfinder.mvp.presenter.fragments.SoonFragmentPresenter;

@Component(modules = {AppModule.class, RetrofitModule.class})
@Singleton
public interface AppComponent {
    void inject(KinoRepository repository);
    void inject(DataManager manager);
    void inject(NowFragmentPresenter presenter);
    void inject(SoonFragmentPresenter presenter);
    void inject(CinemaFragmentPresenter presenter);
}
