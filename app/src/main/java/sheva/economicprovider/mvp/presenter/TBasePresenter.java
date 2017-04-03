package sheva.economicprovider.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import sheva.economicprovider.mvp.ui.interfaces.IView;

/**
 * Created by shevc on 01.04.2017.
 */

public class TBasePresenter<V extends IView> {
    @NonNull
    private V v;

    public void bind(@Nullable V v){
        this.v = v;
    }

    public void unbind(){
        v = null;
    }

    public V getView(){
        return v;
    }
}
