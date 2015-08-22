package com.mobidroid.nanodegree.moviefinder.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobidroid.nanodegree.moviefinder.MovieDetailsActivityFragment;
import com.mobidroid.nanodegree.moviefinder.R;

/**
 * Created by hasan on 8/6/2015.
 */
public class TrailersAdapter extends CursorAdapter {

    public TrailersAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_trailers_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String trailerName = cursor.getString(MovieDetailsActivityFragment.COL_NAME);

        viewHolder.trailerNameTextView.setText(trailerName);
    }

    public static class ViewHolder {
        public final TextView trailerNameTextView;

        public ViewHolder(View vi) {
            trailerNameTextView = (TextView)vi.findViewById(R.id.textview_trailer_name);
        }
    }
}
