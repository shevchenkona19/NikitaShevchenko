package sheva.economicprovider.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.presenter.interfaces.INewsActivityPresenter;
import sheva.economicprovider.mvp.ui.activities.NewsActivity;

/**
 * Created by shevc on 26.03.2017.
 */

public class NewsActivityPresenter extends BasePresenter<NewsActivity> implements INewsActivityPresenter {
    @Inject
    DataManager manager;

    public NewsActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void updateList() {
        List<Article> list = new ArrayList<>();
        manager.getObservableOfArticle()
                .subscribe(new Subscriber<Article>() {
                    @Override
                    public void onCompleted() {
                        getView().updateList(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Article article) {
                        list.add(article);
                    }
                });
    }
}
