package sheva.newsprovider.mvp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.IConstants;
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.Article;
import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.model.entities.WeatherEntity;
import sheva.newsprovider.mvp.ui.activities.NewsItem;
import sheva.newsprovider.utils.PositionUtils;

public class FeedFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<List<Article>> lists;
    private WeatherEntity weatherEnt;
    private List<Interest> interestList;
    private int lastPosition;

    private Context context;
    private LayoutInflater inflater;

    public interface ILaunchFragment {
        void launchFragment(String name, String title);

        void showSnackbarError(String errorText);
    }

    public interface IUpdateList {
        void getArticlesForInterest(List<Interest> interests);

        void getWeather();

        void hideRefresh();

        void addToFavorite(Article article);

        void deleteLastFromDB();
    }

    private ILaunchFragment launchFragment;
    private IUpdateList iUpdateList;

    public FeedFragmentAdapter(Context context,
                               ILaunchFragment fragment,
                               IUpdateList updateList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        lists = new ArrayList<>();
        launchFragment = fragment;
        iUpdateList = updateList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case IConstants.Feed.TYPE_WEATHER:
                View v = inflater.inflate(R.layout.item_feed_weather, parent, false);
                return new ItemWeatherViewHolder(v);
            case IConstants.Feed.TYPE_HEADER:
                View v1 = inflater.inflate(R.layout.item_feed_header, parent, false);
                return new ItemHeaderViewHolder(v1);
            case IConstants.Feed.TYPE_NEWS:
                View v2 = inflater.inflate(R.layout.item_feed_news, parent, false);
                return new ItemNewsViewHolder(v2);
            case IConstants.Feed.TYPE_MORE:
                View v3 = inflater.inflate(R.layout.item_feed_more, parent, false);
                return new ItemMoreViewHolder(v3);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position == 0 && weatherEnt == null) {
            return;
        }
        if (holder instanceof ItemWeatherViewHolder) {
            ItemWeatherViewHolder weatherViewHolder = (ItemWeatherViewHolder) holder;
            weatherViewHolder.tvWeatherTemp.setText(String.valueOf(weatherEnt.getMain().getTemp().intValue()) + "°");
            weatherViewHolder.tvWeatherCityName.setText(weatherEnt.getName());
            Picasso.with(context)
                    .load(Uri.parse(IConstants.Weather.BASE_IMG_URL +
                            weatherEnt.getWeather().get(0).getIcon() +
                            IConstants.Weather.PNG))
                    .into(weatherViewHolder.ivWeatherIMG);
        } else if (holder instanceof ItemHeaderViewHolder) {
            ItemHeaderViewHolder headerViewHolder = (ItemHeaderViewHolder) holder;
            headerViewHolder.tvHeader.setText(lists.get(position / 5).get(0).getInterestName());
        } else if (holder instanceof ItemNewsViewHolder) {
            ItemNewsViewHolder newsViewHolder = (ItemNewsViewHolder) holder;
            int resolved[] = PositionUtils.resolvePosition(position);
            final Article article = lists.get(resolved[0]).get(resolved[1]);
            newsViewHolder.tvNewsHeader.setText(article.getTitle());
            newsViewHolder.btnView.setOnClickListener(view -> {
                Intent intent = new Intent(context, NewsItem.class);
                intent.putExtra("OBJECT", article);
                context.startActivity(intent);
            });
            newsViewHolder.btnFavorite.setOnClickListener(view -> {
                if (!newsViewHolder.isFavorite) {
                    newsViewHolder.isFavorite = true;
                    iUpdateList.addToFavorite(article);
                    newsViewHolder.btnFavorite
                            .setImageDrawable(
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_star_black_24dp));
                } else {
                    newsViewHolder.isFavorite = false;
                    iUpdateList.deleteLastFromDB();
                    newsViewHolder.btnFavorite
                            .setImageDrawable(
                                    context.getResources()
                                            .getDrawable(R.drawable.ic_star_border_black_24dp));
                }
            });
            Picasso.with(context)
                    .load(article.getUrlToImage())
                    .into(newsViewHolder.ivNewsIMG);
        } else if (holder instanceof ItemMoreViewHolder) {
            final int pos = position;
            ItemMoreViewHolder moreViewHolder = (ItemMoreViewHolder) holder;
            moreViewHolder.btnMore.setOnClickListener(view -> launchFragment.launchFragment(lists.get((pos-4)/5).get(0).getInterestName(),
                    lists.get((pos-4)/5).get(0).getInterestName()));
        }
        addAnimation(holder, position);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return 1 + (lists.size() * 5);
    }

    public void updateListWithWeather(List<WeatherEntity> weatherEntityList) {
        weatherEnt = weatherEntityList.get(0);
    }

    private void updateListForInterest() {
        iUpdateList.getArticlesForInterest(interestList);
    }

    public void updateListWithArticles(List<List<Article>> articles) {
        lists = articles;
        notifyDataSetChanged();
        iUpdateList.hideRefresh();
    }

    public void setInterestList(List<Interest> interestList1) {
        if (interestList1 == null || interestList1.size() == 0) {
            launchFragment.showSnackbarError("You haven't set any interests");
        }
        interestList = interestList1;
    }

    public void updateAllList() {
        if (interestList == null || interestList.size() == 0) {
            iUpdateList.hideRefresh();
            launchFragment.showSnackbarError("You haven't set any interests");
        }
        lists.clear();
        iUpdateList.getWeather();
        updateListForInterest();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IConstants.Feed.TYPE_WEATHER;
        } else if (String.valueOf(position).endsWith("6") ||
                String.valueOf(position).endsWith("1")) {
            return IConstants.Feed.TYPE_HEADER;
        } else if (String.valueOf(position).endsWith("2") ||
                String.valueOf(position).endsWith("3") ||
                String.valueOf(position).endsWith("4") ||
                String.valueOf(position).endsWith("7") ||
                String.valueOf(position).endsWith("8") ||
                String.valueOf(position).endsWith("9")) {
            return IConstants.Feed.TYPE_NEWS;
        } else {
            return IConstants.Feed.TYPE_MORE;
        }
    }

    private void addAnimation(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.from_bottom : R.anim.from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }



    static class ItemWeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivItemFeedWeatherIMG)
        ImageView ivWeatherIMG;
        @BindView(R.id.tvItemFeedWeatherCityName)
        TextView tvWeatherCityName;
        @BindView(R.id.tvItemFeedWeatherTemp)
        TextView tvWeatherTemp;

        ItemWeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemFeedHeader)
        TextView tvHeader;

        ItemHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemNewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivItemFeedNewsIMG)
        ImageView ivNewsIMG;
        @BindView(R.id.tvItemFeedNewsHeader)
        TextView tvNewsHeader;
        @BindView(R.id.btnFeedNewsView)
        ImageButton btnView;
        @BindView(R.id.ibFeedNewsFavorite)
        ImageButton btnFavorite;
        boolean isFavorite = false;

        ItemNewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemMoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btnItemFeedMore)
        Button btnMore;

        ItemMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}