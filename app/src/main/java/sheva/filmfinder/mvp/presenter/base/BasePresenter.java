package sheva.filmfinder.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.subscriptions.CompositeSubscription;


public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void unsubscribeOnDestroy(@NonNull CompositeSubscription compositeSubscription){
        compositeSubscription.add(compositeSubscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }
}
