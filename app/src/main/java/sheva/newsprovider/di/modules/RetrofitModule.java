package sheva.newsprovider.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sheva.newsprovider.mvp.model.repositories.NewsRepository;
import sheva.newsprovider.mvp.model.repositories.WeatherRepository;
import sheva.newsprovider.mvp.model.retrofit.NewsAPI;
import sheva.newsprovider.mvp.model.retrofit.WeatherAPI;

/**
 * Created by shevc on 15.04.2017.
 * s
 */
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public NewsAPI provideNewsAPI(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(NewsAPI.class);
    }

    @Provides
    @Singleton
    public WeatherAPI provideWeatherAPI(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(WeatherAPI.class);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    public NewsRepository provideNewsRepository() {
        return new NewsRepository();
    }

    @Provides
    @Singleton
    public WeatherRepository provideWeatherRepository() {
        return new WeatherRepository();
    }
}
