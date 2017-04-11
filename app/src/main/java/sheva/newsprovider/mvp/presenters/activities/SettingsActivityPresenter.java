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
        getViewState().exit();
    }
}
