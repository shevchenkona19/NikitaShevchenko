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
import sheva.filmfinder.mvp.model.entities.kinoload.Result;
import sheva.filmfinder.utils.StringUtils;


public class GoingFilmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Result> list;
    private Context context;
    private LayoutInflater inflater;
    private int showType;

    public GoingFilmsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
        showType = IConstants.viewType.CARDS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (showType) {
            case IConstants.viewType.CARDS:
                v = inflater.inflate(R.layout.item_film, parent, false);
                return new FilmCardViewHolder(v);
            case IConstants.viewType.GRID:
                v = inflater.inflate(R.layout.item_film_grid, parent, false);
                return new FilmGridViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FilmCardViewHolder) {
            FilmCardViewHolder v = (FilmCardViewHolder) holder;
            v.tvFilmName.setText(list.get(position).getName());
            v.tvDirectorName.setText(StringUtils.getDirector(list.get(position).getRejisser()));
            v.tvRating.setText(list.get(position).getVote());
            Picasso.with(context)
                    .load(IConstants.BASE_URL + list.get(position).getImage())
                    .into(v.ivPoster);
        } else if (holder instanceof FilmGridViewHolder) {
            FilmGridViewHolder v = (FilmGridViewHolder) holder;
            if (list.get(position).getName().length() > 22){
                v.tvGridName.setText(list.get(position).getName().substring(0 , 22) + "...");
            } else {
                v.tvGridName.setText(list.get(position).getName());
            }
            Picasso.with(context)
                    .load(IConstants.BASE_URL + list.get(position).getImage().replaceFirst("sm", "bp"))
                    .into(v.ivGridPoster);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Result> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setShowType(int showType) {
        this.showType = showType;
        updateList(new ArrayList<>(list));
    }

    public int getShowType() {
        return showType;
    }

    public Result getItemAtPosition(int position) {
        return list.get(position);
    }

    static class FilmCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivItemFilmPoster)
        ImageView ivPoster;
        @BindView(R.id.tvItemFilmName)
        TextView tvFilmName;
        @BindView(R.id.tvItemDirectorName)
        TextView tvDirectorName;
        @BindView(R.id.tvItemRating)
        TextView tvRating;

        FilmCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FilmGridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemGridFilmName)
        TextView tvGridName;
        @BindView(R.id.ivItemGridPoster)
        ImageView ivGridPoster;

        FilmGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
