package sheva.filmfinder.mvp.presenter.fragments;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.filmfinder.App;
import sheva.filmfinder.mvp.datamanager.DataManager;
import sheva.filmfinder.mvp.model.entities.soon.Result;
import sheva.filmfinder.mvp.presenter.base.BasePresenter;
import sheva.filmfinder.mvp.presenter.interfaces.ISoonFragmentPresenter;
import sheva.filmfinder.mvp.ui.interfaces.ISoonFragmentView;
import timber.log.Timber;

@InjectViewState
public class SoonFragmentPresenter extends BasePresenter<ISoonFragmentView> implements ISoonFragmentPresenter {
    @Inject
    DataManager manager;

    public SoonFragmentPresenter() {
        App.get().getAppComponent().inject(this);
    }
    @Override
    public void updateList() {
        final List<Result> list = new ArrayList<>();
        manager.getSoonFilms().subscribe(new Subscriber<Result>() {
            @Override
            public void onCompleted() {
                getViewState().hideRefresh();
                getViewState().onUpdateList(list);
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
    }
}
