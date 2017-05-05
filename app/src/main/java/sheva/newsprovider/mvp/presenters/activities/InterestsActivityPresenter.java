package sheva.newsprovider.mvp.presenters.activities;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.IInterestsActivityPresenter;
import sheva.newsprovider.mvp.ui.base.IInterestsActivityView;

@InjectViewState
public class InterestsActivityPresenter extends BasePresenter<IInterestsActivityView> implements IInterestsActivityPresenter {
    @Inject
    DataManager manager;

    public InterestsActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void sendListOfInterests(List<Interest> list) {
        manager.setUserInterests(list);
        getViewState().startMainActivity();
    }
}
