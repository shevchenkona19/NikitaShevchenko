package sheva.filmfinder.mvp.ui.interfaces;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;


public interface IMainActivityView extends MvpView {
    void showFragment(Fragment fragment);
}
