package com.dxl.appganz.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dxl.appganz.R;
import com.dxl.appganz.model.CategoryResult;

public class MyRecyclerViewAdapter extends CommonRecyclerAdapter<CategoryResult.ResultsBean> {

    public MyRecyclerViewAdapter(Context context) {
        super(context, R.layout.item_category);

    }


    @Override
    protected void convert(CommonRecyclerHolder holder, CategoryResult.ResultsBean resultsBean) {
        if (resultsBean == null) return;
        ImageView imageView = holder.getView(R.id.category_item_img);
        if (resultsBean.images != null && resultsBean.images.size() > 0) {
            String quality = "?imageView2/0/w/400";
            Glide.with(mContext)
                    .load(resultsBean.images.get(0) + quality)
                    .placeholder(R.mipmap.image_default)
                    .error(R.mipmap.image_default)
                    .into(imageView);
        }
        holder.setTextViewText(R.id.category_item_desc, resultsBean.desc == null ? "unknown" : resultsBean.desc);
        holder.setTextViewText(R.id.category_item_author, resultsBean.who == null ? "unknown" : resultsBean.who);
        holder.setTextViewText(R.id.category_item_time, resultsBean.publishedAt);
        holder.setTextViewText(R.id.category_item_src, resultsBean.source == null ? "unknown" : resultsBean.source);
    }


}
