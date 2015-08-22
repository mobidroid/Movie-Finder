package com.mobidroid.nanodegree.moviefinder.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mobidroid.nanodegree.moviefinder.MovieDetailsActivityFragment;
import com.mobidroid.nanodegree.moviefinder.R;
import com.mobidroid.nanodegree.moviefinder.sync.MovieDataLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by hasan on 8/6/2015.
 */
public class Utility {

    public static final String API_KEY = "499f158bf8fd92d83ec15bd47fb82fbc";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w342"; //TODO
    private static final String PATH_SEPARATOR = "/";



    public static String getPreferredSortOrder(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_sort_order_key),
                context.getString(R.string.pref_sort_order_default));
    }


    public static String getImageURL(String imagePath, String imageSize) {
        StringBuilder imageURL = new StringBuilder();

        imageURL.append(IMAGE_BASE_URL);
        imageURL.append(imageSize);
        imageURL.append(PATH_SEPARATOR);
        imageURL.append(imagePath);

        return imageURL.toString();
    }

    public static Integer getYearFromDate(String dateStr) { //TODO
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(Utility.DATE_FORMAT);
        try {
            Date inputDate = dbDateFormat.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(inputDate);

            return cal.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * This function is taken during search over internet
     * for making my list of items to show with individual heights
     * for 2 list views with different heights. And then it is modified
     * according to the MeasureSpec
     *
     * Original Source:
     * http://stackoverflow.com/questions/17693578/android-how-to-display-2-listviews-in-one-activity-one-after-the-other
     *
     *
     * @param mListView
     */
    public static void setDynamicHeight(ListView mListView) {
        ListAdapter mListAdapter = mListView.getAdapter();
        if (mListAdapter == null) {
            // when adapter is null
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < mListAdapter.getCount(); i++) {
            View listItem = mListAdapter.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount()));
        mListView.setLayoutParams(params);
        mListView.requestLayout();
    }

    public static boolean isStringEmpty(String string) {
        return (string == null || string.equals("null") || string.equals(""));
    }

    public static boolean isAppInstalled(String uri, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }


    public static boolean canResolveIntent(Intent intent, Context context) { //TODO
        List<ResolveInfo> resolveInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }

    public static String argsArrayToString(String[] args) {
        StringBuilder argsBuilder = new StringBuilder();

        final int argsCount = args.length;
        for (int i = 0; i < argsCount; i++) {
            argsBuilder.append(args[i]);

            if (i < argsCount - 1) {
                argsBuilder.append(",");
            }
        }

        return argsBuilder.toString();
    }

    public static boolean isMovieIdFavorite(String [] favoriteMovieIds, String movieId) {
        boolean result = false;

        if (favoriteMovieIds == null || favoriteMovieIds.length == 0) return result;

        for (int i = 0; i < favoriteMovieIds.length; i++) {
            if (movieId.trim().equals(favoriteMovieIds[i].trim())){
                result = true;
                break;
            }
        }

        return result;
    }

    public static String[] loadFavoriteMovieIds (Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> favoritMovieIdsSet =  prefs.getStringSet(MovieDetailsActivityFragment.FAVORITE_MOVIE_IDS_SET_KEY, null);

        if (favoritMovieIdsSet != null) {
            String[] array = new String[favoritMovieIdsSet.size()];

            Iterator<String> movieIdsIter = favoritMovieIdsSet.iterator();

            int i = 0;
            while (movieIdsIter.hasNext()) {
                array[i] = movieIdsIter.next();
                i = i + 1;
            }
            return array;
        }

        return null;
    }

    @SuppressWarnings("ResourceType")
    public static @MovieDataLoader.MovieStatus int getMovieStatus(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(context.getString(R.string.pref_movie_status_key), MovieDataLoader.MOVIE_STATUS_UNKNOWN);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
