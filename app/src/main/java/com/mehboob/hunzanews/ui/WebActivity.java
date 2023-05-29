package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebViewClient;

import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {
private ActivityWebBinding binding;
String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        url= getIntent().getStringExtra("url");
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl(url);



    }
}