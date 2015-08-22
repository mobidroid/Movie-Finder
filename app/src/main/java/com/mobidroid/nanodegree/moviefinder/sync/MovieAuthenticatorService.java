package com.mobidroid.nanodegree.moviefinder.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by hasan on 8/6/2015.
 */
public class MovieAuthenticatorService extends Service {

    // Instance field that stores the authenticator object
    private MovieAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new MovieAuthenticator(this);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
