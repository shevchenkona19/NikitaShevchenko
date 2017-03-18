package sheva.bank.mvp.presenter;

import sheva.bank.mvp.presenter.interfaces.IMainActivityPresenter;
import sheva.bank.mvp.view.interfaces.IMainActivityView;

/**
 * Created by shevc on 18.03.2017.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter{
    private IMainActivityView iMainActivityView;
    public MainActivityPresenter(){

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
