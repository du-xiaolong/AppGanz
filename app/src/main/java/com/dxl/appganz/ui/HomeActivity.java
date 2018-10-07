package com.dxl.appganz.ui;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dxl.appganz.R;
import com.dxl.appganz.adapter.CommonViewPagerAdapter;
import com.dxl.appganz.base.BaseActivity;
import com.dxl.appganz.data.Constants;
import com.dxl.appganz.interf.IHomeView;
import com.dxl.appganz.presenter.HomePresenter;
import com.dxl.appganz.util.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dxl on 2018/9/26 22:28.
 */
public class HomeActivity extends BaseActivity implements IHomeView {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tab_layout)
    TabLayout mTablayout;
    @BindView(R.id.main_vp)
    ViewPager viewPager;

    HomePresenter mHomePresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public void loadBannerFail(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBanner(List<String> imgs) {
        mBanner.setImages(imgs).start();
    }

    @Override
    protected void beforeInit() {
        StatusBarUtil.setTranslucent(this);
    }

    @Override
    protected void initView() {
        mHomePresenter = new HomePresenter(this);
        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(HomeActivity.this).load(path).into(imageView);
                    }
                });
        CommonViewPagerAdapter pagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < Constants.categoryNames.length; i++) {
            CategoryFragment fragment = CategoryFragment.newInstance(Constants.categoryNames[i]);
            pagerAdapter.addFragment(fragment);
        }
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        mTablayout.setupWithViewPager(viewPager);

        mHomePresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHomePresenter != null) {
            mHomePresenter.unSubscribe();
        }
    }
}
