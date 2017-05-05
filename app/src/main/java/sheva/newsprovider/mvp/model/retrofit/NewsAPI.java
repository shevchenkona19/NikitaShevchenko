package sheva.newsprovider.mvp.model.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sheva.newsprovider.mvp.model.entities.NewsEntity;

/**
 * Created by shevc on 15.04.2017.
 * d
 */

public interface NewsAPI {

    @GET("v1/articles?")
    Observable<NewsEntity> getNews(@Query("source") String source, @Query("sortBy") String sortBy,
                                   @Query("apiKey") String apiKey);
}
