package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.webkit.WebViewClient;

import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.databinding.ActivityTermsPolicyBinding;

public class TermsPolicyActivity extends AppCompatActivity {
private ActivityTermsPolicyBinding binding;
private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTermsPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
name=getIntent().getStringExtra("obj");

binding.txt.setText(name);


        // Load and format your privacy policy text
        String privacyPolicyText = getString(R.string.your_privacy_policy_text); // Replace with your actual resource string
        CharSequence styledText = Html.fromHtml(privacyPolicyText);

        // Set the styled text to the TextView
        binding.termPolicy.setText(styledText);

//        binding.webView.getSettings().setJavaScriptEnabled(true);
//        binding.webView.getSettings().getCacheMode();
//        binding.webView.getSettings().getDatabaseEnabled();
//        binding.webView.setWebViewClient(new WebViewClient());
//        binding.webView.loadUrl("https://hunzanews.net/privacy-policy/");


        binding.backBtn.setOnClickListener(view -> finish());
    }
}