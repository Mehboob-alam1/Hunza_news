package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.adapters.EntertainmentAdapter;
import com.mehboob.hunzanews.adapters.GbAdapter;
import com.mehboob.hunzanews.adapters.HealthAdapter;
import com.mehboob.hunzanews.adapters.NationalAdapter;
import com.mehboob.hunzanews.adapters.PakistanAdapter;
import com.mehboob.hunzanews.adapters.SportsAdapter;
import com.mehboob.hunzanews.adapters.WorldAdapter;
import com.mehboob.hunzanews.databinding.ActivityCategorizeArticleBinding;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;

import java.lang.reflect.Type;
import java.util.List;

public class CategorizeArticleActivity extends AppCompatActivity {
private ActivityCategorizeArticleBinding binding;
private List<CategoryItem> list;
private String listObj;
private String from;
private WorldAdapter worldAdapter;
private SportsAdapter sportsAdapter;
private EntertainmentAdapter entertainmentAdapter;
private GbAdapter gbAdapter;

private PakistanAdapter pakistanAdapter;


private NationalAdapter nationalAdapter;
private HealthAdapter healthAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCategorizeArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listObj = getIntent().getStringExtra("list");
        from= getIntent().getStringExtra("from");

        Gson gson = new Gson();
        Type listType = new TypeToken<List<CategoryItem>>() {
        }.getType();

        list = gson.fromJson(listObj, listType);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.progressBar.setVisibility(View.GONE);
            }
        },3000);

        setLayoutManager();

        binding.backBtn.setOnClickListener(view -> {
            finish();
        });



        switch (from){


            case "world":
                worldAdapter= new WorldAdapter(list,this,1);
                binding.recyclerCategorize.setAdapter(worldAdapter);
                binding.txtTitleCategory.setText("World");
                worldAdapter.setOnItemClickListener((position, categoryItem) -> {
                    onItemClick(categoryItem);
                });
                break;

            case "Sports":
                sportsAdapter= new SportsAdapter(list,this,1);
                binding.recyclerCategorize.setAdapter(sportsAdapter);
                binding.txtTitleCategory.setText("Sports");
                sportsAdapter.setOnItemClickListener((position, newsItem) -> onItemClick(newsItem));
                break;


            case "Entertain":
                entertainmentAdapter= new EntertainmentAdapter(list,this,1);
                binding.recyclerCategorize.setAdapter(entertainmentAdapter);
                binding.txtTitleCategory.setText("Entertainment");
                entertainmentAdapter.setOnItemClickListener((position, newsItem) -> onItemClick(newsItem));
                break;

            case "GB":
                gbAdapter= new GbAdapter(list,this,1);
                binding.recyclerCategorize.setAdapter(gbAdapter);
                binding.txtTitleCategory.setText("Gilgit Baltistan");
                gbAdapter.setOnItemClickListener((position, newsItem) -> onItemClick(newsItem));

                break;

            case "Pak":
                pakistanAdapter= new PakistanAdapter(list,this,1);
                binding.recyclerCategorize.setAdapter(pakistanAdapter);
                binding.txtTitleCategory.setText("Pakistan");
                pakistanAdapter.setOnItemClickListener((position, categoryItem) -> onItemClick(categoryItem));
                break;

            case "Nation":
                nationalAdapter= new NationalAdapter(list,this,1);
                binding.recyclerCategorize.setAdapter(nationalAdapter);
                binding.txtTitleCategory.setText("National");
                nationalAdapter.setOnItemClickListener((position, newsItem) -> onItemClick(newsItem));
                break;
            case "Health":
                healthAdapter= new HealthAdapter(list,this,1);
                binding.recyclerCategorize.setAdapter(healthAdapter);
                binding.txtTitleCategory.setText("Health");
                healthAdapter.setOnItemClickListener((position, newsItem) -> onItemClick(newsItem));
                break;






        }






    }

    private void onItemClick(CategoryItem categoryItem) {

        Intent i = new Intent(this, ExploreDetailsActivity.class);
        Gson gson = new Gson();
        String jsonObj = gson.toJson(categoryItem);

        if (jsonObj != null) {
            i.putExtra("obj", jsonObj);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(i);
        } else {
            // Handle the case where jsonObj is null, perhaps log an error or show a message
            Log.e("DetailsCategory", "jsonObj is null");
            // You might want to show a Toast or log the error
            Toast.makeText(this, "Error: Could not retrieve article details", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setReverseLayout(true);
//        llm.setStackFromEnd(true);
        binding.recyclerCategorize.setLayoutManager(llm);
    }
}