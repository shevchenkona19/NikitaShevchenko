package sheva.newsprovider.mvp.presenters;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by shevc on 06.04.2017.
 *
 */

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
