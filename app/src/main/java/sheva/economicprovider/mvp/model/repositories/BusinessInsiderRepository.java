package sheva.economicprovider.mvp.model.repositories;

import javax.inject.Inject;

import rx.Observable;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.entities.NewsEntity;
import sheva.economicprovider.mvp.model.interfaces.NewsAPI;


public class BusinessInsiderRepository {
    @Inject
    NewsAPI newsAPI;

    public BusinessInsiderRepository() {
        App.get().plusRetrofitComponent().inject(this);
    }

    public Observable<NewsEntity> getNewsEntity() {
        return newsAPI.getNews("business-insider", "top", "0a9a0488d7574a669cc08e8f02a84f93");
    }
}
