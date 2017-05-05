package sheva.newsprovider.mvp.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ISettingsActivityView extends MvpView {
    void exit();
    void changeInterests();
    void setSwitchBrowser(boolean b);
    void setSwitchFarenheit(boolean b);
    void setSwitchNotifications(boolean b);
}