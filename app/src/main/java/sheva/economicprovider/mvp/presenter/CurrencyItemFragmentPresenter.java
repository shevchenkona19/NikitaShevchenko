package sheva.economicprovider.mvp.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.entities.ExchangeRate;
import sheva.economicprovider.mvp.presenter.interfaces.ICurrencyItemFragmentPresenter;
import sheva.economicprovider.mvp.ui.interfaces.ICurrencyItemFragmentView;

/**
 * Created by shevc on 23.03.2017.
 */

public class CurrencyItemFragmentPresenter extends BasePresenter<ICurrencyItemFragmentView> implements ICurrencyItemFragmentPresenter {
    @Inject
    DataManager dataManager;

    public CurrencyItemFragmentPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void updateList(String date) {
        dataManager.getObservableOfExchangeRate(date)
                .map((Func1<ExchangeRate, Object>) exchangeRate -> exchangeRate)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MY", e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d("MY", "onNext");
                        getView().updateList((List<ExchangeRate> )o);
                    }
                });
    }
}