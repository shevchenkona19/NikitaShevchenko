package sheva.economicprovider.mvp.ui.interfaces;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by shevc on 17.03.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface IMainActivityView extends MvpView {
    void showText(String text);
    void showDialogForDate();
    void showCurrencyFragment();
    void removeCurrencyFragment();
}
