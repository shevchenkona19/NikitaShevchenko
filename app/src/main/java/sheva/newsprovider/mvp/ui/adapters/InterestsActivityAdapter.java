package sheva.newsprovider.mvp.ui.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsprovider.R;

public class InterestsActivityAdapter extends RecyclerView.Adapter<InterestsActivityAdapter.InterestViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private boolean[] interests;

    public InterestsActivityAdapter(Context context) {
        this.context = context;
        interests = new boolean[12];
        inflater = LayoutInflater.from(context);
    }

    @Override
    public InterestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_interest, parent, false);
        return new InterestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InterestViewHolder holder, int position) {
        Resources resources = context.getResources();
        switch (position) {
            case 0:
                holder.tvInterestName.setText(R.string.news);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.news)
                                + '/' + resources.getResourceTypeName(R.drawable.news)
                                + '/' + resources.getResourceEntryName(R.drawable.news)))
                        .into(holder.ivImg);
                break;
            case 1:
                holder.tvInterestName.setText(R.string.tech);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.tech)
                                + '/' + resources.getResourceTypeName(R.drawable.tech)
                                + '/' + resources.getResourceEntryName(R.drawable.tech)))
                        .into(holder.ivImg);
                break;
            case 2:
                holder.tvInterestName.setText(R.string.sport);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.sports)
                                + '/' + resources.getResourceTypeName(R.drawable.sports)
                                + '/' + resources.getResourceEntryName(R.drawable.sports)))
                        .into(holder.ivImg);
                break;
            case 3:
                holder.tvInterestName.setText(R.string.business);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.business)
                                + '/' + resources.getResourceTypeName(R.drawable.business)
                                + '/' + resources.getResourceEntryName(R.drawable.business)))
                        .into(holder.ivImg);
                break;
            case 4:
                holder.tvInterestName.setText(R.string.entertainment);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.entertainment)
                                + '/' + resources.getResourceTypeName(R.drawable.entertainment)
                                + '/' + resources.getResourceEntryName(R.drawable.entertainment)))
                        .into(holder.ivImg);
                break;
            case 5:
                holder.tvInterestName.setText(R.string.finance);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.finance)
                                + '/' + resources.getResourceTypeName(R.drawable.finance)
                                + '/' + resources.getResourceEntryName(R.drawable.finance)))
                        .into(holder.ivImg);
                break;
            case 6:
                holder.tvInterestName.setText(R.string.football);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.football)
                                + '/' + resources.getResourceTypeName(R.drawable.football)
                                + '/' + resources.getResourceEntryName(R.drawable.football)))
                        .into(holder.ivImg);
                break;
            case 7:
                holder.tvInterestName.setText(R.string.games);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.games)
                                + '/' + resources.getResourceTypeName(R.drawable.games)
                                + '/' + resources.getResourceEntryName(R.drawable.games)))
                        .into(holder.ivImg);
                break;
            case 8:
                holder.tvInterestName.setText(R.string.music);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.music)
                                + '/' + resources.getResourceTypeName(R.drawable.music)
                                + '/' + resources.getResourceEntryName(R.drawable.music)))
                        .into(holder.ivImg);
                break;
            case 9:
                holder.tvInterestName.setText(R.string.geography);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.geography)
                                + '/' + resources.getResourceTypeName(R.drawable.geography)
                                + '/' + resources.getResourceEntryName(R.drawable.geography)))
                        .into(holder.ivImg);
                break;
            case 10:
                holder.tvInterestName.setText(R.string.science);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.science)
                                + '/' + resources.getResourceTypeName(R.drawable.science)
                                + '/' + resources.getResourceEntryName(R.drawable.science)))
                        .into(holder.ivImg);
                break;
            case 11:
                holder.tvInterestName.setText(R.string.politics);
                Picasso.with(context)
                        .load(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://" + resources.getResourcePackageName(R.drawable.politics)
                                + '/' + resources.getResourceTypeName(R.drawable.politics)
                                + '/' + resources.getResourceEntryName(R.drawable.politics)))
                        .into(holder.ivImg);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public boolean[] getList() {
        return interests;
    }

    static class InterestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivItemInterestImg)
        ImageView ivImg;
        @BindView(R.id.tvItemInterestName)
        TextView tvInterestName;
        @BindView(R.id.cvInterestItem)
        CardView cvItem;

        InterestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
