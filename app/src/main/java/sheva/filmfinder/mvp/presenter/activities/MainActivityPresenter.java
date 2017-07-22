package sheva.filmfinder.mvp.presenter.activities;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;

import sheva.filmfinder.mvp.presenter.base.BasePresenter;
import sheva.filmfinder.mvp.presenter.interfaces.IMainActivityPresenter;
import sheva.filmfinder.mvp.ui.interfaces.IMainActivityView;

@InjectViewState
public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {

    @Override
    public void showFragment(Fragment fragment) {
        getViewState().showFragment(fragment);
    }
}
