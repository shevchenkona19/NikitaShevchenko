package sheva.bank.mvp.view.interfaces;

import sheva.bank.JSONObjects.BankCurrency;

/**
 * Created by shevc on 17.03.2017.
 */

public interface IMainActivityView extends IActivityView {
    void showText(String text);
    void showDialogForDate();
    void updateList(BankCurrency currency);
    void buildOptionsMenu();
}
