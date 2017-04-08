package sheva.economicprovider.mvp.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    void unsubscribeOnDestroy(@NonNull Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }
}