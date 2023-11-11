package com.mehboob.hunzanews.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    private int currentPage = 1;
    private int perPage = 3; // Number of items to load per page
    private NewsDatabase newsDatabase;
    private Application application;
    private MutableLiveData<List<NewsItem>> newsLiveData = new MutableLiveData<>();
    private LiveData<List<NewsItem>> getArticles;
    private NewsApiService apiService;

    public NewsRepository(Application application) {
        this.application = application;

        newsDatabase = NewsDatabase.getInstance(application);

        getArticles = newsDatabase.newsItemDao().getAllNewsItems();
        this.apiService = ApiClient.getClient().create(NewsApiService.class);
    }

    public void insert(List<NewsItem> newsItemList) {
new InsertAsyncTask(newsDatabase).execute(newsItemList);
    }
public void intiApi(NewsApiService newsApiService){
    this.apiService = newsApiService;
}
    public LiveData<List<NewsItem>> getGetArticles() {
        return getArticles;
    }

    class InsertAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {
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



    public void loadNextPage() {
        Call<ApiResponse> call = apiService.getAllNews(currentPage, perPage);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    List<NewsItem> newsItems = response.body().getAllNews();
                    if (newsItems != null && !newsItems.isEmpty()) {
                        // If there are new items, update the LiveData
                        List<NewsItem> currentData = newsLiveData.getValue();

                        if (currentData == null) {
                            currentData = new ArrayList<>();
                        }
                        currentData.addAll(newsItems);
                        newsLiveData.setValue(currentData);

                        // Increment the page number for the next request
                        currentPage++;
                    }
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
