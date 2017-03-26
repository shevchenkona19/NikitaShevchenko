package sheva.economicprovider.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sheva.economicprovider.mvp.model.interfaces.NewsAPI;
import sheva.economicprovider.mvp.model.interfaces.PbAPI;
import sheva.economicprovider.mvp.model.repositories.BusinessInsiderRepository;
import sheva.economicprovider.mvp.model.repositories.PrivateBankRepository;

/**
 * Created by shevc on 18.03.2017.
 */
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public PbAPI provideBankAPI(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(PbAPI.class);
    }

    @Provides
    public Gson provideGson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson;
    }

    @Provides
    @Singleton
    public BusinessInsiderRepository provideBusinessInsiderRepository() {
        return new BusinessInsiderRepository();
    }

    @Provides
    @Singleton
    public PrivateBankRepository providePrivateBankRepository() {
        return new PrivateBankRepository();
    }

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


}
