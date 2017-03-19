package sheva.bank.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public Adapter(Context context){
        this.list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }
    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {

    }

    public void addBankCurrency(BankCurrency currency){
        for(ExchangeRate er : currency.getExchangeRate()){
            list.add(er);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class AdapterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvBuy)
        TextView tvBuy;
        @BindView(R.id.tvCurrency)
        TextView tvCurrency;
        @BindView(R.id.tvSell)
        TextView tvSell;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
