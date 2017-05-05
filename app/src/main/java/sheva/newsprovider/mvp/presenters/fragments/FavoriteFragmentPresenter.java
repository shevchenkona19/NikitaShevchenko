package sheva.newsprovider.mvp.presenters.fragments;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.entities.ArticleDB;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.IFavoriteFragmentPresenter;
import sheva.newsprovider.mvp.ui.base.IFavoriteFragmentView;

@InjectViewState
public class FavoriteFragmentPresenter extends BasePresenter<IFavoriteFragmentView> implements IFavoriteFragmentPresenter {
    @Inject
    DataManager manager;

    public FavoriteFragmentPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void updateList() {
        List<ArticleDB> list1 = new ArrayList<>();
        manager.getFavoriteArticles()
                .subscribe(new Subscriber<List<ArticleDB>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MY", "OnComplete");
                        getViewState().updateListWithArticles(list1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MY", e.getMessage());
                    }

                    @Override
                    public void onNext(List<ArticleDB> list) {
                        Log.d("MY", "OnNExtArticles");
                        list1.addAll(list);
                    }
                });
    }

    @Override
    public void deleteItemFromDB(int id) {
        manager.deleteWithId(id);
    }
}
