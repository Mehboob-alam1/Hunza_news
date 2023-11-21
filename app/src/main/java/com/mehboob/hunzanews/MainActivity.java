package com.mehboob.hunzanews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.mehboob.hunzanews.adapters.PagerAdapter;
import com.mehboob.hunzanews.databinding.ActivityMainBinding;
import com.mehboob.hunzanews.ui.ExploreFragment;
import com.mehboob.hunzanews.ui.LiveFragment;
import com.mehboob.hunzanews.ui.PopularFragment;
import com.mehboob.hunzanews.ui.SettingActivity;
import com.mehboob.hunzanews.ui.TopStoriesFragment;
import com.mehboob.hunzanews.ui.VideoFragment;
import com.mehboob.hunzanews.viewModel.NewsViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private PagerAdapter adapter;
    private boolean doublePressToExit = false;

    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        adapter = new PagerAdapter(getSupportFragmentManager(), 5);
//
//
//        binding.frameContainer.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_INTERNET);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new TopStoriesFragment()).commit();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.top_stories:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new TopStoriesFragment()).commit();
                    return true;
                case R.id.explore:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new ExploreFragment()).commit();
                    return true;
                case R.id.popular:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new PopularFragment()).commit();
                    return true;
                case R.id.video:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new VideoFragment()).commit();
                    return true;
                case R.id.live:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new LiveFragment()).commit();
                    return true;
            }
            return false;
        });


binding.btnSettings.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SettingActivity.class)));

    }

    @Override
    public void onBackPressed() {

        if (doublePressToExit) {
            finishAffinity();


        }
        doublePressToExit = true;
        Toast.makeText(this, "Please click BACK again to exit !", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doublePressToExit = false, 2000);

    }
}