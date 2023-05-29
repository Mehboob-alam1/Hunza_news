package com.mehboob.hunzanews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mehboob.hunzanews.adapters.PagerAdapter;
import com.mehboob.hunzanews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private String API="a6b3796ea24745ca9d057b47d93f178f";
private PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


         adapter= new PagerAdapter(getSupportFragmentManager(),5);
         binding.frameContainer.setAdapter(adapter);


         binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 binding.frameContainer.setCurrentItem(tab.getPosition());
                 if (tab.getPosition()==0|| tab.getPosition()==1|| tab.getPosition()==2|| tab.getPosition()==3|| tab.getPosition()==4)
                     adapter.notifyDataSetChanged();
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });

         binding.frameContainer.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
    }
}