package com.mobidroid.nanodegree.moviefinder.adapter;

import android.content.Context;
import android.database.Cursor;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobidroid.nanodegree.moviefinder.MovieListFragment;
import com.mobidroid.nanodegree.moviefinder.R;
import com.mobidroid.nanodegree.moviefinder.utility.Utility;
import com.squareup.picasso.Picasso;

/**
 * Created by hasan on 8/6/2015.
 */
public class GridViewMoviesAdapter extends CursorAdapter {

    public GridViewMoviesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String posterPath = cursor.getString(MovieListFragment.COL_POSTER_PATH);
        String movieName = cursor.getString(MovieListFragment.COL_ORIGINAL_TITLE);
        int rating = cursor.getInt(MovieListFragment.COL_VOTE_AVERAGE);
        if (posterPath == null) {
            posterPath = cursor.getString(MovieListFragment.COL_BACK_DROP_PATH);
        }

        Glide.with(context).load(Utility.getImageURL(posterPath,"w185")).into(viewHolder.imageView);
        viewHolder.movieName.setText(movieName);
        viewHolder.ratingBar.setRating(Float.valueOf(rating)/2);
    }

    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView movieName;
        public final RatingBar ratingBar;

        public ViewHolder(View vi) {
            imageView = (ImageView) vi.findViewById(R.id.imageView);
            movieName = (TextView) vi.findViewById(R.id.movieName);
            ratingBar = (RatingBar) vi.findViewById(R.id.ratingBar);
        }
    }
}
