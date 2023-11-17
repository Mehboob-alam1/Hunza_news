package com.mehboob.hunzanews.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private static final String PAGE_NO="page";


    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PAGE_NO, Context.MODE_PRIVATE);


    }
    public void savePAGE(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PAGE_NO, userId);
        editor.apply();
    }

    public int getPAGE() {
        return sharedPreferences.getInt(PAGE_NO, 1);

    }
}
