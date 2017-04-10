package sheva.newsprovider.mvp.presenters.activities;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.ILoginActivityPresenter;
import sheva.newsprovider.mvp.ui.base.ILoginActivityView;

/**
 * Created by shevc on 06.04.2017.
 * f
 */
@InjectViewState
public class LoginActivityPresenter extends BasePresenter<ILoginActivityView> implements ILoginActivityPresenter {
    @Inject
    DataManager manager;

    public LoginActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void login(String username, String password) {
        if(manager.login(username, password)) {
            manager.inflateRegisteredUser();
            getViewState().onLogged();
        } else {
            getViewState().showError("Incorrect username or password.");
        }
    }

    @Override
    public void showError(String err) {
        getViewState().showError(err);
    }
}
