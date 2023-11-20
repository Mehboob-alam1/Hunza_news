package com.mehboob.hunzanews.utils;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ShareUtil {


    public static void shareLink(String link, Context context){
        Intent intent = new Intent(Intent.ACTION_SEND);

        // setting type of data shared as text
        //
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");

        // Adding the text to share using putExtra
        intent.putExtra(Intent.EXTRA_TEXT, link);
        context.startActivity(Intent.createChooser(intent, "Share Via"));
    }
}
