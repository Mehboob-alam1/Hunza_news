package com.mehboob.hunzanews.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mehboob.hunzanews.ui.LiveFragment;
import com.mehboob.hunzanews.ui.MyNewsFragment;
import com.mehboob.hunzanews.ui.PopularFragment;
import com.mehboob.hunzanews.ui.TopStoriesFragment;
import com.mehboob.hunzanews.ui.VideoFragment;

public class PagerAdapter extends FragmentPagerAdapter {


    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        tabCount=behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new TopStoriesFragment();
            case 1:
                return new VideoFragment();
            case 2:
                return new MyNewsFragment();
            case 3:
                return new PopularFragment();
            case 4:
                return new LiveFragment();

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
