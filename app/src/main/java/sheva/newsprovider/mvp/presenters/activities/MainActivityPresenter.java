package sheva.newsprovider.mvp.presenters.activities;

import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.IMainActivityPresenter;
import sheva.newsprovider.mvp.ui.base.IMainActivityView;

/**
 * Created by shevc on 07.04.2017.s
 * s
 */
@InjectViewState
public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {
    @Inject
    DataManager manager;

    public MainActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void inflatePhoto() {
        String[] userInfo = manager.getUserInfo();
        getViewState().inflatePhoto(userInfo[0], userInfo[1]);
    }

    @Override
    public void checkInternet() {
        if (!manager.isInternetAvailable()){
                Log.d("MY", "else");
                getViewState().showDialog("You are not connected to internet", "connect",
                        Settings.ACTION_WIFI_SETTINGS);
        }
    }

    @Override
    public void showFragment(Fragment fragment) {
        getViewState().showFragment(fragment);
    }

    @Override
    public void writeNewPhoto(Uri uri) {
        manager.putPhoto(uri);
    }


}