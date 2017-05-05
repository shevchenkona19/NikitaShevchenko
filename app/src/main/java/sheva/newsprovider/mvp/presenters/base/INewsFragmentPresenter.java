package sheva.newsprovider.mvp.presenters.base;

import sheva.newsprovider.mvp.model.entities.Article;

public interface INewsFragmentPresenter {
    void updateList(String source, boolean sortBy);
    void addToFavorite(Article article);
    void deleteLast();
}
