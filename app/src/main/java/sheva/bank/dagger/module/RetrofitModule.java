package sheva.bank.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.xml.sax.helpers.XMLReaderFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sheva.bank.mvp.model.interfaces.BankAPI;

/**
 * Created by shevc on 18.03.2017.
 */
@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public BankAPI provideBankAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(BankAPI.class);
    }
}
