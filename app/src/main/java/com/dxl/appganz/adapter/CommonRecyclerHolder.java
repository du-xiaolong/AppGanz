package com.dxl.appganz.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

public class CommonRecyclerHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mContentView;

    public CommonRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        mContentView = itemView;
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(@IdRes int viewID) {
        View view = mViews.get(viewID);
        if (view == null) {
            view = mContentView.findViewById(viewID);
            mViews.put(viewID, view);
        }
        return (T) view;
    }


    public void setTextViewText(@IdRes int textViewId, String text) {
        ((TextView) getView(textViewId)).setText(text);
    }


}
