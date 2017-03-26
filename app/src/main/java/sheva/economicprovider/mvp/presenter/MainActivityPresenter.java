package sheva.economicprovider.mvp.presenter;

import sheva.economicprovider.mvp.presenter.interfaces.IMainActivityPresenter;
import sheva.economicprovider.mvp.ui.interfaces.IMainActivityView;

/**
 * Created by shevc on 18.03.2017.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {
    private static String TAG = MainActivityPresenter.class.getSimpleName();

    @Override
    public void showDialogForDate() {
        getView().showDialogForDate();
    }

    @Override
    public void showCurrencyFragment() {
        getView().showCurrencyFragment();
    }

    @Override
    public void removeCurrencyFragment() {
        getView().removeCurrencyFragment();
    }
}
