package com.dxl.appganz;

import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dxl.appganz.base.BaseActivity;
import com.dxl.appganz.data.Constants;

import java.util.Random;

import butterknife.BindView;

/**
 * Created by dxl on 2018-9-26 20:40:28
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_spash;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.with(SplashActivity.this)
                        .load(Constants.TRANSITION_URLS[new Random().nextInt(Constants.TRANSITION_URLS.length)])
                        .into(mImageView);
            }
        }, 1000);
    }
}
