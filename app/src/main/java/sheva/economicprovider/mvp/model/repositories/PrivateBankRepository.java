package sheva.economicprovider.mvp.model.repositories;


import javax.inject.Inject;

import rx.Observable;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.entities.BankCurrency;
import sheva.economicprovider.mvp.model.interfaces.PbAPI;

/**
 * Created by shevc on 23.03.2017.
 */

public class PrivateBankRepository {
    @Inject
    PbAPI pbAPI;

    public PrivateBankRepository(){
        App.get().plusRetrofitComponent().inject(this);
    }

    public Observable<BankCurrency> getCurrencyForDate(String date){
        return pbAPI.getBank(date);
    }
}
