package com.dxl.appganz.interf;

import com.dxl.appganz.model.CategoryResult;

import java.util.List;

/**
 * Created by dxl on 2018/9/28 21:31.
 */
public interface IHomeFragmentView extends BaseView{

    void getCategoryItemFail(String message);

    void setCategoryItems(List<CategoryResult.ResultsBean> items);

}
