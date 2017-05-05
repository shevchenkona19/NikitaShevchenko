package sheva.newsprovider.mvp.presenters.base;

import java.util.List;

import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.model.entities.Interest;

/**
 * Created by shevc on 06.04.2017.
 *
 */

public interface IFeedFragmentPresenter {
    void getArticle(List<Interest> interests);
    void getWeather();
    void setUserInterestsForAdapter();
    void updateList();
    void addToFavorite(Article article);
    void deleteLastFromDb();
}
