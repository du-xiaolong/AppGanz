package com.dxl.appganz.ui;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dxl.appganz.R;
import com.dxl.appganz.base.BaseActivity;
import com.dxl.appganz.interf.IHomeView;
import com.dxl.appganz.interf.IWebPresenter;
import com.dxl.appganz.interf.IWebView;
import com.dxl.appganz.presenter.WebPresenter;

import butterknife.BindView;

/**
 * Created by dxl on 2018/10/9 14:55.
 */
public class WebViewActivity extends BaseActivity implements IWebView {
    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.web_toolbar)
    Toolbar mWebToolbar;
    @BindView(R.id.web_progressBar)
    ProgressBar mWebProgressBar;
    @BindView(R.id.web_appbar)
    AppBarLayout mWebAppbar;
    @BindView(R.id.web_view)
    WebView mWebView;


    IWebPresenter mWebPresenter;




    @Override
    protected void initView() {
        super.initView();
        mWebPresenter = new WebPresenter(this);
        mWebToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mWebPresenter.subscribe();

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_web_view;
    }


    @Override
    public void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mWebProgressBar.setVisibility(View.VISIBLE);
                mWebProgressBar.setProgress(newProgress);
            }
        });
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void setTitle(String title) {
        mWebTitle.setText(title);
    }



    @Override
    public String getUrl() {
        return getIntent().getStringExtra("url");
    }

    @Override
    public String getWebTitle() {
        return getIntent().getStringExtra("title");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }else {
            finish();
        }
    }
}
