package com.mehboob.hunzanews.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.mehboob.hunzanews.Repository.NewsRepository;
import com.mehboob.hunzanews.adapters.NewsAdapter;
import com.mehboob.hunzanews.databinding.FragmentTopStoriesBinding;
import com.mehboob.hunzanews.models.allarticles.ApiResponse;

import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.models.allarticles.CommentCount;
import com.mehboob.hunzanews.network.ApiClient;
import com.mehboob.hunzanews.network.NewsApiService;
import com.mehboob.hunzanews.utils.SessionManager;
import com.mehboob.hunzanews.viewModel.NewsViewModel;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopStoriesFragment extends Fragment {
    private FragmentTopStoriesBinding binding;



    private NewsAdapter adapter;

    NewsApiService apiService;

    private NewsViewModel newsViewModel;
    private NewsRepository newsRepository;

private LinearLayoutManager layoutManager;
    private static final int PER_PAGE = 300;
    private static final String TAG = "NewsFragment";


    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentTopStoriesBinding.inflate(inflater, container, false);

        adapter = new NewsAdapter(getActivity().getApplication(), new ArrayList<>());
         layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.idRVUsers.setLayoutManager(layoutManager);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.progressBar.setVisibility(View.GONE);
            }
        },5000);




        newsViewModel.getAllNews().observe(getViewLifecycleOwner(), newsItems -> {




            // Update your UI with the new data
            adapter.setNewsList(newsItems);




        });
        adapter.setOnItemClickListener((position, newsItem) -> {
            Intent i = new Intent(getActivity().getApplication(), ArticleDetailActivity.class);
            Gson gson= new Gson();
            String jsonObj= gson.toJson(newsItem);
            i.putExtra("obj",jsonObj);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().getApplication().startActivity(i);
        });

        binding.idRVUsers.setAdapter(adapter);

        return binding.getRoot();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        apiService = ApiClient.getClient().create(NewsApiService.class);
        newsRepository = new NewsRepository(requireActivity().getApplication());
        newsRepository.initApi(apiService);
        sessionManager = new SessionManager(getActivity().getApplication());

        // Observe the LiveData from the ViewModel


        newsViewModel.loadNextPage(PER_PAGE);


    }

    @Override
    public void onResume() {
        super.onResume();

        scrollToFirstItem();
    }

    private void scrollToFirstItem() {
        if (adapter.getItemCount() > 0) {
            layoutManager.scrollToPositionWithOffset(adapter.getItemCount()-1, 0);
        }
    }

}


