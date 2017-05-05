package sheva.newsprovider.mvp.ui.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.model.entities.WeatherEntity;

/**
 * Created by shevc on 07.04.2017.
 * c
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface IFeedFragmentView extends MvpView {
    void receiveArticles(List<List<Article>> articleList);

    void receiveWeather(List<WeatherEntity> weatherList);

    void setUserInterests(List<Interest> interestList);

    void updateList();
}
