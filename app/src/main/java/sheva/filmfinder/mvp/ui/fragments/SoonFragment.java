package sheva.filmfinder.mvp.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sheva.filmfinder.IConstants;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.soon.Result;
import sheva.filmfinder.mvp.presenter.fragments.SoonFragmentPresenter;
import sheva.filmfinder.mvp.ui.adapters.SoonFilmsAdapter;
import sheva.filmfinder.mvp.ui.interfaces.ISoonFragmentView;

public class SoonFragment extends MvpAppCompatFragment implements ISoonFragmentView{
    @BindView(R.id.ibSoonCardView)
    ImageButton ibCardView;
    @BindView(R.id.ibSoonListView)
    ImageButton ibSoonGridView;
    @BindView(R.id.rvSoonList)
    RecyclerView rvSoonList;
    @BindView(R.id.pbSoonLoading)
    ProgressBar pbLoading;
    private Unbinder unbinder;
    private SoonFilmsAdapter adapter;
    @InjectPresenter
    SoonFragmentPresenter presenter;

    public static SoonFragment newInstance() {

        Bundle args = new Bundle();

        SoonFragment fragment = new SoonFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public SoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_soon, container, false);
        unbinder = ButterKnife.bind(this, v);
        adapter = new SoonFilmsAdapter(this.getContext());
        rvSoonList.setAdapter(adapter);
        rvSoonList.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.updateList();
        ibCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setViewType(IConstants.viewType.CARDS);
                rvSoonList.setLayoutManager(new LinearLayoutManager(SoonFragment.this.getContext()));
                rvSoonList.setAdapter(adapter);
            }
        });
        ibSoonGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setViewType(IConstants.viewType.GRID);
                rvSoonList.setLayoutManager(new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL));
                rvSoonList.setAdapter(adapter);
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onUpdateList(List<Result> list) {
        adapter.onUpdate(list);
    }

    @Override
    public void hideRefresh() {
        pbLoading.setVisibility(View.GONE);
        rvSoonList.setVisibility(View.VISIBLE);
    }
}
