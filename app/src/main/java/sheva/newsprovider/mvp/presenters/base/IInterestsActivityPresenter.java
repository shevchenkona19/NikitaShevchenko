package sheva.newsprovider.mvp.presenters.base;

import java.util.List;

import sheva.newsprovider.mvp.model.entities.Interest;

/**
 * Created by shevc on 06.04.2017.
 *c
 */

public interface IInterestsActivityPresenter {
    void sendListOfInterests(List<Interest> list);
}
