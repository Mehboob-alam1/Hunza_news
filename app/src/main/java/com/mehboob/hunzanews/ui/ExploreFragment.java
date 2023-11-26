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
import com.mehboob.hunzanews.adapters.GbAdapter;
import com.mehboob.hunzanews.adapters.HealthAdapter;
import com.mehboob.hunzanews.adapters.NationalAdapter;
import com.mehboob.hunzanews.adapters.PakistanAdapter;
import com.mehboob.hunzanews.adapters.SportsAdapter;
import com.mehboob.hunzanews.adapters.WorldAdapter;
import com.mehboob.hunzanews.databinding.FragmentExploreBinding;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;
import com.mehboob.hunzanews.network.ApiClient;
import com.mehboob.hunzanews.network.NewsApiService;
import com.mehboob.hunzanews.viewModel.CategoryViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    private GbAdapter gbAdapter;
    private PakistanAdapter pakistanAdapter;

    private NationalAdapter nationalAdapter;

    private HealthAdapter healthAdapter;
    private LinearLayoutManager worldLayoutManager;
    private LinearLayoutManager sportsLayoutManager;
    private LinearLayoutManager entertainmentLayoutManager;
    private LinearLayoutManager gbLinerLayoutManager;
    private LinearLayoutManager pakLinearLayoutManager;
    private LinearLayoutManager nationalLayoutManager;
    private LinearLayoutManager healthLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        apiService = ApiClient.getClient().create(NewsApiService.class);
        categoryRepository = new CategoryRepository(requireActivity().getApplication());


        categoryViewModel.getCategorizeData("world");
        categoryViewModel.getCategorizeData("sports");

        categoryViewModel.getCategorizeData("Entertainment");
        categoryViewModel.getCategorizeData("Gilgit Baltistan");
        categoryViewModel.getCategorizeData("Pakistan");
        categoryViewModel.getCategorizeData("National");
        categoryViewModel.getCategorizeData("Health");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentExploreBinding.inflate(inflater, container, false);

        setViews();


        categoryViewModel.getWorldNews().observe(getViewLifecycleOwner(), categoryItems -> adapter.setNewsList(categoryItems));


        categoryViewModel.getSports().observe(getViewLifecycleOwner(), categoryItems -> sportsAdapter.setNewsList(categoryItems));


        categoryViewModel.getGB().observe(getViewLifecycleOwner(), categoryItems -> gbAdapter.setNewsList(categoryItems));
        categoryViewModel.getEntertainment().observe(getViewLifecycleOwner(), categoryItems -> {
            entertainmentAdapter.setNewsList(categoryItems);
        });

        categoryViewModel.getPakistan().observe(getViewLifecycleOwner(), categoryItems -> {


            pakistanAdapter.setNewsList(categoryItems);
        });
        categoryViewModel.getNational().observe(getViewLifecycleOwner(), categoryItems -> nationalAdapter.setNewsList(categoryItems));


        categoryViewModel.getHealth().observe(getViewLifecycleOwner(),categoryItems -> healthAdapter.setNewsList(categoryItems));
        onClicks();
        binding.recyclerWorld.setAdapter(adapter);
        binding.recyclerSports.setAdapter(sportsAdapter);
        binding.recyclerEntertainment.setAdapter(entertainmentAdapter);
        binding.recyclerGb.setAdapter(gbAdapter);

        binding.recyclerPakistan.setAdapter(pakistanAdapter);

        binding.recyclerNational.setAdapter(nationalAdapter);
        binding.recyclerHealth.setAdapter(healthAdapter);

        fullArticles();
        return binding.getRoot();
    }

    private void fullArticles() {

        binding.lineWorld.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplication(), CategorizeArticleActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(adapter.getNewsList());
            i.putExtra("list", jsonObj);
            i.putExtra("from", "world");
            getContext().startActivity(i);
        });

        binding.lineSports.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplication(), CategorizeArticleActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(sportsAdapter.getNewsList());
            i.putExtra("list", jsonObj);
            i.putExtra("from", "Sports");
            getContext().startActivity(i);
        });
        binding.lineEntertainment.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplication(), CategorizeArticleActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(entertainmentAdapter.getNewsList());
            i.putExtra("list", jsonObj);
            i.putExtra("from", "Entertain");
            getContext().startActivity(i);
        });
        binding.lineGB.setOnClickListener(view -> {

            Intent i = new Intent(getActivity().getApplication(), CategorizeArticleActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(gbAdapter.getNewsList());
            i.putExtra("list", jsonObj);
            i.putExtra("from", "GB");
            getContext().startActivity(i);


        });


        binding.linePakistan.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplication(), CategorizeArticleActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(pakistanAdapter.getNewsList());
            i.putExtra("list", jsonObj);
            i.putExtra("from", "Pak");
            getContext().startActivity(i);
        });


        binding.lineNational.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplication(), CategorizeArticleActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(nationalAdapter.getNewsList());
            i.putExtra("list", jsonObj);
            i.putExtra("from", "Nation");
            getContext().startActivity(i);
        });


        binding.lineHealth.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplication(), CategorizeArticleActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(healthAdapter.getNewsList());
            i.putExtra("list", jsonObj);
            i.putExtra("from", "Health");
            getContext().startActivity(i);
        });
    }

    private void onClicks() {

        //world adapter

        adapter.setOnItemClickListener((position, categoryItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ExploreDetailsActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(categoryItem);

            if (jsonObj != null) {
                i.putExtra("obj", jsonObj);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().getApplication().startActivity(i);
            } else {
                // Handle the case where jsonObj is null, perhaps log an error or show a message
                Log.e(TAG, "jsonObj is null");
                // You might want to show a Toast or log the error
                Toast.makeText(getActivity().getApplication(), "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
            }
        });

        //sports

        sportsAdapter.setOnItemClickListener((position, newsItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ExploreDetailsActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(newsItem);

            if (jsonObj != null) {
                i.putExtra("obj", jsonObj);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().getApplication().startActivity(i);
            } else {
                // Handle the case where jsonObj is null, perhaps log an error or show a message
                Log.e(TAG, "jsonObj is null");
                // You might want to show a Toast or log the error
                Toast.makeText(getActivity().getApplication(), "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
            }
        });

        //entertainment

        entertainmentAdapter.setOnItemClickListener((position, newsItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ExploreDetailsActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(newsItem);

            if (jsonObj != null) {
                i.putExtra("obj", jsonObj);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().getApplication().startActivity(i);
            } else {
                // Handle the case where jsonObj is null, perhaps log an error or show a message
                Log.e(TAG, "jsonObj is null");
                // You might want to show a Toast or log the error
                Toast.makeText(getActivity().getApplication(), "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
            }
        });

// gilgit

        gbAdapter.setOnItemClickListener((position, newsItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ExploreDetailsActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(newsItem);

            if (jsonObj != null) {
                i.putExtra("obj", jsonObj);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().getApplication().startActivity(i);
            } else {
                // Handle the case where jsonObj is null, perhaps log an error or show a message
                Log.e(TAG, "jsonObj is null");
                // You might want to show a Toast or log the error
                Toast.makeText(getActivity().getApplication(), "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
            }
        });

        //pakistan
        pakistanAdapter.setOnItemClickListener((position, categoryItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ExploreDetailsActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(categoryItem);

            if (jsonObj != null) {
                i.putExtra("obj", jsonObj);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().getApplication().startActivity(i);
            } else {
                // Handle the case where jsonObj is null, perhaps log an error or show a message
                Log.e(TAG, "jsonObj is null");
                // You might want to show a Toast or log the error
                Toast.makeText(getActivity().getApplication(), "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
            }
        });


        //national

        nationalAdapter.setOnItemClickListener((position, newsItem) -> {


            Intent i = new Intent(getActivity().getApplication(), ExploreDetailsActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(newsItem);

            if (jsonObj != null) {
                i.putExtra("obj", jsonObj);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().getApplication().startActivity(i);
            } else {
                // Handle the case where jsonObj is null, perhaps log an error or show a message
                Log.e(TAG, "jsonObj is null");
                // You might want to show a Toast or log the error
                Toast.makeText(getActivity().getApplication(), "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
            }
        });

        healthAdapter.setOnItemClickListener((position, newsItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ExploreDetailsActivity.class);
            Gson gson = new Gson();
            String jsonObj = gson.toJson(newsItem);

            if (jsonObj != null) {
                i.putExtra("obj", jsonObj);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().getApplication().startActivity(i);
            } else {
                // Handle the case where jsonObj is null, perhaps log an error or show a message
                Log.e(TAG, "jsonObj is null");
                // You might want to show a Toast or log the error
                Toast.makeText(getActivity().getApplication(), "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
            }
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

        //gb

        gbAdapter = new GbAdapter(new ArrayList<>(), getActivity().getApplication(), 0);
        gbLinerLayoutManager = new LinearLayoutManager(requireContext());
        gbLinerLayoutManager.setReverseLayout(true);
        gbLinerLayoutManager.setStackFromEnd(true);
        binding.recyclerGb.setLayoutManager(gbLinerLayoutManager);

        // pakistan

        pakistanAdapter = new PakistanAdapter(new ArrayList<>(), getActivity().getApplication(), 0);
        pakLinearLayoutManager = new LinearLayoutManager(requireContext());
        pakLinearLayoutManager.setReverseLayout(true);
        pakLinearLayoutManager.setStackFromEnd(true);
        binding.recyclerPakistan.setLayoutManager(pakLinearLayoutManager);

        // national
        nationalAdapter = new NationalAdapter(new ArrayList<>(), getActivity().getApplication(), 0);
        nationalLayoutManager = new LinearLayoutManager(requireContext());
        nationalLayoutManager.setReverseLayout(true);
        nationalLayoutManager.setStackFromEnd(true);
        binding.recyclerNational.setLayoutManager(nationalLayoutManager);


        // health
        healthAdapter = new HealthAdapter(new ArrayList<>(), getActivity().getApplication(), 0);
        healthLayoutManager = new LinearLayoutManager(requireContext());
        healthLayoutManager.setReverseLayout(true);
        healthLayoutManager.setStackFromEnd(true);
        binding.recyclerHealth.setLayoutManager(healthLayoutManager);
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