package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mehboob.hunzanews.R;
import com.mehboob.hunzanews.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(view -> finish());


    }
}