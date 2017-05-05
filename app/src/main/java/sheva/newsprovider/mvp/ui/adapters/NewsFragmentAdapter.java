package sheva.newsprovider.mvp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.Article;

public class NewsFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Article> list;
    private LayoutInflater inflater;
    private Context context;
    private int lastPosition;

    public interface IAdapterUtils {
        void addToFavorite(Article article);

        void deleteLast();
    }

    private IAdapterUtils utils;

    public NewsFragmentAdapter(Context context, IAdapterUtils utils) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        list = new ArrayList<>();
        this.utils = utils;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View v = inflater.inflate(R.layout.item_news_first, parent, false);
                return new NewsItemFirstViewHolder(v);
            default:
                View v1 = inflater.inflate(R.layout.item_news_each, parent, false);
                return new NewsEachItemViewHolder(v1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsItemFirstViewHolder) {
            NewsItemFirstViewHolder viewHolder = (NewsItemFirstViewHolder) holder;
            viewHolder.tvName.setText(list.get(position).getTitle());
            Picasso.with(context)
                    .load(list.get(position).getUrlToImage())
                    .into(viewHolder.ivHeader);
            viewHolder.btnFavorite.setOnClickListener(view -> {
                if (!viewHolder.isFavorite) {
                    viewHolder.isFavorite = true;
                    utils.addToFavorite(list.get(position));
                    viewHolder.btnFavorite
                            .setImageDrawable(
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_star_black_24dp));
                } else {
                    viewHolder.isFavorite = false;
                    utils.deleteLast();
                    viewHolder.btnFavorite
                            .setImageDrawable(
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_star_border_black_24dp));
                }
            });
        } else {
            NewsEachItemViewHolder viewHolder = (NewsEachItemViewHolder) holder;
            viewHolder.tvName.setText(list.get(position).getTitle());
            Picasso.with(context)
                    .load(list.get(position).getUrlToImage())
                    .into(viewHolder.ivPhoto);
            viewHolder.btnFavorite.setOnClickListener(view -> {
                if (!viewHolder.isFavorite) {
                    viewHolder.isFavorite = true;
                    utils.addToFavorite(list.get(position));
                    viewHolder.btnFavorite
                            .setImageDrawable(
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_star_black_24dp));
                } else {
                    viewHolder.isFavorite = false;
                    utils.deleteLast();
                    viewHolder.btnFavorite
                            .setImageDrawable(
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_star_border_black_24dp));
                }
            });
        }
        addAnimation(holder, position);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    private void addAnimation(RecyclerView.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.from_bottom : R.anim.from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Article> getList() {
        return list;
    }

    public void updateList(List<Article> articles) {
        list.clear();
        list.addAll(articles);
        notifyDataSetChanged();
    }

    static class NewsItemFirstViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivNewsFirstHeaderIMG)
        ImageView ivHeader;
        @BindView(R.id.tvNewsFirstName)
        TextView tvName;
        @BindView(R.id.ibNewsFirstFavorite)
        ImageButton btnFavorite;
        private boolean isFavorite = false;

        NewsItemFirstViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class NewsEachItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivNewsEachPhoto)
        ImageView ivPhoto;
        @BindView(R.id.tvNewsEachName)
        TextView tvName;
        @BindView(R.id.ibNewsEachFavorite)
        ImageButton btnFavorite;
        private boolean isFavorite = false;

        NewsEachItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
