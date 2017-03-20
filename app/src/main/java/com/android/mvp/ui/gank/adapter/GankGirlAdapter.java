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
import com.android.mvp.util.ImageLoader;
import com.android.mvp.util.MLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    private static final String TAG = "GankGirlAdapter";

    // 图片的宽度
    private int mPhotoWidth;



    public GankGirlAdapter(Context context, List<GankItemBean> gankItemBeen) {
        this.context = context;
        mGankItemBeen = gankItemBeen;
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int marginPixels = context.getResources().getDimensionPixelOffset(R.dimen.photo_margin_width);
        mPhotoWidth = widthPixels / 2 - marginPixels;
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
        int photoHeight = ImageLoader.calcPhotoHeight(bean.getPixel(), mPhotoWidth);//根据缩放比率，求出宽高

        final ViewGroup.LayoutParams params = myHolder.mIvGirl.getLayoutParams();
        params.width=mPhotoWidth;
        params.height=photoHeight;
        myHolder.mIvGirl.setLayoutParams(params);
        Glide.with(context).load(bean.getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().placeholder(R.mipmap.defalt_img).into(myHolder.mIvGirl);
//        ImageLoader.loadFitCenter(context,bean.getUrl(),myHolder.mIvGirl,R.mipmap.defalt_img);
//        Picasso.with(context).load(bean.getUrl()).placeholder(R.color.content_color).error(R.color.content_color).into(myHolder.mIvGirl);
        MLog.v(TAG,bean.getPixel()+  "        mPhotoWidth"+mPhotoWidth+"             height"+photoHeight);

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
