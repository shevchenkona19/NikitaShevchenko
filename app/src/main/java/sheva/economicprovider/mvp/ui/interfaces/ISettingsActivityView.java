package sheva.economicprovider.mvp.ui.interfaces;


import java.util.List;

import sheva.economicprovider.mvp.model.entities.NBCurrency;

/**
 * Created by shevc on 01.04.2017.
 */
public interface ISettingsActivityView extends IActivityView{
    void changeSwitchState(boolean b);
    boolean getSwitchState();
    void prepareNotify(List<NBCurrency> list);
}
