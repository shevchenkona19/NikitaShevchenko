package sheva.bank.mvp.model.interfaces;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by shevc on 18.03.2017.
 */

public interface BankAPI {
    @GET("/p24api/exchange_rates")
    Call<Repo>
}
