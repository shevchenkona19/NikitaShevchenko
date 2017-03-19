package sheva.bank.mvp.presenter.interfaces;

import sheva.bank.JSONObjects.BankCurrency;

/**
 * Created by shevc on 18.03.2017.
 */

public interface IMainActivityPresenter {
    void showText(String text);
    void showDialogForDate();
    void updateList(String date);
}
