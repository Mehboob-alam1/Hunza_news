package com.mehboob.hunzanews.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mehboob.hunzanews.Repository.NewsRepository;
import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.network.NewsApiService;
import com.mehboob.hunzanews.utils.SessionManager;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;
    private LiveData<List<NewsItem>> allNewsLiveData;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    // Expose a LiveData for observing loading state
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
    public NewsViewModel(Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        allNewsLiveData = newsRepository.getGetArticles();



    }







    public void loadNextPage(int perPage) {
        isLoading.postValue(true);

        // Your data loading logic goes here


        newsRepository.getFromApi(perPage);
         // Increment the page in the repository
        isLoading.postValue(false);
    }

    public LiveData<List<NewsItem>> getAllNews() {
        return allNewsLiveData;
    }


}