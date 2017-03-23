package sheva.rxcardapptrue.mvp.presenter;

import android.support.annotation.Nullable;

import sheva.rxcardapptrue.mvp.view.interfaces.IView;

/**
 * Created by shevc on 22.03.2017.
 */

public abstract class BasePresentor<V extends IView> {
    @Nullable
    private V v;

    public void bind(V v) {
        this.v = v;
    }

    public void unbind() {
        v = null;
    }

    @Nullable
    public V getView() {
        return v;
    }
}
