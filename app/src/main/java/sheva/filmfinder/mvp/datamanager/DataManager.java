package sheva.filmfinder.mvp.datamanager;


import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sheva.filmfinder.App;
import sheva.filmfinder.mvp.model.entities.cinemas.CinemaDataEntity;
import sheva.filmfinder.mvp.model.entities.cinemas.Unmain;
import sheva.filmfinder.mvp.model.entities.kinoload.DataEntity;
import sheva.filmfinder.mvp.model.entities.kinoload.Result;
import sheva.filmfinder.mvp.model.entities.soon.SoonDataEntity;
import sheva.filmfinder.mvp.model.repositories.KinoRepository;

public class DataManager {
    @Inject
    KinoRepository kinoRepository;

    public DataManager() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<Result> getGoingFilms() {
        return kinoRepository.getGoingFilms()
                .flatMap(new Func1<DataEntity, Observable<Result>>() {
                    @Override
                    public Observable<Result> call(DataEntity dataEntity) {
                        return Observable.from(dataEntity.getResult())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    public Observable<sheva.filmfinder.mvp.model.entities.soon.Result> getSoonFilms() {
        return kinoRepository.getSoonFilms()
                .flatMap(new Func1<SoonDataEntity, Observable<sheva.filmfinder.mvp.model.entities.soon.Result>>() {
                    @Override
                    public Observable<sheva.filmfinder.mvp.model.entities.soon.Result> call(SoonDataEntity soonDataEntity) {
                        return Observable.from(soonDataEntity.getResult())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }

    public Observable<Unmain> getCinemas() {
        return kinoRepository.getCinemas()
                .flatMap(new Func1<CinemaDataEntity, Observable<Unmain>>() {
                    @Override
                    public Observable<Unmain> call(CinemaDataEntity cinemaDataEntity) {
                        return Observable.from(cinemaDataEntity.getResult().getUnmain())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                });
    }


}
