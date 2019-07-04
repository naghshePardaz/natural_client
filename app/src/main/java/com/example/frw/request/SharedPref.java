package com.example.frw.request;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPref {
    private static final String MY_PREF_NAME = "Authenticated";

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
    }


    public static void createSharedPref(Context context, String name, String value) {
        // Setting value in Preferences
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(name, value).apply();
    }

    public static boolean isValueExists(Context context, String name) {
        String data = getPreferences(context).getString(name, null);
        return data != null;
    }
}
