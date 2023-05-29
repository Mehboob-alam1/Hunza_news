package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mehboob.hunzanews.MainActivity;
import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
private ActivitySplashBinding binding;
    private static final long SPLASH_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(new Intent(SplashActivity.this, MainActivity.class));

            }
        },SPLASH_DELAY);
    }
}