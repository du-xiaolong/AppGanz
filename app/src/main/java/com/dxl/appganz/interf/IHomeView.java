package com.dxl.appganz.interf;

import java.util.List;

/**
 * Created by dxl on 2018/9/26 22:38.
 */
public interface IHomeView extends BaseView {
    void loadBannerFail(String errorMessage);
    void setBanner(List<String> imgs);
}
