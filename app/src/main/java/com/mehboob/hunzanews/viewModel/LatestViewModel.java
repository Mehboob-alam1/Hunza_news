package com.mehboob.hunzanews.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mehboob.hunzanews.Repository.LatestNewsRepository;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;
import com.mehboob.hunzanews.models.allarticles.NewsItem;

import java.util.List;

public class LatestViewModel extends AndroidViewModel {

    private LatestNewsRepository latestNewsRepository;
    private MutableLiveData<List<NewsItem>> latestNews;
    public LatestViewModel(@NonNull Application application) {
        super(application);

        latestNewsRepository= new LatestNewsRepository(application);
        latestNews=latestNewsRepository.getLatestNews();


    }


    public MutableLiveData<List<NewsItem>> getLatestNews() {
        return latestNews;
    }


    public void requestLatestNews(){

        latestNewsRepository.makeApiRequest();
    }
}
