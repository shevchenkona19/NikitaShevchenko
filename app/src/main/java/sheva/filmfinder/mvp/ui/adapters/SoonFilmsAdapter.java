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
import sheva.filmfinder.mvp.model.entities.soon.Result;


public class SoonFilmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<sheva.filmfinder.mvp.model.entities.soon.Result> list;
    private Context context;
    private LayoutInflater inflater;
    private int BviewType = IConstants.viewType.CARDS;

    public SoonFilmsAdapter(Context context) {
        this.context = context;
        inflater =LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (BviewType) {
            case IConstants.viewType.CARDS:
                v = inflater.inflate(R.layout.item_soon_film, parent, false);
                return new SoonCardViewHolder(v);
            case IConstants.viewType.GRID:
                v = inflater.inflate(R.layout.item_soon_film_grid, parent, false);
                return new SoonGridViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SoonCardViewHolder){
            SoonCardViewHolder h = (SoonCardViewHolder) holder;
            h.tvCardFilmName.setText(list.get(position).getName());
            if (list.get(position).getRejisser().indexOf('>') != -1) {
                h.tvCardDirectorName.setText(list.get(position).getRejisser()
                        .substring(list.get(position).getRejisser().indexOf('>')+1,
                                list.get(position).getRejisser().length()-4));
            } else {
                h.tvCardDirectorName.setText(list.get(position).getRejisser());
            }
            h.tvCardTimer.setText(list.get(position).getBefore());
            Picasso.with(context)
                    .load(IConstants.BASE_URL + list.get(position).getImage().replaceFirst("sm", "bp"))
                    .into(h.ivCardPoster);
        } else if (holder instanceof SoonGridViewHolder){
            SoonGridViewHolder h = (SoonGridViewHolder) holder;
            if (list.get(position).getName().length() > 25){
                h.tvGridFilmName.setText(list.get(position).getName().substring(0 , 25) + "...");
            }else {
                h.tvGridFilmName.setText(list.get(position).getName());
            }
            h.tvGridTimer.setText(list.get(position).getBefore());
            Picasso.with(context)
                    .load(IConstants.BASE_URL + list.get(position).getImage().replaceFirst("sm", "bp"))
                    .into(h.ivGridPoster);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setViewType(int viewType){
        BviewType = viewType;
    }

    public void onUpdate(List<Result> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    static class SoonCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivSoonPoster)
        ImageView ivCardPoster;
        @BindView(R.id.tvSoonDirectorName)
        TextView tvCardDirectorName;
        @BindView(R.id.tvSoonFilmName)
        TextView tvCardFilmName;
        @BindView(R.id.tvSoonTimer)
        TextView tvCardTimer;

        SoonCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class SoonGridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivItemSoonGridPoster)
        ImageView ivGridPoster;
        @BindView(R.id.tvItemSoonGridFilmName)
        TextView tvGridFilmName;
        @BindView(R.id.tvSoonGridTimer)
        TextView tvGridTimer;

        SoonGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
