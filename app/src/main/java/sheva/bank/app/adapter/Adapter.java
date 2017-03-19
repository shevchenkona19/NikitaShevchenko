package sheva.bank.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.bank.JSONObjects.BankCurrency;
import sheva.bank.JSONObjects.ExchangeRate;
import sheva.bank.R;

/**
 * Created by shevc on 17.03.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private List<ExchangeRate> list;
    private LayoutInflater inflater;

    public Adapter(Context context) {
        this.list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("MY", "onCreateVH");
        View v = inflater.inflate(R.layout.each_item, parent, false);
        return new AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        if (String.valueOf(list.get(position).getPurchaseRate()) == "null" ||
                String.valueOf(list.get(position).getPurchaseRate()).equals("null")) {
            holder.tvBuy.setText(String.valueOf(list.get(position).getPurchaseRateNB()));
            holder.tvCurrency.setText(list.get(position).getCurrency());
            holder.tvSell.setText(String.valueOf(list.get(position).getSaleRateNB()));
        } else {
            holder.tvBuy.setText(String.valueOf(list.get(position).getPurchaseRate()));
            holder.tvCurrency.setText(list.get(position).getCurrency());
            holder.tvSell.setText(String.valueOf(list.get(position).getSaleRate()));
        }
    }

    public void addBankCurrency(BankCurrency currency) {
        Log.d("MY", "addBankCurr");
        list.clear();
        for (ExchangeRate er : currency.getExchangeRate()) {
            list.add(er);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class AdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBuy)
        TextView tvBuy;
        @BindView(R.id.tvCurrency)
        TextView tvCurrency;
        @BindView(R.id.tvSell)
        TextView tvSell;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            Log.d("MY", "new Card");
            ButterKnife.bind(this, itemView);
        }
    }
}
