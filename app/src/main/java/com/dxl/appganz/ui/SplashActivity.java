package com.dxl.appganz.ui;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dxl.appganz.R;
import com.dxl.appganz.base.BaseActivity;
import com.dxl.appganz.data.Constants;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.with(SplashActivity.this)
                        .load(Constants.TRANSITION_URLS[new Random().nextInt(Constants.TRANSITION_URLS.length)])
                        .into(mImageView);
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 3000);
    }


    private boolean isIn = false;

    private void toMainActivity() {
        if (isIn)
            return;
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
        isIn = true;
    }

    @OnClick({R.id.skip})
    void onClick(){
        toMainActivity();
    }
}
