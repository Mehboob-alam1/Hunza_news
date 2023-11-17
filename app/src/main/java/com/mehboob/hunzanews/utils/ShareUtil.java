package com.mehboob.hunzanews.utils;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ShareUtil {


    public static void shareLink(String link, Context context){
        Intent intentt = new Intent(Intent.ACTION_SEND);

        // setting type of data shared as text
        intentt.setType("text/plain");
        intentt.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");

        // Adding the text to share using putExtra
        intentt.putExtra(Intent.EXTRA_TEXT, link);
        context.startActivity(Intent.createChooser(intentt, "Share Via"));
    }
}
