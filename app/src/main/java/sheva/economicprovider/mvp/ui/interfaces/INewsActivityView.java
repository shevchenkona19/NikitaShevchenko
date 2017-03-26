package sheva.economicprovider.mvp.ui.interfaces;

import java.util.List;

import sheva.economicprovider.mvp.model.entities.Article;

/**
 * Created by shevc on 26.03.2017.
 */

public interface INewsActivityView extends IActivityView {
    void updateList(List<Article> list);
}
