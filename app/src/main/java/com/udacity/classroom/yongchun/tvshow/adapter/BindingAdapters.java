package com.udacity.classroom.yongchun.tvshow.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.classroom.yongchun.tvshow.utilities.NetworkUtils;

public class BindingAdapters {

    @BindingAdapter(value = {"posterUrl", "placeholder", "error"})
    public static void setPosterUrl(ImageView imageView, String posterUrl, Drawable placeHolder, Drawable error) {
        if (TextUtils.isEmpty(posterUrl)) {
            posterUrl = null;
        } else {
            posterUrl = NetworkUtils.buildPosterUri(posterUrl).toString();
        }

        Picasso.get()
                .load(posterUrl)
                .placeholder(placeHolder)
                .error(error)
                .into(imageView);
    }

    @BindingAdapter(value = {"backdropUrl", "placeholder", "error"})
    public static void setBackdropUrl(ImageView imageView, String backdropUrl, Drawable placeHolder, Drawable error) {
        if (TextUtils.isEmpty(backdropUrl)) {
            backdropUrl = null;
        } else {
            backdropUrl = NetworkUtils.buildBackdropUri(backdropUrl).toString();
        }

        Picasso.get()
                .load(backdropUrl)
                .placeholder(placeHolder)
                .error(error)
                .into(imageView);
    }
}
