package sheva.economicprovider.mvp.model.interfaces;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import sheva.economicprovider.mvp.model.entities.NBCurrency;

/**
 * Created by shevc on 01.04.2017.
 *
 */

public interface NBAPI {
    @GET("NBUStatService/v1/statdirectory/exchange?json")
    Observable<List<NBCurrency>> getNBCurrency();
}
