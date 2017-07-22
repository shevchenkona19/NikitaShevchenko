package sheva.filmfinder.mvp.ui.interfaces;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import sheva.filmfinder.mvp.model.entities.cinemas.Unmain;

/**
 * Created by shevc on 07.06.2017.
 * Let's GO!
 */

public interface ICinemaFragmentView extends MvpView{
    void hideLoading();
    void onListUpdate(List<Unmain> list);
}
