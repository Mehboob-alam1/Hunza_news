package com.mehboob.hunzanews.network;

import com.mehboob.hunzanews.models.allarticles.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    String BASE_URL="https://hunzanews.net/wp-json/news-api/";
    @GET("all-news")
    Call<ApiResponse> getAllNews(@Query("page") int page, @Query("per_page") int perPage);


    //Call<ApiResponse> getAllNews();


}