package sheva.economicprovider.mvp.ui.interfaces;

import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import sheva.economicprovider.mvp.model.entities.Article;

/**
 * Created by shevc on 26.03.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface INewsActivityView extends MvpView {
    void updateList(List<Article> list);
    void startItemActivity(int position, View v);
}
