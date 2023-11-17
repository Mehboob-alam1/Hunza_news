package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.databinding.ActivityArticleDetailBinding;
import com.mehboob.hunzanews.models.allarticles.NewsItem;
import com.mehboob.hunzanews.utils.HtmlParser;
import com.mehboob.hunzanews.utils.ShareUtil;

import java.lang.reflect.Type;

public class ArticleDetailActivity extends AppCompatActivity {
    private ActivityArticleDetailBinding binding;
    private String obj;
    private NewsItem newsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        obj = getIntent().getStringExtra("obj");

        Gson gson = new Gson();
        Type listType = new TypeToken<NewsItem>() {
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