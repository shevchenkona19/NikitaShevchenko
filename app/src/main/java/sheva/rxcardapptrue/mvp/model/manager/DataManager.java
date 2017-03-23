package sheva.rxcardapptrue.mvp.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sheva.rxcardapptrue.App;
import sheva.rxcardapptrue.mvp.model.entities.NewsEntity;
import sheva.rxcardapptrue.mvp.model.repositories.NewsRep;

/**
 * Created by shevc on 22.03.2017.
 */

public class DataManager {
    @Inject
    NewsRep newsRep;

    public DataManager() {
        App.getComponent().inject(this);
    }

    public List<NewsEntity> getNewsList() {
        List<NewsEntity> list = new ArrayList<>();
        Observable.from(newsRep.getList())
                .filter(newsEntity -> newsEntity.getTime() > 604800000)
                .take(10)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(newsEntity -> list.add(newsEntity));
        return list;
    }
}
