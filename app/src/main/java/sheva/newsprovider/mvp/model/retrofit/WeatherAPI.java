package sheva.newsprovider.mvp.model.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sheva.newsprovider.mvp.model.entities.WeatherEntity;

/**
 * Created by shevc on 16.04.2017.
 * x
 */

public interface WeatherAPI {

    @GET("data/2.5/weather?")
    Observable<WeatherEntity> getWeather(@Query("lat") String lat, @Query("lon")String lon,
                                         @Query("units") String units,
                                         @Query("APPID") String key);
}
