package com.mehboob.hunzanews.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mehboob.hunzanews.Database.NewsDatabase;
import com.mehboob.hunzanews.dao.NewsItemDao;
import com.mehboob.hunzanews.models.allarticles.ApiResponse;
import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.network.ApiClient;
import com.mehboob.hunzanews.network.NewsApiService;
import com.mehboob.hunzanews.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {


    private NewsDatabase newsDatabase;
    private Application application;
    private LiveData<List<NewsItem>> getArticles;
    ;
    private NewsApiService apiService;

    private int currentPage=1;


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






    public void getFromApi(int perPage) {

        Call<ApiResponse> call = apiService.getAllNews(perPage, currentPage);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {

                    List<NewsItem> data = response.body().getAllNews();
                    insert(data);
                    // Update the current page in the SessionManager





                } else {

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

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
            newsItemDao.insertNewsItems(lists[0]);

            return null;
        }
    }
}