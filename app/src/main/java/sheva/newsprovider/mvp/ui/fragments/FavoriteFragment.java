package sheva.newsprovider.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.ArticleDB;
import sheva.newsprovider.mvp.presenters.fragments.FavoriteFragmentPresenter;
import sheva.newsprovider.mvp.ui.adapters.FavoriteFragmentAdapter;
import sheva.newsprovider.mvp.ui.base.IFavoriteFragmentView;

public class FavoriteFragment extends MvpAppCompatFragment implements IFavoriteFragmentView {
    @BindView(R.id.rvFavoriteList)
    RecyclerView rvFavorite;
    @InjectPresenter
    FavoriteFragmentPresenter presenter;
    private Unbinder unbinder;
    private FavoriteFragmentAdapter adapter;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    public FavoriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        unbinder = ButterKnife.bind(this, v);
        adapter = new FavoriteFragmentAdapter(getContext());
        rvFavorite.setAdapter(adapter);
        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getLayoutPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    adapter.deleteAtPosition(pos);
                    presenter.deleteItemFromDB(adapter.getItemAtPosition(pos)
                            .getNewsId());
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvFavorite);
        presenter.updateList();
        return v;
    }


    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    //IFavoriteFragmentView
    @Override
    public void updateListWithArticles(List<ArticleDB> list) {
        adapter.updateListWithArticles(list);
    }
}
