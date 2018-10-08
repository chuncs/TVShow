package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemActorBinding;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemEpisodeBinding;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemLatestBinding;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemSeasonBinding;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemSimilarBinding;
import com.udacity.classroom.yongchun.tvshow.model.Cast;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.model.Episode;
import com.udacity.classroom.yongchun.tvshow.model.Popular;
import com.udacity.classroom.yongchun.tvshow.model.Season;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_SEASON = 0;
    private static final int VIEW_TYPE_ACTOR = 1;
    private static final int VIEW_TYPE_SIMILAR = 2;
    private static final int VIEW_TYPE_EPISODE = 3;
    private static final int VIEW_TYPE_LATEST = 4;
    private Context mContext;
    private List<Season> mSeasons;
    private List<Cast> mActors;
    private List<Popular> mSimilar;
    private List<Episode> mEpisodes;
    private List<Detail> mLatest;

    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        switch (viewType) {
            case VIEW_TYPE_SEASON:
                ItemSeasonBinding seasonBinding = ItemSeasonBinding.inflate(layoutInflater, parent, false);
                return new SeasonItemViewHolder(mContext, seasonBinding);
            case VIEW_TYPE_ACTOR:
                ItemActorBinding actorBinding = ItemActorBinding.inflate(layoutInflater, parent, false);
                return new ActorItemViewHolder(actorBinding);
            case VIEW_TYPE_SIMILAR:
                ItemSimilarBinding similarBinding = ItemSimilarBinding.inflate(layoutInflater, parent, false);
                return new SimilarItemViewHolder(mContext, similarBinding);
            case VIEW_TYPE_EPISODE:
                ItemEpisodeBinding episodeBinding = ItemEpisodeBinding.inflate(layoutInflater, parent, false);
                return new EpisodeItemViewHolder(mContext, episodeBinding);
            case VIEW_TYPE_LATEST:
                ItemLatestBinding latestBinding = ItemLatestBinding.inflate(layoutInflater, parent, false);
                return new LatestItemViewHolder(mContext, latestBinding);
            default:
                throw new IllegalArgumentException(mContext.getString(R.string.view_type_error) + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SeasonItemViewHolder) {
            ((SeasonItemViewHolder) holder).bindView(mSeasons.get(position));
        } else if (holder instanceof ActorItemViewHolder) {
            ((ActorItemViewHolder) holder).bindView(mActors.get(position));
        } else if (holder instanceof SimilarItemViewHolder) {
            ((SimilarItemViewHolder) holder).bindView(mSimilar.get(position));
        } else if (holder instanceof EpisodeItemViewHolder) {
            ((EpisodeItemViewHolder) holder).bindView(mEpisodes.get(position));
        } else if (holder instanceof LatestItemViewHolder) {
            ((LatestItemViewHolder) holder).bindView(mLatest.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return getSeasonSize() + getActorSize() + getSimilarSize() + getEpisodeSize() + getLatestSize();
    }

    @Override
    public int getItemViewType(int position) {
        if (getSeasonSize() > 0) {
            return VIEW_TYPE_SEASON;
        } else if (getActorSize() > 0) {
            return VIEW_TYPE_ACTOR;
        } else if (getSimilarSize() > 0) {
            return VIEW_TYPE_SIMILAR;
        } else if (getEpisodeSize() > 0) {
            return VIEW_TYPE_EPISODE;
        } else if (getLatestSize() > 0){
            return VIEW_TYPE_LATEST;
        } else {
            return -1;
        }
    }

    private int getSeasonSize() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    private int getActorSize() {
        return mActors != null ? mActors.size() : 0;
    }

    private int getSimilarSize() {
        return mSimilar != null ? mSimilar.size() : 0;
    }

    private int getEpisodeSize() {
        return mEpisodes != null ? mEpisodes.size() : 0;
    }

    private int getLatestSize() {
        return mLatest != null ? mLatest.size() : 0;
    }

    public void setSeasons(List<Season> seasons) {
        mSeasons = seasons;
        notifyDataSetChanged();
    }

    public void setActors(List<Cast> actors) {
        mActors = actors;
        notifyDataSetChanged();
    }

    public void setSimilar(List<Popular> similar) {
        mSimilar = similar;
        notifyDataSetChanged();
    }

    public void setEpisodes(List<Episode> episodes) {
        mEpisodes = episodes;
        notifyDataSetChanged();
    }

    public void setLatest(List<Detail> latest) {
        mLatest = latest;
        notifyDataSetChanged();
    }
}
