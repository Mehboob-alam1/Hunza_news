package com.mehboob.hunzanews.models.allarticles;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiResponse {
    @SerializedName("all-news")
    private List<NewsItem> allNews;

    public List<NewsItem> getAllNews() {
        return allNews;
    }

    // Add other methods if necessary


    public void setAllNews(List<NewsItem> allNews) {
        this.allNews = allNews;
    }
}
