package sheva.newsprovider.mvp.model.repositories;

import javax.inject.Inject;

import rx.Observable;
import sheva.newsprovider.App;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.mvp.model.entities.WeatherEntity;
import sheva.newsprovider.mvp.model.retrofit.WeatherAPI;

public class WeatherRepository {
    @Inject
    WeatherAPI api;

    public WeatherRepository() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<WeatherEntity> getWeatherForCoordinates(double lon, double lat, String units) {
        return api.getWeather(String.valueOf(lat), String.valueOf(lon),
                units,
                IConstants.ApiKeys.weatherAPIKey);
    }
}
