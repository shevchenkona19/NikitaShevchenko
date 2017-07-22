package sheva.filmfinder.mvp.presenter.fragments;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.filmfinder.App;
import sheva.filmfinder.mvp.datamanager.DataManager;
import sheva.filmfinder.mvp.model.entities.cinemas.Unmain;
import sheva.filmfinder.mvp.presenter.base.BasePresenter;
import sheva.filmfinder.mvp.presenter.interfaces.ICinemaFragmentPresenter;
import sheva.filmfinder.mvp.ui.interfaces.ICinemaFragmentView;
import timber.log.Timber;

/**
 * Created by shevc on 07.06.2017.
 * Let's GO!
 */
@InjectViewState
public class CinemaFragmentPresenter extends BasePresenter<ICinemaFragmentView> implements ICinemaFragmentPresenter {
    @Inject
    DataManager manager;

    public CinemaFragmentPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void updateList() {
        final List<Unmain> list = new ArrayList<>();
        manager.getCinemas().subscribe(new Subscriber<Unmain>() {
            @Override
            public void onCompleted() {
                getViewState().hideLoading();
                getViewState().onListUpdate(list);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e.getMessage());
            }

            @Override
            public void onNext(Unmain unmain) {
                list.add(unmain);
            }
        })   ;
    }
}
