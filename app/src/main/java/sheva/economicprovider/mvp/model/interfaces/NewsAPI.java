package sheva.economicprovider.mvp.model.interfaces;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sheva.economicprovider.mvp.model.entities.NewsEntity;

/**
 * Created by shevc on 26.03.2017.
 */

public interface NewsAPI {

    @GET("v1/articles?")
    Observable<NewsEntity> getNews(@Query("source") String source, @Query("sortBy") String sortBy,
                                   @Query("apiKey") String apiKey);
}
