package com.android.mvp.ui.wx.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.bean.WXItemBean;
import com.android.mvp.ui.wx.activity.WxWebActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/20  14:17
 */
public class WxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context context;
    private List<WXItemBean> mWXItemBeanList;


    public WxAdapter(Context context, List<WXItemBean> WXItemBeanList) {
        this.context = context;
        this.mWXItemBeanList = WXItemBeanList;
    }

    public void setData(List<WXItemBean> WXItemBeanList) {
        this.mWXItemBeanList = WXItemBeanList;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wx, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        WXItemBean itemBean = mWXItemBeanList.get(position);
        MyHolder myHolder = (MyHolder) holder;
        if (itemBean != null) {
            Glide.with(context).load(itemBean.getPicUrl()).into(myHolder.mIvWechatItemImage);
        }
        myHolder.mTvWechatItemTitle.setText(itemBean.getTitle());
        myHolder.mTvWechatItemTime.setText(itemBean.getCtime());
        myHolder.mTvWechatItemFrom.setText(itemBean.getDescription());
        myHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, WxWebActivity.class);
                intent.putExtra("title",itemBean.getTitle());
                intent.putExtra("url",itemBean.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWXItemBeanList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_wechat_item_image)
        ImageView mIvWechatItemImage;
        @Bind(R.id.tv_wechat_item_title)
        TextView mTvWechatItemTitle;
        @Bind(R.id.tv_wechat_item_from)
        TextView mTvWechatItemFrom;
        @Bind(R.id.tv_wechat_item_time)
        TextView mTvWechatItemTime;
        @Bind(R.id.item_view)
        LinearLayout mItemView;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
