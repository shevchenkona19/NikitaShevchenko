package sheva.economicprovider.mvp.model.interfaces;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sheva.economicprovider.mvp.model.entities.BankCurrency;


public interface PbAPI {

    @GET("p24api/exchange_rates?json")
    Observable<BankCurrency> getBank(@Query("date") String date);
}
