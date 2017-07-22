package sheva.filmfinder.mvp.presenter.fragments;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import sheva.filmfinder.App;
import sheva.filmfinder.mvp.datamanager.DataManager;
import sheva.filmfinder.mvp.model.entities.kinoload.Result;
import sheva.filmfinder.mvp.presenter.base.BasePresenter;
import sheva.filmfinder.mvp.presenter.interfaces.INowFragmentPresenter;
import sheva.filmfinder.mvp.ui.interfaces.INowFragmentView;
import timber.log.Timber;

@InjectViewState
public class NowFragmentPresenter extends BasePresenter<INowFragmentView> implements INowFragmentPresenter {
    @Inject
    DataManager manager;

    public NowFragmentPresenter() {
        App.get().getAppComponent().inject(this);
    }
    @Override
    public void updateList() {
        final List<Result> list = new ArrayList<>();
        Subscription subscription = manager.getGoingFilms().subscribe(new Subscriber<Result>() {
            @Override
            public void onCompleted() {
                getViewState().onListUpdated(list);
                getViewState().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e.getMessage());
            }

            @Override
            public void onNext(Result result) {
                list.add(result);
            }
        });
        unsubscribeOnDestroy(new CompositeSubscription(subscription));
    }
}
