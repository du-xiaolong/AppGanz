package com.dxl.appganz.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dxl.appganz.R;
import com.dxl.appganz.model.CategoryResult;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    public MyRecyclerViewAdapter() {

    }

    public void addData(List<CategoryResult.ResultsBean> list) {
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
