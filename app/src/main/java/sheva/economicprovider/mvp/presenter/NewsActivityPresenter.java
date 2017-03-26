package sheva.economicprovider.mvp.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.presenter.interfaces.INewsActivityPresenter;
import sheva.economicprovider.mvp.ui.activities.NewsActivity;

/**
 * Created by shevc on 26.03.2017.
 */

public class NewsActivityPresenter extends BasePresenter<NewsActivity> implements INewsActivityPresenter{
    @Inject
    DataManager manager;

    public NewsActivityPresenter(){
        App.get().getAppComponent().inject(this);
    }
    @Override
    public void updateList() {
        manager.getObservableOfArticle()
                .subscribeOn(AndroidSchedulers.mainThread())
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
                        getView().updateList(list);
                    }
                });
    }
}
