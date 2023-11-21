package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.databinding.ActivityExploreDetailsBinding;
import com.mehboob.hunzanews.models.allarticles.CategoryItem;
import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.utils.HtmlParser;
import com.mehboob.hunzanews.utils.ShareUtil;

import java.lang.reflect.Type;

public class ExploreDetailsActivity extends AppCompatActivity {
    private ActivityExploreDetailsBinding binding;
    private String obj;
    private CategoryItem newsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExploreDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        obj = getIntent().getStringExtra("obj");

        Gson gson = new Gson();
        Type listType = new TypeToken<CategoryItem>() {
        }.getType();

        newsItem = gson.fromJson(obj, listType);




        binding.backBtn.setOnClickListener(view -> {
            onBackPressed();
        });


        Glide.with(this).load(newsItem.getThumbnailUrl())
                .into(binding.imgArticle);

        binding.postTitle.setText(newsItem.getPostTitle());
        binding.datePost.setText("Updated at "+newsItem.getPostDate());
        binding.postBy.setText("By " +newsItem.getPostAuthor());

        binding.postContent.setText(HtmlParser.parseHtml(newsItem.getPostContent()));

        binding.shareBtn.setOnClickListener(view -> {
            ShareUtil.shareLink(newsItem.getGuid(),this);
        });
    }
}