package com.udacity.classroom.yongchun.tvshow.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemFeedBinding;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemNetworkStateBinding;
import com.udacity.classroom.yongchun.tvshow.model.NetworkState;
import com.udacity.classroom.yongchun.tvshow.model.Popular;

public class FeedListAdapter extends PagedListAdapter<Popular, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;
    private Context mContext;
    private NetworkState mNetworkState;

    public FeedListAdapter(Context context) {
        super(new PopularDiffCallback());
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_PROGRESS:
                ItemNetworkStateBinding stateBinding = ItemNetworkStateBinding.inflate(layoutInflater, parent, false);
                return new NetworkStateItemViewHolder(stateBinding);
            case TYPE_ITEM:
                ItemFeedBinding feedBinding = ItemFeedBinding.inflate(layoutInflater, parent, false);
                return new PopularItemViewHolder(mContext, feedBinding);
            default:
                throw new IllegalArgumentException(mContext.getString(R.string.view_type_error) + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PopularItemViewHolder) {
            ((PopularItemViewHolder) holder).bindView(getItem(position));
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(mNetworkState);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasExtraRow() ? 1: 0);
    }

    private boolean hasExtraRow() {
        return mNetworkState != null && mNetworkState != NetworkState.LOADED;
    }

    public void setNetworkState(NetworkState networkState) {
        NetworkState previousState = mNetworkState;
        boolean previousRow = hasExtraRow();
        mNetworkState = networkState;
        boolean currentRow = hasExtraRow();

        if (previousRow != currentRow) {
            if (previousRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (currentRow && previousState != networkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }
}
