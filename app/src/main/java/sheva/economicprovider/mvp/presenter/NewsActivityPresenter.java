package sheva.economicprovider.mvp.presenter;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.economicprovider.App;
import sheva.economicprovider.mvp.model.datamanager.DataManager;
import sheva.economicprovider.mvp.model.entities.Article;
import sheva.economicprovider.mvp.presenter.interfaces.INewsActivityPresenter;
import sheva.economicprovider.mvp.ui.activities.NewsActivity;
import sheva.economicprovider.mvp.ui.interfaces.INewsActivityView;

/**
 * Created by shevc on 26.03.2017.
 */
@InjectViewState
public class NewsActivityPresenter extends BasePresenter<INewsActivityView> implements INewsActivityPresenter {
    @Inject
    DataManager manager;

    public NewsActivityPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void updateList() {
        List<Article> list = new ArrayList<>();
        manager.getObservableOfArticle()
                .subscribe(new Subscriber<Article>() {
                    @Override
                    public void onCompleted() {
                        getViewState().updateList(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Article article) {
                        list.add(article);
                    }
                });
    }

    @Override
    public void startActivity(int position, View v) {
        getViewState().startItemActivity(position, v);
    }

    @Override
    public void onDestroy() {
        App.get().clearRetrofitComponent();
        super.onDestroy();
    }
}
