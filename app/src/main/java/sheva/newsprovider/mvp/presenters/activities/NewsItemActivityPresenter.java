package sheva.newsprovider.mvp.presenters.activities;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.INewsItemPresenter;
import sheva.newsprovider.mvp.ui.base.INewsItemActivityView;

/**
 * Created by shevc on 07.04.2017.
 * d
 */
@InjectViewState
public class NewsItemActivityPresenter extends BasePresenter<INewsItemActivityView> implements INewsItemPresenter {
    @Inject
    DataManager manager;
    public NewsItemActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }
    @Override
    public void showNews() {
        if (manager.isUsingPhoneBrowser()) {
            getViewState().showBrowser();
        } else {
            getViewState().showWebView();
        }
    }
}
