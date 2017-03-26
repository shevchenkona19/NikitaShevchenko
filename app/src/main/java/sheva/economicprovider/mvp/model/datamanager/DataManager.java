package sheva.economicprovider.mvp.model.datamanager;


import android.util.Log;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.entities.ExchangeRate;
import sheva.economicprovider.mvp.model.repositories.PrivateBankRepository;

/**
 * Created by shevc on 23.03.2017.
 */

public class DataManager {
    @Inject
    PrivateBankRepository pbRep;

    @Inject
    public DataManager() {
        App.get().plusRetrofitComponent().inject(this);
    }

    public Observable<ExchangeRate> getObservableOfExchangeRate(String date) {
        Log.d("MY", "GetObsOfExchRate");
        Observable mObservable = pbRep.getCurrencyForDate(date)
                .flatMap(bankCurrency -> Observable.from(bankCurrency.getExchangeRate()))
                .observeOn(Schedulers.io());
        Log.d("MY", "OBSERVABLE IN DATA MANAGER: " + mObservable.toString());
        return mObservable;
    }
}
