package sheva.newsprovider.mvp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import sheva.newsprovider.R;
import sheva.newsprovider.mvp.model.entities.ArticleDB;

public class FavoriteFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<ArticleDB> articleDBList;

    public FavoriteFragmentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        articleDBList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_favorite_news, parent, false);
        return new FavoriteNewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FavoriteNewsViewHolder viewHolder = (FavoriteNewsViewHolder) holder;
        viewHolder.tvFavorite.setText(articleDBList.get(position).getTitle());
        Picasso.with(context)
                .load(articleDBList.get(position).getUrlToImage())
                .resize(100, 100)
                .centerCrop()
                .into(viewHolder.ivFavorite);
    }

    @Override
    public int getItemCount() {
        return articleDBList.size();
    }

    public void deleteAtPosition(int position) {
        articleDBList.remove(position);
        notifyItemRemoved(position);
    }

    public ArticleDB getItemAtPosition(int position) {
        Log.d("MY", "POSITION: " + position);
        if (position - 1 < 0) {
            return articleDBList.get(0);
        } else if (position >= articleDBList.size()) {
            return articleDBList.get(articleDBList.size() - 1);
        }
        return articleDBList.get(position);
    }

    public void updateListWithArticles(List<ArticleDB> articleDB) {
        articleDBList = articleDB;
        Log.d("MY", "Size: " + articleDB.size());
        notifyDataSetChanged();
    }

    static class FavoriteNewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_favorite_img)
        ImageView ivFavorite;
        @BindView(R.id.tv_favorite_title)
        TextView tvFavorite;

        FavoriteNewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}