package sheva.economicprovider.mvp.model.datamanager;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.model.entities.ExchangeRate;
import sheva.economicprovider.mvp.model.entities.NewsEntity;
import sheva.economicprovider.mvp.model.repositories.BusinessInsiderRepository;
import sheva.economicprovider.mvp.model.repositories.PrivateBankRepository;

/**
 * Created by shevc on 23.03.2017.
 */

public class DataManager {
    @Inject
    PrivateBankRepository pbRep;
    @Inject
    BusinessInsiderRepository businessInsiderRepository;

    @Inject
    public DataManager() {
        App.get().plusRetrofitComponent().inject(this);
    }

    public Observable<ExchangeRate> getObservableOfExchangeRate(String date) {
        Log.d("MY", "GetObsOfExchRate");
        Observable mObservable = pbRep.getCurrencyForDate(date)
                .flatMap(bankCurrency -> Observable.from(bankCurrency.getExchangeRate()))
                .observeOn(Schedulers.io());
        Log.d("MY", "OBSERVABLE IN DATA MANAGER: " + mObservable.toString());
        return mObservable;
    }

    public Observable<List<Article>> getObservableOfArticle(){
        Observable observable = businessInsiderRepository.getNewsEntity()
                .flatMap((Func1<NewsEntity, Observable<?>>) newsEntity -> Observable.from(newsEntity.getArticles()).toList())
                .observeOn(Schedulers.io());
        return observable;
    }
}
