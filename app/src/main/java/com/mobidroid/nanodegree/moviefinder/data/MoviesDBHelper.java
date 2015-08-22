package com.mobidroid.nanodegree.moviefinder.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hasan on 8/6/2015.
 */
public class MoviesDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "movie_finder.db";

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MoviesContract.MovieEntry.TABLE_NAME + " (" +
                        MoviesContract.MovieEntry._ID + " INTEGER PRIMARY KEY," +
                        MoviesContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER UNIQUE NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_IS_ADULT + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_BACK_DROP_PATH + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL," +
                        MoviesContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_POPULARITY + " REAL NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_IS_VIDEO + " TEXT NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_VOTE_COUNT + " INTEGER NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_DATE + " INTEGER NOT NULL, " +
                        MoviesContract.MovieEntry.COLUMN_RUNTIME + " REAL, " +
                        MoviesContract.MovieEntry.COLUMN_STATUS + " TEXT " +
                        " );";

            final String SQL_CREATE_TRAILER_TABLE = "CREATE TABLE " + MoviesContract.MovieTrailerEntry.TABLE_NAME + " (" +
                        MoviesContract.MovieTrailerEntry._ID + " INTEGER PRIMARY KEY," +
                        MoviesContract.MovieTrailerEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_TRAILER_ID + " TEXT NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_ISO_369_1 + " TEXT NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_KEY + " TEXT NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_SITE + " TEXT NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_SIZE + " TEXT NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                        MoviesContract.MovieTrailerEntry.COLUMN_DATE + " INTEGER NOT NULL, " +
                        // Set up the movie_id column as a foreign key to movie table.
                        " FOREIGN KEY (" + MoviesContract.MovieTrailerEntry.COLUMN_MOVIE_ID + ") REFERENCES " +
                        MoviesContract.MovieEntry.TABLE_NAME + " (" + MoviesContract.MovieEntry.COLUMN_MOVIE_ID + ") " +

                        " UNIQUE (" + MoviesContract.MovieTrailerEntry.COLUMN_MOVIE_ID + ", " +
                        MoviesContract.MovieTrailerEntry.COLUMN_TRAILER_ID + ") ON CONFLICT REPLACE);";


            final String SQL_CREATE_REVIEW_TABLE = "CREATE TABLE " + MoviesContract.MovieReviewEntry.TABLE_NAME + " (" +
                MoviesContract.MovieReviewEntry._ID + " INTEGER PRIMARY KEY," +
                MoviesContract.MovieReviewEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                MoviesContract.MovieReviewEntry.COLUMN_REVIEW_ID + " TEXT NOT NULL, " +
                MoviesContract.MovieReviewEntry.COLUMN_AUTHOR + " TEXT NOT NULL, " +
                MoviesContract.MovieReviewEntry.COLUMN_CONTENT + " TEXT NOT NULL, " +
                MoviesContract.MovieReviewEntry.COLUMN_URL + " TEXT NOT NULL, " +
                MoviesContract.MovieReviewEntry.COLUMN_DATE + " INTEGER NOT NULL, " +

                // Set up the movie_id column as a foreign key to movie table.
                " FOREIGN KEY (" + MoviesContract.MovieReviewEntry.COLUMN_MOVIE_ID + ") REFERENCES " +
                MoviesContract.MovieEntry.TABLE_NAME + " (" + MoviesContract.MovieEntry.COLUMN_MOVIE_ID + ") " +

                " UNIQUE (" + MoviesContract.MovieReviewEntry.COLUMN_MOVIE_ID + ", " +
                MoviesContract.MovieReviewEntry.COLUMN_REVIEW_ID + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRAILER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_REVIEW_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieTrailerEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieReviewEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
