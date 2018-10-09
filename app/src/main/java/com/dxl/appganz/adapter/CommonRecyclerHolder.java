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
    public int position;

    public CommonRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        mContentView = itemView;
        mViews = new SparseArray<>();
        mContentView.setOnClickListener(new MyOnClickListener(position));
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

    public void setOnClickListener(OnItemClickListener itemClickListener, @IdRes int... ids){
        MyOnClickListener myOnClickListener = new MyOnClickListener(position);
        myOnClickListener.setItemClickListener(itemClickListener);
        for (int id : ids) {
            getView(id).setOnClickListener(myOnClickListener);
        }
    }


    interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    class MyOnClickListener implements View.OnClickListener{

        private int mPosition;

        OnItemClickListener mItemClickListener;

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            mItemClickListener = itemClickListener;
        }

        public MyOnClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, mPosition);
            }
        }
    }


}
