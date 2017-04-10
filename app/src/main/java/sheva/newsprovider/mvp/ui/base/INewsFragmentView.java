package sheva.newsprovider.mvp.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by shevc on 07.04.2017.
 * d
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface INewsFragmentView extends MvpView {
}
