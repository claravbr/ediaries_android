package com.uclm.louise.ediaries.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.uclm.louise.ediaries.R;

public class SessionManager {
    private SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static final String TOKEN = "token";

    /**
     * Function to save auth token
     */
    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    /**
     * Function to fetch auth token
     */
    public String fetchAuthToken() {
        return prefs.getString(TOKEN, null);
    }
}
