package sheva.newsprovider.mvp.presenters.fragments;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.newsprovider.App;
import sheva.newsprovider.mvp.model.datamanager.DataManager;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.presenters.BasePresenter;
import sheva.newsprovider.mvp.presenters.base.INewsFragmentPresenter;
import sheva.newsprovider.mvp.ui.base.INewsFragmentView;

@InjectViewState
public class NewsFragmentPresenter extends BasePresenter<INewsFragmentView> implements INewsFragmentPresenter {
    @Inject
    DataManager manager;

    public NewsFragmentPresenter() {
        App.get().getAppComponent().inject(this);
    }
    @Override
    public void updateList(String source, boolean sortBy) {
        String sort;
        if (sortBy) {
            sort = "latest";
        } else {
            sort = "top";
        }
        final List<Article> list = new ArrayList<>();
        manager.getNewsObservable(source, sort)
                .subscribe(new Subscriber<Article>() {
                    @Override
                    public void onCompleted() {
                        getViewState().updateList(list);
                        getViewState().hideRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MY", e.getMessage());
                        getViewState().hideRefresh();
                        getViewState().showWarning(e.getMessage());
                    }

                    @Override
                    public void onNext(Article article) {
                        list.add(article);
                    }
                });
    }

    @Override
    public void addToFavorite(Article article) {
        manager.insertArticle(article);
    }

    @Override
    public void deleteLast() {
        manager.deleteLast();
    }
}
