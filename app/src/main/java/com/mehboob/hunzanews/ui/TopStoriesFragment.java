package com.mehboob.hunzanews.ui;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mehboob.hunzanews.Repository.NewsRepository;
import com.mehboob.hunzanews.adapters.NewsAdapter;
import com.mehboob.hunzanews.databinding.FragmentTopStoriesBinding;
import com.mehboob.hunzanews.models.allarticles.ApiResponse;

import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.models.allarticles.CommentCount;
import com.mehboob.hunzanews.network.ApiClient;
import com.mehboob.hunzanews.network.NewsApiService;
import com.mehboob.hunzanews.viewModel.NewsViewModel;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopStoriesFragment extends Fragment {
    private FragmentTopStoriesBinding binding;

    private ArrayList<CommentCount> list;

    private NewsAdapter adapter;

    NewsApiService apiService;

    private NewsViewModel newsViewModel;
    private NewsRepository newsRepository;


    private static final int PER_PAGE = 3;
    private static final String TAG = "NewsFragment";

    private boolean isLoading = false;

    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    int mCurrentPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentTopStoriesBinding.inflate(inflater, container, false);


        adapter = new NewsAdapter(getActivity().getApplication(), new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.idRVUsers.setLayoutManager(layoutManager);

        binding.idRVUsers.setAdapter(adapter);

        if (mCurrentPage != 1) {
            onScrollOfRecyclerView(layoutManager);
        }


        return binding.getRoot();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        apiService = ApiClient.getClient().create(NewsApiService.class);
        newsRepository = new NewsRepository(requireActivity().getApplication());
        newsRepository.initApi(apiService);

        // Observe the LiveData from the ViewModel
        if (mCurrentPage == 1) {
            newsViewModel.loadNextPage(PER_PAGE, mCurrentPage);
            mCurrentPage=2;
            Log.d(TAG, "Its first time");
        } else {
            Log.d(TAG, "Its second time");
        }
        newsViewModel.getAllNews().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(List<NewsItem> newsItems) {
                // Update your UI with the new data
                adapter.setNewsList(newsItems);

                isLoading = false;
                hideProgressBar();
                Log.d(TAG, "onChanged: " + newsItems.size());
            }
        });


    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void onScrollOfRecyclerView(LinearLayoutManager layoutManager) {
        binding.idRVUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    // Load more items
                    isLoading = true;
                    mCurrentPage++;
                    Log.d(TAG, "Current Page: " + mCurrentPage);
                    showProgressBar();

                    newsViewModel.loadNextPage(PER_PAGE, mCurrentPage);

                }
            }
        });

    }
}


