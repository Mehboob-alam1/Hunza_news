package com.mehboob.hunzanews.models.allarticles;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class CommentConverter {


    private static Gson gson = new Gson();

    // TypeConverter for CommentCount to String
    @TypeConverter
    public static String fromCommentCount(CommentCount commentCount) {
        return gson.toJson(commentCount);
    }

    // TypeConverter for String to CommentCount
    @TypeConverter
    public static CommentCount toCommentCount(String json) {
        return gson.fromJson(json, CommentCount.class);
    }
}
