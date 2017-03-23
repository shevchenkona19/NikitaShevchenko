package sheva.rxcardapptrue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.rxcardapptrue.R;
import sheva.rxcardapptrue.mvp.model.entities.NewsEntity;

/**
 * Created by shevc on 22.03.2017.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<NewsEntity> list;
    private int lastPosition;

    public Adapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = layoutInflater.inflate(R.layout.first_item, parent, false);
            return new AdapterViewHolderFirst(v);
        } else {
            View v = layoutInflater.inflate(R.layout.each_item, parent, false);
            return new AdapterViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdapterViewHolder) {
            AdapterViewHolder adapterViewHolder = (AdapterViewHolder) holder;
            adapterViewHolder.tvText.setText(getList().get(position).getText());
            adapterViewHolder.tvTitle.setText(getList().get(position).getTitle());
        } else {
            AdapterViewHolderFirst viewHolder = (AdapterViewHolderFirst) holder;
            viewHolder.tvTitle.setText(getList().get(position).getTitle());
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

    public void updateList(List<NewsEntity> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public List<NewsEntity> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class AdapterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvText)
        TextView tvText;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.imageView)
        ImageView ivImage;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class AdapterViewHolderFirst extends RecyclerView.ViewHolder{
        @BindView(R.id.tvText)
        TextView tvText;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.imageView)
        ImageView ivImage;
        public AdapterViewHolderFirst(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
