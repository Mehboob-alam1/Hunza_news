package com.mehboob.hunzanews.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private List<NewsItem> newsPostsList;
    NewsApiService apiService;

    private NewsViewModel newsViewModel;
    private NewsRepository newsRepository;


    private NestedScrollView nestedSV;

    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    int page = 0, limit = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = FragmentTopStoriesBinding.inflate(inflater, container, false);



        adapter = new NewsAdapter(getActivity().getApplication(),new ArrayList<>());
        binding.idRVUsers.setAdapter(adapter);

        // Setup the RecyclerView and load more data when needed
        binding.idRVUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                newsViewModel.isLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (!aBoolean && newsViewModel.hasMorePages() &&
                                (visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                                firstVisibleItemPosition >= 0) {
                            // Load more data when the user scrolls to the end of the list
                            newsViewModel.loadNextPage();
                        }
                    }
                });


            }
        });

  //      loadMoreButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Trigger loading of the next page when the "Load More" button is clicked
//                newsViewModel.loadNextPage();
//            }
//        });


        return binding.getRoot();

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
apiService=ApiClient.getClient().create(NewsApiService.class);
newsRepository= new NewsRepository(requireActivity().getApplication());
newsRepository.intiApi(apiService);

        // Observe the LiveData from the ViewModel
        newsViewModel.getAllNews().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(List<NewsItem> newsItems) {
                // Update your UI with the new data
                adapter.setNewsList(newsItems);
            }
        });
    }


}