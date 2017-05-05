package sheva.newsprovider.mvp.ui.base;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by shevc on 06.04.2017.
 *
 */
public interface IMainActivityView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void inflatePhoto(String name, String img);
    void showDialog(String text, String btnText, String action);
    @StateStrategyType(SkipStrategy.class)
    void showFragment(Fragment fragment);
}