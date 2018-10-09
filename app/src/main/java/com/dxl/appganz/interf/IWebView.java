package com.dxl.appganz.interf;

/**
 * Created by dxl on 2018/10/9 14:52.
 */
public interface IWebView extends BaseView {

    void initWebView();

    void loadUrl(String url);

    void setTitle(String title);

    String getUrl();

    String getWebTitle();
}
