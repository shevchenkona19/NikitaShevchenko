package sheva.economicprovider.mvp.ui.interfaces;

import java.util.List;

import sheva.economicprovider.mvp.model.entities.ExchangeRate;

/**
 * Created by shevc on 23.03.2017.
 */

public interface ICurrencyItemFragmentView extends IFragmentView {
    void updateList(List<ExchangeRate> list);
}
