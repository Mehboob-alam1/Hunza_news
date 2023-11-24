package com.mehboob.hunzanews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        binding.termOfUse.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this,TermsPolicyActivity.class);
            intent.putExtra("obj","Terms Of Use");
            startActivity(intent);
        });


        binding.privacyPolicy.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this,TermsPolicyActivity.class);
            intent.putExtra("obj","Privacy Policy");
            startActivity(intent);
        });

binding.contactUs.setOnClickListener(view -> {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("message/rfc822");
    intent.putExtra(Intent.EXTRA_SUBJECT, "");
    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hunzanews@gmail.com"});
    Intent mailer = Intent.createChooser(intent, null);
    startActivity(mailer);
});
    }
}