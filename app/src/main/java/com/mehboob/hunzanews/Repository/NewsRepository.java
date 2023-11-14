package com.mehboob.hunzanews.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.mehboob.hunzanews.Database.NewsDatabase;
import com.mehboob.hunzanews.dao.NewsItemDao;
import com.mehboob.hunzanews.models.allarticles.ApiResponse;
import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.network.ApiClient;
import com.mehboob.hunzanews.network.NewsApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {


    private NewsDatabase newsDatabase;
    private Application application;
    private LiveData<List<NewsItem>> getArticles;
    private NewsApiService apiService;
    List<NewsItem> newsItems;
    public NewsRepository(Application application) {
        this.application = application;
        newsDatabase = NewsDatabase.getInstance(application);
        getArticles = newsDatabase.newsItemDao().getAllNewsItems();
        this.apiService = ApiClient.getClient().create(NewsApiService.class);
    }

    public void insert(List<NewsItem> newsItemList) {
        new InsertAsyncTask(newsDatabase).execute(newsItemList);
    }

    public void initApi(NewsApiService newsApiService) {
        this.apiService = newsApiService;
    }

    public LiveData<List<NewsItem>> getGetArticles() {
        return getArticles;
    }

    public void loadNextPage(int perPage,int page) {
        Log.d("NewsRepository", "Loading page: " + page +"Per page: " + perPage);
        Call<ApiResponse> call = apiService.getAllNews(perPage, page);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    List<NewsItem> newsItems = response.body().getAllNews();
                    Log.d("NewsRepository", "API Response for page " + page + ": " + newsItems);
                    if (newsItems != null && !newsItems.isEmpty()) {
                        // If there are new items, update the database
                        insert(newsItems);

                        // Increment the page number for the next request
                       // loadNextPage(perPage, page + 1);
                    }
                } else {
                    // Handle error
                    Toast.makeText(application, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure
                Toast.makeText(application, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class InsertAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {
        private NewsItemDao newsItemDao;

        InsertAsyncTask(NewsDatabase newsDatabase) {
            newsItemDao = newsDatabase.newsItemDao();
        }

        @Override
        protected Void doInBackground(List<NewsItem>... lists) {
            Log.d("InsertAsyncTask", "Inserting data: " + lists[0]);

            newsItemDao.insertNewsItems(lists[0]);
            return null;
        }
    }
}