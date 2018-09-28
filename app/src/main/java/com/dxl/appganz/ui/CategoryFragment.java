package com.dxl.appganz.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dxl.appganz.R;
import com.dxl.appganz.base.BaseFragment;
import com.dxl.appganz.interf.IHomeFragmentView;
import com.dxl.appganz.model.CategoryResult;
import com.dxl.appganz.presenter.HomeFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dxl on 2018/9/28 21:38.
 */
public class CategoryFragment extends BaseFragment implements IHomeFragmentView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    HomeFragmentPresenter mFragmentPresenter;
    private MyRecyclerViewAdapter mAdapter;


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


    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        public MyRecyclerViewAdapter() {

        }

        public void addData(List<CategoryResult.ResultsBean> list){
            itemList.clear();
            itemList.addAll(list);
            notifyDataSetChanged();
        }

        private List<CategoryResult.ResultsBean> itemList = new ArrayList<>();


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.item_text.setText(itemList.get(i).desc);
        }

        @Override
        public int getItemCount() {
            return itemList == null ? 0 : itemList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView item_text;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_text = itemView.findViewById(R.id.item_text);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentPresenter.unSubscribe();
    }
}
