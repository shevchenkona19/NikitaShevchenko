package sheva.economicprovider.mvp.presenter.interfaces;

import android.view.View;


public interface INewsActivityPresenter {
    void updateList();
    void startActivity(int position, View v);
    void getNews();
}
