package sheva.newsprovider.mvp.presenters.fragments;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.model.entities.WeatherEntity;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.IFeedFragmentPresenter;
import sheva.newsprovider.mvp.ui.base.IFeedFragmentView;

/**
 * Created by shevc on 07.04.2017.
 * d
 */
@InjectViewState
public class FeedFragmentPresenter extends BasePresenter<IFeedFragmentView> implements IFeedFragmentPresenter {
    @Inject
    DataManager manager;

    public FeedFragmentPresenter() {
        App.get().getAppComponent().inject(this);
    }


    @Override
    public void getArticle(final List<Interest> interests) {
        if (interests == null) {
            return;
        }
        final List<List<Article>> articleLists = new ArrayList<>();
        final int[] counter = {0};
        for(final Interest i : interests) {
            final List<Article> articleList = new ArrayList<>();
            manager.getNewsObservable(i.getSourceAt(0), "top")
                    .take(3)
                    .subscribe(new Subscriber<Article>() {
                        @Override
                        public void onCompleted() {
                            articleLists.add(articleList);
                            counter[0]++;
                            if (counter[0] == interests.size()) {
                                getViewState().receiveArticles(articleLists);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Article article) {
                            article.setInterestName(i.getName());
                            articleList.add(article);
                        }
                    });
            articleList.clear();
        }
    }

    @Override
    public void getWeather() {
        final List<WeatherEntity> weatherEntityList = new ArrayList<>();
        manager.getWeatherObservable()
                .subscribe(new Subscriber<WeatherEntity>() {
                    @Override
                    public void onCompleted() {
                        getViewState().receiveWeather(weatherEntityList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MY", e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherEntity weatherEntity) {
                        weatherEntityList.add(weatherEntity);
                    }
                });
    }

    @Override
    public void setUserInterestsForAdapter() {
        getViewState().setUserInterests(manager.getUserInterests());
    }

    @Override
    public void updateList() {
        getViewState().updateList();
    }

    @Override
    public void addToFavorite(Article article) {
        manager.insertArticle(article);
    }

    @Override
    public void deleteLastFromDb() {
        manager.deleteLast();
    }

}