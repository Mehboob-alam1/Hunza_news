package com.mehboob.hunzanews.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.adapters.RecyclerAdapter;
import com.mehboob.hunzanews.databinding.FragmentTopStoriesBinding;
import com.mehboob.hunzanews.models.MainModel;
import com.mehboob.hunzanews.models.ModelClass;
import com.mehboob.hunzanews.utils.ApiUtilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopStoriesFragment extends Fragment {
private FragmentTopStoriesBinding binding;
    private String API="a6b3796ea24745ca9d057b47d93f178f";
    private ArrayList<ModelClass> list;
    private String country="us";
    private RecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_top_stories, container, false);

        binding=FragmentTopStoriesBinding.inflate(inflater,container,false);

        list= new ArrayList<>();
        binding.recyclerStories.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter= new RecyclerAdapter(getContext(),list);
        binding.recyclerStories.setAdapter(adapter);


        findNews();







       return binding.getRoot();

    }

    private void findNews() {

        ApiUtilities.getApiInterface().getNews(country,100,API).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                    if (response.isSuccessful()){
                        list.addAll(response.body().getArticles());
                        adapter.notifyDataSetChanged();
                    }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {

            }
        });

    }
}