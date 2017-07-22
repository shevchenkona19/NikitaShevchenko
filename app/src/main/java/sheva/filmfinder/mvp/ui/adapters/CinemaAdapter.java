package sheva.filmfinder.mvp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.filmfinder.IConstants;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.cinemas.Unmain;

/**
 * Created by shevc on 07.06.2017.
 */

public class CinemaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Unmain> list;
    private Context context;
    private LayoutInflater inflater;
    private int showType = IConstants.viewType.CARDS;

    public CinemaAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (showType) {
            case IConstants.viewType.CARDS:
                v = inflater.inflate(R.layout.item_cinema_card, parent, false);
                return new CinemaCardViewHolder(v);
            case IConstants.viewType.GRID:
                v = inflater.inflate(R.layout.item_cinema_grid, parent, false);
                return new CinemaGridViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CinemaCardViewHolder) {
            CinemaCardViewHolder h = (CinemaCardViewHolder) holder;
            h.tvCinemaCardName.setText(list.get(position).getName());
            h.tvCinemaCardAddress.setText(list.get(position).getAddress());
            Picasso.with(context)
                    .load(IConstants.BASE_URL + list.get(position).getImage())
                    .into(h.ivCinemaCardThumbnail);
        } else if (holder instanceof CinemaGridViewHolder) {
            CinemaGridViewHolder h = (CinemaGridViewHolder) holder;
            h.tvCinemaGridName.setText(list.get(position).getName());
            Picasso.with(context)
                    .load(IConstants.BASE_URL + list.get(position).getImage())
                    .into(h.ivCinemaGridThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getShowType() {
        return showType;
    }

    public Unmain getItemAtPosition(int position) {
        return list.get(position);
    }

    public void onUpdate(List<Unmain> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    static class CinemaCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCinemaThumbnail)
        ImageView ivCinemaCardThumbnail;
        @BindView(R.id.tvCinemaName)
        TextView tvCinemaCardName;
        @BindView(R.id.tvCinemaAddress)
        TextView tvCinemaCardAddress;

        public CinemaCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class CinemaGridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCinemaGridThumbnail)
        ImageView ivCinemaGridThumbnail;
        @BindView(R.id.tvCinemaGridName)
        TextView tvCinemaGridName;

        public CinemaGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
