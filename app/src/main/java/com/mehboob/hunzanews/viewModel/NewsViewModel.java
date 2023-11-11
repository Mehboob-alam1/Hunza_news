package com.mehboob.hunzanews.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mehboob.hunzanews.Repository.NewsRepository;
import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.network.NewsApiService;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private boolean mHasMorePages = true;

    private NewsRepository mNewsRepository;
    private Application mApplication;
    private LiveData<List<NewsItem>> mAllNewsLiveData;

    public NewsViewModel(Application application) {
        super(application);
        this.mApplication = application;
        mNewsRepository = new NewsRepository(application);
        mAllNewsLiveData = mNewsRepository.getGetArticles();
    }

    public void insert(List<NewsItem> newsItemList) {
        mNewsRepository.insert(newsItemList);
    }

    public LiveData<List<NewsItem>> getAllNews() {
        return mAllNewsLiveData;
    }

    public void loadNextPage() {
        mNewsRepository.loadNextPage();
    }

    public LiveData<Boolean> isLoading() {
        return mIsLoading;
    }

    public boolean hasMorePages() {
        return mHasMorePages;
    }
}