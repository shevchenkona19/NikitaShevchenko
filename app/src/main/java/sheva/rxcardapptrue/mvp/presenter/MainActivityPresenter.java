package sheva.rxcardapptrue.mvp.presenter;

import javax.inject.Inject;

import sheva.rxcardapptrue.App;
import sheva.rxcardapptrue.mvp.model.manager.DataManager;
import sheva.rxcardapptrue.mvp.presenter.interfaces.IMainActivityPresenter;
import sheva.rxcardapptrue.mvp.view.interfaces.IMainActivityView;

/**
 * Created by shevc on 22.03.2017.
 */

public class MainActivityPresenter extends BasePresentor<IMainActivityView> implements IMainActivityPresenter {
    @Inject
    DataManager manager;

    public MainActivityPresenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void updateList() {
        getView().updateList(manager.getNewsList());
    }
}
