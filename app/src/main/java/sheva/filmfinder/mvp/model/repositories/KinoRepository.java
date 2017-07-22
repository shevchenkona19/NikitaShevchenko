package sheva.filmfinder.mvp.model.repositories;


import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sheva.filmfinder.App;
import sheva.filmfinder.mvp.model.api.KinoAPI;
import sheva.filmfinder.mvp.model.entities.cinemas.CinemaDataEntity;
import sheva.filmfinder.mvp.model.entities.kinoload.DataEntity;
import sheva.filmfinder.mvp.model.entities.soon.SoonDataEntity;

public class KinoRepository {
    @Inject
    KinoAPI kinoAPI;

    public KinoRepository() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<DataEntity> getGoingFilms() {
        return kinoAPI.getGoingFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SoonDataEntity> getSoonFilms() {
        return kinoAPI.getSoonFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CinemaDataEntity> getCinemas() {
        return kinoAPI.getCinemas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
