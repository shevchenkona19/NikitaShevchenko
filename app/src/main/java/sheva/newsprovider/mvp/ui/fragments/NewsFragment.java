package sheva.newsprovider.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;

/**
 * Created by shevc on 06.04.2017.
 *
 */

public class NewsFragment extends MvpAppCompatFragment{
    @BindView(R.id.rvNewsList)
    RecyclerView rvList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
