package sheva.bank.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import sheva.bank.mvp.model.interfaces.BankAPI;

/**
 * Created by shevc on 18.03.2017.
 */
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public BankAPI provideBankAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BankAPI.class);
    }
}
