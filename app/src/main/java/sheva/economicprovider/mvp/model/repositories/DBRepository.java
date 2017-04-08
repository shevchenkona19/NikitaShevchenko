package sheva.economicprovider.mvp.model.repositories;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.db.DBHelper;
import sheva.economicprovider.mvp.model.entities.Article;

/**
 * Created by shevc on 07.04.2017.
 * l
 */

public class DBRepository {
    @Inject
    DBHelper helper;
    @Inject
    BusinessInsiderRepository repository;

    public DBRepository(){
        App.get().plusDBComponent().inject(this);
        App.get().plusRetrofitComponent().inject(this);
    }

    public void insert(Article article) {
        helper.insert(article);
    }

    public Observable<List<Article>> getAllArticlesFromDB(){
        return helper.getAllArticlesFromDB();
    }

    public Observable<List<Article>> getLimitedAmountOfArticles(int limit) {
        return helper.getLimitedAmountOfArticles(limit);
    }

    public Observable<Article> getFilteredArticles() {
        List<Article> list1 = new ArrayList<>();
        String currTime = new SimpleDateFormat("HHmm").format(new Date());
        int time = Integer.valueOf(currTime);
        String date = new SimpleDateFormat("d.M.Y").format(new Date());
        helper.getFilteredArticles(time)
                .subscribe(new Subscriber<List<Article>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MY", e.getMessage());
                    }

                    @Override
                    public void onNext(List<Article> list) {
                        if (list.size() > 0) {
                            list1.addAll(list);
                        }
                    }
                });
        if (list1.size() > 0) {
            return Observable.from(list1);
        } else {
            helper.dropTable();
            return repository.getNewsEntity()
                    .map(newsEntity -> {
                        List<Article> list2 = newsEntity.getArticles();
                        for (Article article: list2) {
                            helper.insert(article);
                        }
                        return list2;
                    }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(Observable::from);
        }
    }
}
