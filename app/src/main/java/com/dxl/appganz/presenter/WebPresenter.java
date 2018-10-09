package com.dxl.appganz.presenter;

import com.dxl.appganz.interf.IWebPresenter;
import com.dxl.appganz.interf.IWebView;

/**
 * Created by dxl on 2018/10/9 14:58.
 */
public class WebPresenter implements IWebPresenter {

    private IWebView mIWebView;

    public WebPresenter(IWebView webView) {
        mIWebView = webView;
    }

    @Override
    public void subscribe() {
        mIWebView.initWebView();
        mIWebView.setTitle(mIWebView.getWebTitle());

        mIWebView.loadUrl(mIWebView.getUrl());
    }

    @Override
    public void unSubscribe() {

    }
}
