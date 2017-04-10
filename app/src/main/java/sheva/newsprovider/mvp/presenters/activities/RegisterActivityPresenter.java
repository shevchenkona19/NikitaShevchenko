package sheva.newsprovider.mvp.presenters.activities;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.IRegisterActivityPresenter;
import sheva.newsprovider.mvp.ui.base.IRegisterActivityView;

/**
 * Created by shevc on 07.04.2017.
 * s
 */
@InjectViewState
public class RegisterActivityPresenter extends BasePresenter<IRegisterActivityView> implements IRegisterActivityPresenter {
    @Inject
    DataManager manager;

    public RegisterActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }
    @Override
    public void register(String name, String username, String password, Uri img) {
        manager.register(name, username, password, img);
        getViewState().startLoginActivity();
    }
}
