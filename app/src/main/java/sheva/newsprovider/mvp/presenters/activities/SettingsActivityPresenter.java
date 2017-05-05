package sheva.newsprovider.mvp.presenters.activities;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.ISettingsActivityPresenter;
import sheva.newsprovider.mvp.ui.base.ISettingsActivityView;

/**
 * Created by shevc on 07.04.2017.
 * f
 */
@InjectViewState
public class SettingsActivityPresenter extends BasePresenter<ISettingsActivityView> implements ISettingsActivityPresenter {
    @Inject
    DataManager manager;

    public SettingsActivityPresenter () {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void exit() {
        manager.unregisterCurrentUser();
        manager.setIsUsingVk(false);
        getViewState().exit();
    }

    @Override
    public void changeInterests() {
        manager.deleteInterests();
        getViewState().changeInterests();
    }

    @Override
    public void usePhoneBrowser(boolean b) {
        manager.usePhoneBrowser(b);
    }

    @Override
    public void useFahrenheit(boolean b) {
        manager.setIsUsingFarenheit(b);
    }

    @Override
    public void setSwitchBrowser() {
        getViewState().setSwitchBrowser(manager.isUsingPhoneBrowser());
    }

    @Override
    public void setSwitchFahrenheit() {
        getViewState().setSwitchFarenheit(manager.isUsingFarenheit());
    }

    @Override
    public void setSwitchNotify() {
        getViewState().setSwitchNotifications(manager.isNotify());
    }

    @Override
    public void useNotify(boolean b) {
        manager.setNotifications(b);
    }


}
