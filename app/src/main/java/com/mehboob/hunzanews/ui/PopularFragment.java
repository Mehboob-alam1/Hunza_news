package com.mehboob.hunzanews.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.Repository.CategoryRepository;
import com.mehboob.hunzanews.Repository.LatestNewsRepository;
import com.mehboob.hunzanews.adapters.NewsAdapter;
import com.mehboob.hunzanews.adapters.WorldAdapter;
import com.mehboob.hunzanews.databinding.FragmentPopularBinding;
import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.viewModel.CategoryViewModel;
import com.mehboob.hunzanews.viewModel.LatestViewModel;

import java.util.ArrayList;
import java.util.List;


public class PopularFragment extends Fragment {
    private FragmentPopularBinding binding;
    private LatestViewModel latestViewModel;
    private LatestNewsRepository latestNewsRepository;
    private NewsAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        latestViewModel = new ViewModelProvider(this).get(LatestViewModel.class);
        latestNewsRepository = new LatestNewsRepository(requireActivity().getApplication());


        latestViewModel.requestLatestNews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentPopularBinding.inflate(inflater, container, false);

        setRecyclerView();

        latestViewModel.getLatestNews().observe(getViewLifecycleOwner(), newsItemList -> {
            adapter.setNewsList(newsItemList);
        });

        adapter.setOnItemClickListener((position, newsItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ArticleDetailActivity.class);
            Gson gson= new Gson();
            String jsonObj= gson.toJson(newsItem);
            i.putExtra("obj",jsonObj);
            i.putExtra("fragment","TopStories");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().getApplication().startActivity(i);
        });
        binding.recyclerWorld.setAdapter(adapter);
        return binding.getRoot();
    }

    private void setRecyclerView() {

        adapter = new NewsAdapter(getActivity().getApplication(), new ArrayList<>());
        layoutManager = new LinearLayoutManager(requireContext());

        binding.recyclerWorld.setLayoutManager(layoutManager);
    }
}