package sheva.economicprovider.mvp.ui.interfaces;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import sheva.economicprovider.mvp.model.entities.ExchangeRate;

/**
 * Created by shevc on 23.03.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ICurrencyItemFragmentView extends IView {
    void updateList(List<ExchangeRate> list);
}
