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

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;
    private LiveData<List<NewsItem>> allNewsLiveData;
    private MutableLiveData<Integer> currentPageLiveData;


    public NewsViewModel(Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        allNewsLiveData = newsRepository.getGetArticles();

    }

    public LiveData<Integer> getCurrentPage() {
        return currentPageLiveData;
    }

    public void setCurrentPageLiveData(MutableLiveData<Integer> currentPageLiveData) {
        this.currentPageLiveData = currentPageLiveData;
    }

    public void loadNextPage(int perPage,int currentPage) {
        newsRepository.loadNextPage(perPage,currentPage);
//        newsRepository.updatePage(currentPage);
//        currentPageLiveData.setValue(newsRepository.getCurrentPage());
    }

    public LiveData<List<NewsItem>> getAllNews() {
        return allNewsLiveData;
    }


}