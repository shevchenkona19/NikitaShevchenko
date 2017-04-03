package sheva.economicprovider.mvp.model.repositories;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.entities.NBCurrency;
import sheva.economicprovider.mvp.model.interfaces.NBAPI;

public class NationalBankRepository {
    @Inject
    NBAPI nbapi;

    public NationalBankRepository(){
        App.get().plusRetrofitComponent().inject(this);
    }

    public Observable<List<NBCurrency>> getListOfCurrency(){
        return nbapi.getNBCurrency();
    }
}
