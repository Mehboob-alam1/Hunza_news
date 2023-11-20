package com.mehboob.hunzanews.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mehboob.hunzanews.Repository.CategoryRepository;
import com.mehboob.hunzanews.adapters.EntertainmentAdapter;
import com.mehboob.hunzanews.adapters.SportsAdapter;
import com.mehboob.hunzanews.adapters.WorldAdapter;
import com.mehboob.hunzanews.databinding.FragmentExploreBinding;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;
import com.mehboob.hunzanews.network.ApiClient;
import com.mehboob.hunzanews.network.NewsApiService;
import com.mehboob.hunzanews.viewModel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {
    private FragmentExploreBinding binding;

    private CategoryViewModel categoryViewModel;
    private CategoryRepository categoryRepository;
    private NewsApiService apiService;
    private static final String TAG = "ExploreFragment";
    private WorldAdapter adapter;
    private SportsAdapter sportsAdapter;

    private EntertainmentAdapter entertainmentAdapter;
    private LinearLayoutManager worldLayoutManager;
    private LinearLayoutManager sportsLayoutManager;
    private LinearLayoutManager entertainmentLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        apiService = ApiClient.getClient().create(NewsApiService.class);
        categoryRepository = new CategoryRepository(requireActivity().getApplication());


        categoryViewModel.getCategorizeData("world");
        categoryViewModel.getCategorizeData("sports");

        categoryViewModel.getCategorizeData("Entertainment");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentExploreBinding.inflate(inflater, container, false);

        setViews();


        categoryViewModel.getWorldNews().observe(getViewLifecycleOwner(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {


                adapter.setNewsList(categoryItems);

            }
        });


        categoryViewModel.getSports().observe(getViewLifecycleOwner(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {


                sportsAdapter.setNewsList(categoryItems);

            }
        });

        categoryViewModel.getEntertainment().observe(getViewLifecycleOwner(), new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {


                Log.d(TAG, "onChanged: "+categoryItems.get(0).toString());

                entertainmentAdapter.setNewsList(categoryItems);
            }
        });

        onClicks();
        binding.recyclerWorld.setAdapter(adapter);
        binding.recyclerSports.setAdapter(sportsAdapter);
        binding.recyclerEntertainment.setAdapter(entertainmentAdapter);


        return binding.getRoot();
    }

    private void onClicks() {


        adapter.setOnItemClickListener((position, newsItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ArticleDetailActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(newsItem);
            i.putExtra("obj", jsonObj);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().getApplication().startActivity(i);
        });

    }

    private void setViews() {
        adapter = new WorldAdapter(new ArrayList<>(), getActivity().getApplication(), 0);
        worldLayoutManager = new LinearLayoutManager(requireContext());
        worldLayoutManager.setReverseLayout(true);
        worldLayoutManager.setStackFromEnd(true);
        binding.recyclerWorld.setLayoutManager(worldLayoutManager);

// sports
        sportsAdapter = new SportsAdapter(new ArrayList<>(), getActivity().getApplication(), 0);
        sportsLayoutManager = new LinearLayoutManager(requireContext());
        sportsLayoutManager.setReverseLayout(true);
        sportsLayoutManager.setStackFromEnd(true);
        binding.recyclerSports.setLayoutManager(sportsLayoutManager);

//entertainment
        entertainmentAdapter = new EntertainmentAdapter(new ArrayList<>(), getActivity().getApplication(), 0);
        entertainmentLayoutManager = new LinearLayoutManager(requireContext());
        entertainmentLayoutManager.setReverseLayout(true);
        entertainmentLayoutManager.setStackFromEnd(true);
        binding.recyclerEntertainment.setLayoutManager(entertainmentLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();

        scrollToFirstItem();
    }

    private void scrollToFirstItem() {
        if (adapter.getItemCount() > 0) {
            worldLayoutManager.scrollToPositionWithOffset(adapter.getItemCount() - 1, 0);
        }
        if (sportsAdapter.getItemCount() > 0) {
            sportsLayoutManager.scrollToPositionWithOffset(adapter.getItemCount() - 1, 0);
        }

        if (entertainmentAdapter.getItemCount() > 0) {
            entertainmentLayoutManager.scrollToPositionWithOffset(adapter.getItemCount() - 1, 0);
        }
    }
}