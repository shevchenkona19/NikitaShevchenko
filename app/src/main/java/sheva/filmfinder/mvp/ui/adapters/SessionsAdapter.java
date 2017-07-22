package sheva.filmfinder.mvp.ui.adapters;

import android.content.Context;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.filmfinder.R;
import sheva.filmfinder.mvp.model.entities.sessions.HallTimeEntity;
import sheva.filmfinder.mvp.model.entities.sessions.SessionEntity;
import sheva.filmfinder.utils.StringUtils;

/**
 * Created by shevc on 13.06.2017.
 * Let's GO!
 */

public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.SessionViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<SessionEntity> list;
    private RecyclerView recyclerView;

    public SessionsAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
        this.recyclerView = recyclerView;
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_sesssions_cinema, parent, false);
        return new SessionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SessionViewHolder holder, int position) {
        final int pos = position;
        holder.tvCienemaName.setText(list.get(position).getCinemaName());
        if (list.get(position).isExpanded()) {
            holder.tvSessionTimeHall.setVisibility(View.VISIBLE);
        } else {
            holder.tvSessionTimeHall.setVisibility(View.GONE);
        }
        StringBuilder builder = new StringBuilder();
        for (HallTimeEntity entity : list.get(pos).getList()) {
            builder.append(entity.getHallName());
            builder.append("\n");
            builder.append(StringUtils.getFromatedSessionString(entity.getSessions()));
            builder.append("\n");
        }
        holder.tvSessionTimeHall.setText(builder.toString());
        holder.ibCinemaExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(pos).isExpanded()) {
                    list.get(pos).setExpanded(false);
                    holder.tvSessionTimeHall.setVisibility(View.GONE);
                    holder.ibCinemaExpanded.setBackground(context.getResources()
                            .getDrawable(R.drawable.ic_expand_more_black_24dp));
                    TransitionManager.beginDelayedTransition(recyclerView);
                } else {
                    list.get(pos).setExpanded(true);
                    holder.tvSessionTimeHall.setVisibility(View.VISIBLE);
                    holder.ibCinemaExpanded.setBackground(context.getResources()
                            .getDrawable(R.drawable.ic_expand_less_black_24dp));
                    TransitionManager.beginDelayedTransition(recyclerView);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(pos).isExpanded()) {
                    list.get(pos).setExpanded(false);
                    holder.tvSessionTimeHall.setVisibility(View.GONE);
                    holder.ibCinemaExpanded.setBackground(context.getResources()
                            .getDrawable(R.drawable.ic_expand_more_black_24dp));
                    TransitionManager.beginDelayedTransition(recyclerView);
                } else {
                    list.get(pos).setExpanded(true);
                    holder.tvSessionTimeHall.setVisibility(View.VISIBLE);
                    holder.ibCinemaExpanded.setBackground(context.getResources()
                            .getDrawable(R.drawable.ic_expand_less_black_24dp));
                    TransitionManager.beginDelayedTransition(recyclerView);
                }
            }
        });
    }

    public void updateList(List<SessionEntity> sessionEntityList) {
        list.clear();
        list.addAll(sessionEntityList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SessionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSessionTimeHall)
        TextView tvSessionTimeHall;
        @BindView(R.id.ivSessionCinemaExpanded)
        ImageButton ibCinemaExpanded;
        @BindView(R.id.tvSessionCinemaName)
        TextView tvCienemaName;
        private View item;

        public SessionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
