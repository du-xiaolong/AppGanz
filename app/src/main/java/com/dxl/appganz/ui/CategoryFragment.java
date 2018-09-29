package com.dxl.appganz.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dxl.appganz.R;
import com.dxl.appganz.adapter.MyRecyclerViewAdapter;
import com.dxl.appganz.base.BaseFragment;
import com.dxl.appganz.interf.IHomeFragmentView;
import com.dxl.appganz.model.CategoryResult;
import com.dxl.appganz.presenter.HomeFragmentPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dxl on 2018/9/28 21:38.
 */
public class CategoryFragment extends BaseFragment implements IHomeFragmentView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private static final String CATEGORY_NAME = "category_name";

    HomeFragmentPresenter mFragmentPresenter;

    private String title = "";

    private MyRecyclerViewAdapter mAdapter;

    public static CategoryFragment newInstance(String categoryName) {

        Bundle args = new Bundle();
        args.putString(CATEGORY_NAME, categoryName);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void init() {
        mFragmentPresenter = new HomeFragmentPresenter(this);
        initRecyclerView();
        mFragmentPresenter.subscribe();
    }

    //初始化RecyclerView
    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void getCategoryItemFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCategoryItems(List<CategoryResult.ResultsBean> items) {
        mAdapter.addData(items);
    }

    @Override
    public String getCategoryName() {
        return getArguments().getString(CATEGORY_NAME, "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentPresenter.unSubscribe();
    }
}
