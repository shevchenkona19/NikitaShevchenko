package sheva.cardapptrue;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by shevc on 09.03.2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<String> list;
    private int lastPosition;

    public RVAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = layoutInflater.inflate(R.layout.first_item, parent, false);
            return new RVAdapterViewHolderFirst(v);
        } else {
            View v = layoutInflater.inflate(R.layout.each_item, parent, false);
            return new RVAdapterViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RVAdapterViewHolder) {
            RVAdapterViewHolder rvAdapterViewHolder = (RVAdapterViewHolder) holder;
            rvAdapterViewHolder.tvDate.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
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

    public void addEntity(String entity) {
        Log.d("MY", "addEntity");
        list.add(entity);
        notifyItemInserted(getItemCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RVAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;

        public RVAdapterViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        }
    }

    static class RVAdapterViewHolderFirst extends RecyclerView.ViewHolder {

        public RVAdapterViewHolderFirst(View itemView) {
            super(itemView);
        }
    }
}
