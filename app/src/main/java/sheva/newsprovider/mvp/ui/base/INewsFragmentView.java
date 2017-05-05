package sheva.newsprovider.mvp.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import sheva.newsprovider.mvp.model.entities.Article;

/**
 * Created by shevc on 07.04.2017.
 * d
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface INewsFragmentView extends MvpView {
    void updateList(List<Article> list);
    void hideRefresh();
    void showWarning(String text);
}
