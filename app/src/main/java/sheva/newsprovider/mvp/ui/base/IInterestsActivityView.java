package sheva.newsprovider.mvp.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by shevc on 06.04.2017.
 *k
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface IInterestsActivityView extends MvpView {
    void startMainActivity();
}
