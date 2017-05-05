package sheva.newsprovider.mvp.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import sheva.newsprovider.mvp.model.entities.ArticleDB;
@StateStrategyType(SkipStrategy.class)
public interface IFavoriteFragmentView extends MvpView{
    void updateListWithArticles(List<ArticleDB> list);
}
