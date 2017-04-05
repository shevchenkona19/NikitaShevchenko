package sheva.economicprovider.mvp.model.datamanager;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.model.entities.BankCurrency;
import sheva.economicprovider.mvp.model.entities.ExchangeRate;
import sheva.economicprovider.mvp.model.entities.NBCurrency;
import sheva.economicprovider.mvp.model.entities.NewsEntity;
import sheva.economicprovider.mvp.model.interfaces.NBAPI;
import sheva.economicprovider.mvp.model.repositories.BusinessInsiderRepository;
import sheva.economicprovider.mvp.model.repositories.NationalBankRepository;
import sheva.economicprovider.mvp.model.repositories.PrivateBankRepository;
import sheva.economicprovider.mvp.model.repositories.SharedPreferencesRepository;


public class DataManager {
    @Inject
    PrivateBankRepository pbRep;
    @Inject
    BusinessInsiderRepository businessInsiderRepository;
    @Inject
    SharedPreferencesRepository spRep;
    @Inject
    NationalBankRepository nationalBankRepository;

    @Inject
    public DataManager() {
        App.get().plusRetrofitComponent().inject(this);
    }

    public Observable<ExchangeRate> getObservableOfExchangeRate(String date) {
        Observable observable = pbRep.getCurrencyForDate(date)
                .flatMap((Func1<BankCurrency, Observable<?>>) bankCurrency -> Observable.from(bankCurrency.getExchangeRate()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return observable;
    }

    public Observable<Article> getObservableOfArticle(){
        Observable observable = businessInsiderRepository.getNewsEntity()
                .flatMap((Func1<NewsEntity, Observable<?>>) newsEntity -> Observable.from(newsEntity.getArticles()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return observable;
    }

    public Observable<List<NBCurrency>> getNBCurrencyList(){
        return nationalBankRepository.getListOfCurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<NBCurrency>> getNBCurrencyListForService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://bank.gov.ua/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NBAPI nbapi = retrofit.create(NBAPI.class);
        return nbapi.getNBCurrency();
    }

    public void setSendNotify(boolean b){
        spRep.setSendNotification(b);
    }

    public boolean getSendNotify(){
        return spRep.getSendNotification();
    }


}
