package com.mehboob.hunzanews.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mehboob.hunzanews.models.allarticles.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LatestNewsRepository {

    private Application application;

    private MutableLiveData<List<NewsItem>> latestNews;
    private final String BASE_URL = "https://hunzanews.net/wp-json/news-api/latest-news/";

    public LatestNewsRepository(Application application){
        this.application = application;
        latestNews = new MutableLiveData<>();
    }

    public MutableLiveData<List<NewsItem>> getLatestNews() {
        return latestNews;
    }

    public void makeApiRequest() {
        RequestQueue queue = Volley.newRequestQueue(application);
        List<NewsItem> newsItemList = new ArrayList<>();

        // Form the complete URL for your request.
        String url = BASE_URL;

        // Request a JSON array response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the response.
                        Log.d("VolleyResponse", response.toString());

                        try {
                            // Deserialize the JSON array using Gson
                            Gson gson = new Gson();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                NewsItem newsItem = gson.fromJson(jsonObject.toString(), NewsItem.class);
                                newsItemList.add(newsItem);
                            }

                            // Set the value of MutableLiveData with the list of NewsItems
                            latestNews.setValue(newsItemList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("VolleyResponse", "Error parsing JSON array", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors.
                        Log.e("VolleyResponse", "Error occurred", error);
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }
}
