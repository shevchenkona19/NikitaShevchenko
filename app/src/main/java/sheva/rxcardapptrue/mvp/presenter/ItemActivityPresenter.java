package sheva.rxcardapptrue.mvp.presenter;

import sheva.rxcardapptrue.mvp.presenter.interfaces.IItemActivityPresenter;
import sheva.rxcardapptrue.mvp.view.interfaces.IItemActivityView;

/**
 * Created by shevc on 22.03.2017.
 */

public class ItemActivityPresenter extends BasePresentor<IItemActivityView> implements IItemActivityPresenter {
    @Override
    public void receiveTransition() {
        getView().receiveTransition();
    }
}
