package sheva.filmfinder.mvp.ui.interfaces;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import sheva.filmfinder.mvp.model.entities.soon.Result;


public interface ISoonFragmentView extends MvpView {
    void onUpdateList(List<Result> list);
    void hideRefresh();
}
