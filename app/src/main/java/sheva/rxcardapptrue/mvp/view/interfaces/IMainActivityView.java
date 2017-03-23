package sheva.rxcardapptrue.mvp.view.interfaces;

import java.util.List;

import sheva.rxcardapptrue.mvp.model.entities.NewsEntity;

/**
 * Created by shevc on 22.03.2017.
 */

public interface IMainActivityView extends IActivityView {
    void updateList(List<NewsEntity> list);
}
