package com.android.mvp.ui.gank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.mvp.R;
import com.android.mvp.bean.GankItemBean;
import com.android.mvp.ui.gank.activity.GankGirlDetialActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/21  14:52
 */
public class GankGirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GankItemBean> mGankItemBeen;



    public GankGirlAdapter(Context context, List<GankItemBean> gankItemBeen) {
        this.context = context;
        mGankItemBeen = gankItemBeen;
    }


    public void setData(List<GankItemBean> gankItemBeen) {
        this.mGankItemBeen = gankItemBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_girl, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        GankItemBean bean=mGankItemBeen.get(position);
        Picasso.with(context).load(bean.getUrl()).placeholder(R.color.content_color).error(R.color.content_color).into(myHolder.mIvGirl);

        myHolder.mIvGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, GankGirlDetialActivity.class);
                intent.putExtra("url",bean.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGankItemBeen.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_girl)
        ImageView mIvGirl;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
