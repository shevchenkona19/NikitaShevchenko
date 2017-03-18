package sheva.bank.mvp.presenter;

import javax.inject.Inject;

import sheva.bank.App;
import sheva.bank.mvp.model.interfaces.BankAPI;
import sheva.bank.mvp.presenter.interfaces.IMainActivityPresenter;
import sheva.bank.mvp.view.interfaces.IMainActivityView;

/**
 * Created by shevc on 18.03.2017.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {
    private IMainActivityView iMainActivityView;
    @Inject
    BankAPI api;

    public MainActivityPresenter() {
        //App.get().inject(getView());
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
    public void updateList() {
        getView().updateList();
    }
}
