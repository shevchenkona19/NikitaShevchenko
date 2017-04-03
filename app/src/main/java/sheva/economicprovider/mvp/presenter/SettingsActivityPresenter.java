package sheva.economicprovider.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.entities.NBCurrency;
import sheva.economicprovider.mvp.presenter.interfaces.ISettingsActivityPresenter;
import sheva.economicprovider.mvp.ui.interfaces.ISettingsActivityView;

/**
 * Created by shevc on 01.04.2017.
 */
public class SettingsActivityPresenter extends TBasePresenter<ISettingsActivityView> implements ISettingsActivityPresenter{

    @Inject
    DataManager manager;

    public SettingsActivityPresenter(){
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void getNotify() {
        getView().changeSwitchState(manager.getSendNotify());
    }

    @Override
    public void setNotify() {
        manager.setSendNotify(getView().getSwitchState());
    }

    @Override
    public void prepareNotification() {
        List<NBCurrency> list2 = new ArrayList<>();
        manager.getNBCurrencyList()
                .take(10)
                .subscribe(new Subscriber<List<NBCurrency>>() {
                    @Override
                    public void onCompleted() {
                        getView().prepareNotify(list2);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(SettingsActivityPresenter.class.getSimpleName(), e.getMessage());
                    }

                    @Override
                    public void onNext(List<NBCurrency> list) {
                        list2.addAll(list);
                    }
                });
    }
}
