package sheva.economicprovider.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import sheva.economicprovider.mvp.presenter.interfaces.IMainActivityPresenter;
import sheva.economicprovider.mvp.ui.interfaces.IMainActivityView;

@InjectViewState
public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {

    @Override
    public void showDialogForDate() {
        getViewState().showDialogForDate();
    }

    @Override
    public void showCurrencyFragment() {
        getViewState().showCurrencyFragment();
    }

    @Override
    public void removeCurrencyFragment() {
        getViewState().removeCurrencyFragment();
    }
}
