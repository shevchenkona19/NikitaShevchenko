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
import sheva.economicprovider.mvp.model.repositories.DBRepository;
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
    DBRepository dbRepository;

    @Inject
    public DataManager() {
        App.get().plusRetrofitComponent().inject(this);
        App.get().plusDBComponent().inject(this);
    }

    @SuppressWarnings("unchecked")
    public Observable<ExchangeRate> getObservableOfExchangeRate(String date) {

        return (Observable) pbRep.getCurrencyForDate(date)
                .flatMap((Func1<BankCurrency, Observable<?>>) bankCurrency -> Observable.from(bankCurrency.getExchangeRate()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressWarnings("unchecked")
    public Observable<Article> getObservableOfArticle() {
        return (Observable) businessInsiderRepository.getNewsEntity()
                .flatMap((Func1<NewsEntity, Observable<?>>) newsEntity -> Observable.from(newsEntity.getArticles()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<NBCurrency>> getNBCurrencyList() {
        return nationalBankRepository.getListOfCurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<NBCurrency>> getNBCurrencyListForService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank.gov.ua/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        NBAPI nbapi = retrofit.create(NBAPI.class);
        return nbapi.getNBCurrency();
    }

    public void setSendNotify(boolean b) {
        spRep.setSendNotification(b);
    }

    public boolean getSendNotify() {
        return spRep.getSendNotification();
    }

    //db

    public void insert(Article article) {
        dbRepository.insert(article);
    }

    public Observable<List<Article>> getAllArticlesFromDB() {
        return dbRepository.getAllArticlesFromDB();
    }

    public Observable<List<Article>> getLimitedAmountOfArticles(int limit) {
        return dbRepository.getLimitedAmountOfArticles(limit);
    }

    public Observable<Article> getFilteredArticles() {
        return dbRepository.getFilteredArticles();
    }

    public Observable<Article> getAllArticlesFromDB(String query) {
        return dbRepository.getAllArticlesFromDB()
                .flatMap(new Func1<List<Article>, Observable<Article>>() {
                    @Override
                    public Observable<Article> call(List<Article> list) {
                        return Observable.from(list);
                    }
                })
                .filter(article -> article.getAuthor().substring(0, query.length()).equals(query) ||
                        article.getTitle().substring(0, query.length()).equals(query) ||
                        article.getPublishedAt().substring(0, query.length()).equals(query) ||
                        article.getDescription().substring(0, query.length()).equals(query));
    }
}
