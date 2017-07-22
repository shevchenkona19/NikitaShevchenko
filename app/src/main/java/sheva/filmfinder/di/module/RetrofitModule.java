package sheva.filmfinder.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sheva.filmfinder.mvp.model.api.KinoAPI;

@Module
public class RetrofitModule {
    @Provides
    @Singleton
    public KinoAPI provideKinoAPI(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kinoafisha.ua/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(KinoAPI.class);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }
}
