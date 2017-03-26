package sheva.economicprovider.mvp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.model.entities.ExchangeRate;
import sheva.economicprovider.mvp.ui.fragments.CurrencyItemFragment;

public class CurrencyItemAdapter extends RecyclerView.Adapter<CurrencyItemAdapter.ViewHolder> {

    private final List<ExchangeRate> mValues;
    private final CurrencyItemFragment.OnListFragmentInteractionListener mListener;
    private int lastPosition;
    private Context context;

    public CurrencyItemAdapter(CurrencyItemFragment.OnListFragmentInteractionListener listener, Context context) {
        mListener = listener;
        mValues = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_currencyitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (String.valueOf(mValues.get(position).getPurchaseRate()) == "null" ||
                String.valueOf(mValues.get(position).getPurchaseRate()).equals("null")) {
            holder.tvBuy.setText(String.valueOf(mValues.get(position).getPurchaseRateNB()));
            holder.tvCurrency.setText(mValues.get(position).getCurrency());
            holder.tvSell.setText(String.valueOf(mValues.get(position).getSaleRateNB()));
        } else {
            holder.tvBuy.setText(String.valueOf(mValues.get(position).getPurchaseRate()));
            holder.tvCurrency.setText(mValues.get(position).getCurrency());
            holder.tvSell.setText(String.valueOf(mValues.get(position).getSaleRate()));
        }
        addAnimation(holder, position);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    public void addList(List<ExchangeRate> list){
        mValues.clear();
        mValues.addAll(list);
        notifyDataSetChanged();
    }

    private void addAnimation(RecyclerView.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.from_bottom : R.anim.from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBuy)
        TextView tvBuy;
        @BindView(R.id.tvCurrency)
        TextView tvCurrency;
        @BindView(R.id.tvSell)
        TextView tvSell;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
