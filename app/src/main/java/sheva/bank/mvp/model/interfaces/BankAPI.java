package sheva.bank.mvp.model.interfaces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sheva.bank.JSONObjects.BankCurrency;

/**
 * Created by shevc on 18.03.2017.
 */

public interface BankAPI {
    @GET("/p24api/exchange_rates")
    void getBank(@Query("date") String date, Callback<BankCurrency> cb);
}