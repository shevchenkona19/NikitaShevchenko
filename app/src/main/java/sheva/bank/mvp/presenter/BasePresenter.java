package sheva.bank.mvp.presenter;

import android.support.annotation.Nullable;

import sheva.bank.mvp.view.interfaces.IView;

/**
 * Created by shevc on 18.03.2017.
 */

public abstract class BasePresenter<V extends IView> {
    @Nullable
    private V v;

    public void bind(V v) {
        this.v = v;
    }

    public void unbind() {
        this.v = null;
    }

    @Nullable
    public V getView(){
        return v;
    }
}
