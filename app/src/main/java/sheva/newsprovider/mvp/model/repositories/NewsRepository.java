package sheva.newsprovider.mvp.model.repositories;

import javax.inject.Inject;

import rx.Observable;
import sheva.newsprovider.App;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.mvp.model.entities.NewsEntity;
import sheva.newsprovider.mvp.model.retrofit.NewsAPI;

public class NewsRepository {
    @Inject
    NewsAPI api;

    public NewsRepository() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<NewsEntity> getNews(String source, String sortBy) {
        return api.getNews(source, sortBy, IConstants.ApiKeys.newsAPIKey);
    }
}