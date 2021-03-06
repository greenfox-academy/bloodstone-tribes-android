package com.greenfox.tribesoflagopusandroid.fragments;


import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.greenfox.tribesoflagopusandroid.LoadingViewListener;
import com.greenfox.tribesoflagopusandroid.TribesApplication;

import javax.inject.Inject;

/**
 * Created by georgezsiga on 6/28/17.
 */

public class BaseFragment extends Fragment {

    LoadingViewListener loadingViewListener;

    @Inject
    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    public static String timestamp;

    @Override
    public void onStop() {
        super.onStop();
    }

    public void setLoadingViewListener(LoadingViewListener loadingViewListener) {
        this.loadingViewListener = loadingViewListener;
    }

    public void refreshActiveFragment() {
        if (loadingViewListener != null) {
            loadingViewListener.loadingStarted();
        }
    }

    public void saveOnExit(String fragment) {
        TribesApplication.app().basicComponent().inject(this);
        editor = preferences.edit();
        timestamp = String.valueOf(System.currentTimeMillis());
        editor.putString(fragment, timestamp);
        editor.apply();
    }
}
