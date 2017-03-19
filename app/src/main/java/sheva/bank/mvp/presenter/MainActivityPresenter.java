package sheva.bank.mvp.presenter;

import android.util.Log;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sheva.bank.App;
import sheva.bank.JSONObjects.BankCurrency;
import sheva.bank.mvp.model.interfaces.BankAPI;
import sheva.bank.mvp.presenter.interfaces.IMainActivityPresenter;
import sheva.bank.mvp.view.interfaces.IMainActivityView;

/**
 * Created by shevc on 18.03.2017.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {
    private static String TAG = MainActivityPresenter.class.getSimpleName();
    @Inject
    BankAPI api;

    public MainActivityPresenter() {
        App.get().plusRetrofitComponent().inject(this);
    }

    @Override
    public void showText(String text) {
        getView().showText(text);
    }

    @Override
    public void showDialogForDate() {
        getView().showDialogForDate();
    }

    @Override
    public void updateList(String date) {
        Log.d("MY", "starting update list in presenter");
        Call<BankCurrency> call = api.getBank(date);
        call.enqueue(new Callback<BankCurrency>() {
            @Override
            public void onResponse(Call<BankCurrency> call, Response<BankCurrency> response) {
                Log.d("MY", "onResp: " + response);
                BankCurrency currency = response.body();
                getView().updateList(currency);
            }

            @Override
            public void onFailure(Call<BankCurrency> call, Throwable t) {
                //Постоянно попадает сюда
                Log.d("MY", "onFail");
                Log.e(TAG, t.getMessage());
            }
        });

    }
}
