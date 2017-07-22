package sheva.filmfinder.mvp.ui.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sheva.filmfinder.IConstants;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.kinoload.Result;
import sheva.filmfinder.mvp.presenter.fragments.NowFragmentPresenter;
import sheva.filmfinder.mvp.ui.adapters.GoingFilmsAdapter;
import sheva.filmfinder.mvp.ui.interfaces.INowFragmentView;
import sheva.filmfinder.utils.ItemClickSupport;

public class NowFragment extends MvpAppCompatFragment implements INowFragmentView{
    @BindView(R.id.rvCinemaList)
    RecyclerView rvList;
    @BindView(R.id.ibCardView)
    ImageButton ibCards;
    @BindView(R.id.ibListView)
    ImageButton ibList;
    @BindView(R.id.pbNowLoading)
    ProgressBar pbLoading;
    private Unbinder unbinder;
    private GoingFilmsAdapter adapter;
    @InjectPresenter
    NowFragmentPresenter presenter;
    private ItemClickSupport clickSupport;

    public interface ITransitionOptions {
        void startTransitionFrom(ImageView ivPoster, Result result);
    }

    private static ITransitionOptions iTransitionOptions;

    public static NowFragment newInstance(ITransitionOptions transitionOptions) {
        
        Bundle args = new Bundle();
        iTransitionOptions = transitionOptions;
        NowFragment fragment = new NowFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public NowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_now, container, false);
        adapter = new GoingFilmsAdapter(getContext());
        unbinder = ButterKnife.bind(this, v);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(adapter);
        presenter.updateList();
        ibCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setShowType(IConstants.viewType.CARDS);
                rvList.setLayoutManager(new LinearLayoutManager(getContext()));
                rvList.setAdapter(adapter);
            }
        });
        ibList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setShowType(IConstants.viewType.GRID);
                rvList.setLayoutManager(new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL));
                rvList.setAdapter(adapter);
            }
        });
        clickSupport = ItemClickSupport.addTo(rvList);
        clickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ImageView ivPoster = null;
                switch (adapter.getShowType()){
                    case IConstants.viewType.CARDS:
                        ivPoster = (ImageView) v.findViewById(R.id.ivItemFilmPoster);
                        break;
                    case IConstants.viewType.GRID:
                        ivPoster = (ImageView) v.findViewById(R.id.ivItemGridPoster);
                        break;
                }
                iTransitionOptions.startTransitionFrom(ivPoster, adapter.getItemAtPosition(position));
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        ItemClickSupport.removeFrom(rvList);
        unbinder.unbind();
        iTransitionOptions = null;
        super.onDestroyView();
    }

    @Override
    public void onListUpdated(List<Result> list) {
        adapter.updateList(list);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);
    }
}
