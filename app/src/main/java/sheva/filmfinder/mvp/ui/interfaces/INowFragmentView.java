package sheva.filmfinder.mvp.ui.interfaces;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import sheva.filmfinder.mvp.model.entities.kinoload.Result;

public interface INowFragmentView extends MvpView {
    void onListUpdated(List<Result> list);
    void hideLoading();
}
