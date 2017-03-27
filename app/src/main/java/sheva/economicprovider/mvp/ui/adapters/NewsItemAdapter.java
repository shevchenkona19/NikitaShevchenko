package sheva.economicprovider.mvp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.economicprovider.R;
import sheva.economicprovider.mvp.model.entities.Article;

/**
 * Created by shevc on 26.03.2017.
 */

public class NewsItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Article> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private int lastPosition;

    public NewsItemAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = layoutInflater.inflate(R.layout.news_item_first, parent, false);
            return new FirstNewsItemViewHolder(v);
        } else {
            View v = layoutInflater.inflate(R.layout.news_each_item, parent, false);
            return new NewsItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FirstNewsItemViewHolder) {
            FirstNewsItemViewHolder firstNewsItemViewHolder = (FirstNewsItemViewHolder) holder;
            firstNewsItemViewHolder.tvTitle.setText(list.get(position).getTitle());
            Picasso.with(context)
                    .load(list.get(position).getUrlToImage())
                    .resize(350, 200)
                    .centerCrop()
                    .into(firstNewsItemViewHolder.imageView);
        } else {
            NewsItemViewHolder newsItemViewHolder = (NewsItemViewHolder) holder;
            newsItemViewHolder.tvTitle.setText(list.get(position).getTitle());
            newsItemViewHolder.tvDate.setText(list.get(position).getPublishedAt().substring(0, 9));
            Picasso.with(context)
                    .load(list.get(position).getUrlToImage())
                    .resize(120, 70)
                    .centerCrop()
                    .into(newsItemViewHolder.imageView);
        }
        addAnimation(holder, position);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    private void addAnimation(RecyclerView.ViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.from_bottom : R.anim.from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Article getItemAtPosition(int position){
        return list.get(position);
    }

    public void addList(List<Article> list) {
        Log.d("MY", "addList");
       this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    static class FirstNewsItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNewsListTitle)
        TextView tvTitle;
        @BindView(R.id.ivFirstImage)
        ImageView imageView;

        public FirstNewsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class NewsItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivFirstImage)
        ImageView imageView;
        @BindView(R.id.tvNewsListTitle)
        TextView tvTitle;
        @BindView(R.id.tvDate)
        TextView tvDate;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
