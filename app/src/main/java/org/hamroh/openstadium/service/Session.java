package org.hamroh.openstadium.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.hamroh.openstadium.ui.login.LoginActivity;

public class Session {

    private final SharedPreferences shp;

    public Session(Context context) {
        shp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void logIn(String phone) {
        shp.edit().putString("phone", phone).apply();
        shp.edit().putBoolean("isLoggedIn", true).apply();
    }

    public void logOut(Activity activity) {
        shp.edit().clear().apply();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    public boolean isLoggedIn() {
        return shp.getBoolean("isLoggedIn", false);
    }

}
