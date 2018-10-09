package com.dxl.appganz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter {

    public List<T> getDatas() {
        return mDatas;
    }

    private List<T> mDatas = new ArrayList<>();
    private int mLayoutID;
    protected Context mContext;

    public void addData(List<T> data) {
        if (data != null) {
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void setData(List<T> data) {
        mDatas.clear();
        addData(data);
    }



    CommonRecyclerAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutID = layoutId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutID, viewGroup, false);
        return new CommonRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CommonRecyclerHolder holder = (CommonRecyclerHolder) viewHolder;
        holder.position = i;
        convert(holder, mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    protected abstract void convert(CommonRecyclerHolder holder, T t);



}
