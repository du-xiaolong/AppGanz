package com.dxl.appganz.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dxl.appganz.ui.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class CommonViewPagerAdapter extends FragmentPagerAdapter {

    private List<CategoryFragment> fragments = new ArrayList<>();

    public void addFragment(CategoryFragment fragment) {
        fragments.add(fragment);
    }


    public CommonViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ((CategoryFragment) getItem(position)).getCategoryName();
    }
}
