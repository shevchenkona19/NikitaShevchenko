package sheva.economicprovider.mvp.ui.interfaces;


/**
 * Created by shevc on 17.03.2017.
 */

public interface IMainActivityView extends IActivityView {
    void showText(String text);
    void showDialogForDate();
    void showCurrencyFragment();
    void removeCurrencyFragment();
}
