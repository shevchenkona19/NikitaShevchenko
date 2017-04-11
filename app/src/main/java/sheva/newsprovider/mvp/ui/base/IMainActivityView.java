package sheva.newsprovider.mvp.ui.base;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by shevc on 06.04.2017.
 *
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface IMainActivityView extends MvpView {
    void inflatePhoto(String name, Bitmap img);
}
