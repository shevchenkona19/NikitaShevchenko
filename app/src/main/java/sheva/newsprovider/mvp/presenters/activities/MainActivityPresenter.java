package sheva.newsprovider.mvp.presenters.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
        Object[] userInfo = manager.getUserInfo();
        Log.d("MY", "Bitmap:" + userInfo[1]);
        getViewState().inflatePhoto(((String)userInfo[0]), ((Bitmap)userInfo[1]));
    }
}
