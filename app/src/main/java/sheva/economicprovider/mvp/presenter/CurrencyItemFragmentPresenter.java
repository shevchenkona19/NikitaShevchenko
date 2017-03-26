package sheva.economicprovider.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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
        List<ExchangeRate> list = new ArrayList<>();
        dataManager.getObservableOfExchangeRate(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ExchangeRate>() {
                    @Override
                    public void onCompleted() {
                        getView().updateList(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ExchangeRate exchangeRate) {
                        list.add(exchangeRate);
                    }
                });
    }
}