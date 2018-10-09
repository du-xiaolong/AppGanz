package com.dxl.appganz.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dxl.appganz.R;
import com.dxl.appganz.model.CategoryResult;
import com.dxl.appganz.ui.WebViewActivity;

import java.text.SimpleDateFormat;

public class MyRecyclerViewAdapter extends CommonRecyclerAdapter<CategoryResult.ResultsBean> {

    public MyRecyclerViewAdapter(Context context) {
        super(context, R.layout.item_category);

    }


    @Override
    protected void convert(CommonRecyclerHolder holder, final CategoryResult.ResultsBean resultsBean) {
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
        holder.setTextViewText(R.id.category_item_time, new SimpleDateFormat("yyyy-MM-dd").format(resultsBean.publishedAt));
        holder.setTextViewText(R.id.category_item_src, resultsBean.source == null ? "unknown" : resultsBean.source);
        holder.setOnClickListener(new CommonRecyclerHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CategoryResult.ResultsBean bean = getDatas().get(position);
//                Toast.makeText(mContext, bean.source, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("title", bean.desc);
                intent.putExtra("url", bean.url);
                mContext.startActivity(intent);
            }
        }, R.id.category_item_layout);
    }


}
