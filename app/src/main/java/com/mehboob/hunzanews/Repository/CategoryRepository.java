package com.mehboob.hunzanews.Repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;


import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.mehboob.hunzanews.models.allarticles.CategoryItem;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Response;


public class CategoryRepository {


    private Application application;
    private MutableLiveData<List<CategoryItem>> worldNews;
    private MutableLiveData<List<CategoryItem>> sports;
    private MutableLiveData<List<CategoryItem>> national;
    private MutableLiveData<List<CategoryItem>> entertainment;
    private MutableLiveData<List<CategoryItem>> hunza;
    private MutableLiveData<List<CategoryItem>> GB;

    private MutableLiveData<List<CategoryItem>> pakistan;
    private MutableLiveData<List<CategoryItem>> health;

    ;

    private static final String TAG = "VolleyRequestExample";

    private final String baseUrl = "https://hunzanews.net/wp-json/news-api/category/?category=";


    public CategoryRepository(Application application) {

        this.application = application;

        worldNews = new MutableLiveData<>();
        sports = new MutableLiveData<>();
        national = new MutableLiveData<>();
        entertainment = new MutableLiveData<>();
        hunza = new MutableLiveData<>();
        GB = new MutableLiveData<>();

pakistan= new MutableLiveData<>();

health=  new MutableLiveData<>();

    }


    public MutableLiveData<List<CategoryItem>> getWorldNews() {
        return worldNews;
    }

    public MutableLiveData<List<CategoryItem>> getSports() {
        return sports;
    }

    public MutableLiveData<List<CategoryItem>> getNational() {
        return national;
    }

    public MutableLiveData<List<CategoryItem>> getEntertainment() {
        return entertainment;
    }

    public MutableLiveData<List<CategoryItem>> getHunza() {
        return hunza;
    }

    public MutableLiveData<List<CategoryItem>> getGB() {
        return GB;
    }

    public MutableLiveData<List<CategoryItem>> getPakistan() {
        return pakistan;
    }

    public MutableLiveData<List<CategoryItem>> getHealth() {
        return health;
    }

    public void makeApiRequest(String category) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(application);
        List<CategoryItem> categoryItemsList = new ArrayList<>();
        // Construct the URL
        String url = baseUrl + category;

        // Request a JSONObject response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,

                response -> {
                    try {
                        // Check if the key "sports" exists in the JSON object
                        if (response.has(category)) {
                            // Get the array associated with the key "sports"
                            JSONArray sportsArray = response.getJSONArray(category);

                            // Process each item in the sportsArray
                            for (int i = 0; i < sportsArray.length(); i++) {
                                JSONObject sportsObject = sportsArray.getJSONObject(i);

                                // Access individual fields in sportsObject
                                int postId = sportsObject.getInt("ID");
                                String postTitle = sportsObject.getString("post_title");
                                String postContent = sportsObject.getString("post_content");
                                // ... (Access other fields as needed)
                                CategoryItem categoryItem = extractCommonFields(sportsObject);
                                categoryItemsList.add(categoryItem);
                                Log.d(TAG, "onResponse - Post ID: " + postId + ", Title: " + postTitle);
                                if (category.equalsIgnoreCase("world")) {
                                    // Log or process the data as needed
                                    worldNews.setValue(categoryItemsList);
                                }else if (category.equalsIgnoreCase("Sports")){
                                    sports.setValue(categoryItemsList);
                                } else if (category.equalsIgnoreCase("national")) {
                                    national.setValue(categoryItemsList);
                                }else if (category.equalsIgnoreCase("Hunza")){
                                    hunza.setValue(categoryItemsList);
                                }else if (category.equalsIgnoreCase("gb")|| category.equalsIgnoreCase("gilgit") || category.equalsIgnoreCase("Gilgit Baltistan")){
                                    GB.setValue(categoryItemsList);

                                }else if (category.equalsIgnoreCase("entertainment")){
                                    entertainment.setValue(categoryItemsList);
                                }else if (category.equalsIgnoreCase("pakistan")){
                                    pakistan.setValue(categoryItemsList);
                                }else if (category.equalsIgnoreCase("health")){
                                    health.setValue(categoryItemsList);
                                }


                                // Example: Display post content in a TextView
                                // textView.setText(postContent);
                            }

                        } else {
                            Log.d(TAG, "onResponse: Key " + category + " not found in the JSON response");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "onResponse: Error parsing JSON", e);
                    }

                },
                error -> Log.e(TAG, "onErrorResponse: " + error.toString()));


        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    // Helper method to extract common fields
    private CategoryItem extractCommonFields(JSONObject sportsObject) throws JSONException {
        int postId = sportsObject.getInt("ID");

        String postAuthour = sportsObject.getString("post_author");
        String postDate = sportsObject.getString("post_date");
        String postContent = sportsObject.getString("post_content");
        String postTitle = sportsObject.getString("post_title");

        String postExcerpt = sportsObject.getString("post_excerpt");
        String postSatus = sportsObject.getString("post_status");
        String postGuid = sportsObject.getString("guid");
        String post_typ = sportsObject.getString("post_type");
        String postThumbnail = sportsObject.getString("thumbnail_url");

        // Create and return a CategoryItem object with common fields
        return new CategoryItem(postId, postAuthour, postDate, postContent, postTitle, postExcerpt, postSatus, postThumbnail, postGuid, post_typ);
    }


}
