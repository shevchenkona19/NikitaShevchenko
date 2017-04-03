package sheva.economicprovider.mvp.presenter.interfaces;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IMainActivityPresenter {
    void showDialogForDate();
    void showCurrencyFragment();
    void removeCurrencyFragment();
}
