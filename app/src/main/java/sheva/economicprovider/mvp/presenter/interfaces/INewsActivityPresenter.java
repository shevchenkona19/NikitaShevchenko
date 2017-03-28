package sheva.economicprovider.mvp.presenter.interfaces;

import android.view.View;

/**
 * Created by shevc on 26.03.2017.
 */

public interface INewsActivityPresenter {
    void updateList();
    void startActivity(int position, View v);
}
