package sheva.filmfinder.mvp.model.api;


import retrofit2.http.GET;
import rx.Observable;
import sheva.filmfinder.mvp.model.entities.cinemas.CinemaDataEntity;
import sheva.filmfinder.mvp.model.entities.kinoload.DataEntity;
import sheva.filmfinder.mvp.model.entities.soon.SoonDataEntity;

public interface KinoAPI {
    @GET("ajax/kinoafisha_load")
    Observable<DataEntity> getGoingFilms();

    @GET("ajax/skoro_load")
    Observable<SoonDataEntity> getSoonFilms();

    @GET("ajax/kinoteatrs_load")
    Observable<CinemaDataEntity> getCinemas();
}
