package sheva.economicprovider.mvp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import sheva.economicprovider.R;
import sheva.economicprovider.mvp.model.entities.ExchangeRate;
import sheva.economicprovider.mvp.presenter.CurrencyItemFragmentPresenter;
import sheva.economicprovider.mvp.ui.adapters.CurrencyItemAdapter;
import sheva.economicprovider.mvp.ui.interfaces.ICurrencyItemFragmentView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CurrencyItemFragment extends MvpAppCompatFragment implements ICurrencyItemFragmentView{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private CurrencyItemAdapter adapter;
    @InjectPresenter
    CurrencyItemFragmentPresenter presenter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CurrencyItemFragment() {
    }

    public static CurrencyItemFragment newInstance(int columnCount) {
        CurrencyItemFragment fragment = new CurrencyItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currencyitem_list, container, false);
        adapter = new CurrencyItemAdapter(mListener, this.getContext());
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        }
        if (mListener.setDateForRequest()[1] != null) {
            presenter.updateList(mListener.setDateForRequest()[0], mListener.setDateForRequest()[1]);
        } else {
            presenter.updateList(mListener.setDateForRequest()[0]);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //ICurrencyItemFragmentView
    @Override
    public void updateList(List<ExchangeRate> list) {
        adapter.addList(list);
    }

    public interface OnListFragmentInteractionListener {
        String[] setDateForRequest();
    }
}
