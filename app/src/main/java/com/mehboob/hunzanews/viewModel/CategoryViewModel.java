package com.mehboob.hunzanews.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mehboob.hunzanews.Repository.CategoryRepository;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {


    private CategoryRepository categoryRepository;

private MutableLiveData<List<CategoryItem>> worldNews;
    private MutableLiveData<List<CategoryItem>> sports;
    private MutableLiveData<List<CategoryItem>> national;
    private MutableLiveData<List<CategoryItem>> entertainment;
    private MutableLiveData<List<CategoryItem>> health;
    private MutableLiveData<List<CategoryItem>> GB;

    private MutableLiveData<List<CategoryItem>> pakistan;
    ;
    public CategoryViewModel(Application application) {

        super(application);

        categoryRepository= new CategoryRepository(application);

        worldNews=categoryRepository.getWorldNews();

        sports=categoryRepository.getSports();
        national=categoryRepository.getNational();
        entertainment=categoryRepository.getEntertainment();
        health=categoryRepository.getHealth();
        GB=categoryRepository.getGB();

        pakistan=categoryRepository.getPakistan();

    }



    public void getCategorizeData(String category){
        categoryRepository.makeApiRequest(category);

    }


    public MutableLiveData<List<CategoryItem>> getWorldNews() {
        return worldNews;
    }


    public MutableLiveData<List<CategoryItem>> getSports() {
        return sports;
    }

    public MutableLiveData<List<CategoryItem>> getEntertainment() {
        return entertainment;
    }

    public MutableLiveData<List<CategoryItem>> getGB() {
        return GB;
    }


    public MutableLiveData<List<CategoryItem>> getPakistan() {
        return pakistan;
    }

    public MutableLiveData<List<CategoryItem>> getNational() {
        return national;
    }



    public MutableLiveData<List<CategoryItem>> getHealth() {
        return health;
    }




}
