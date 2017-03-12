package com.android.mvp.ui.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.bean.GankItemBean;
import com.android.mvp.util.GeneralUtil;
import com.android.mvp.widget.view.MyCardView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/21  14:27
 */
public class GankAndroidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<GankItemBean> mGankItemBeen;

    public GankAndroidAdapter(Context context, List<GankItemBean> gankItemBeen) {
        this.context = context;
        mGankItemBeen = gankItemBeen;
    }


    public void setData(List<GankItemBean> gankItemBeen) {
        this.mGankItemBeen = gankItemBeen;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gank_tech, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyHolder myHolder= (MyHolder) holder;



        GankItemBean bean=mGankItemBeen.get(position);
        if(bean!=null){
            if(!GeneralUtil.isEmpty(bean.getDesc()))
            myHolder.mTvTechTitle.setText(bean.getDesc());
            if(!GeneralUtil.isEmpty(bean.getWho()))
            myHolder.mTvTechAuthor.setText(bean.getWho());
//            String date = bean.getPublishedAt();
//            if(date!=null){
//
//            }
//            int idx = date.indexOf(".");
//            date = date.substring(0,idx).replace("T"," ");
//            myHolder.mTvTechTime.setText(DateUtil.formatDateTime(date,true));
        }


    }

    @Override
    public int getItemCount() {
        return mGankItemBeen.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_tech_icon)
        ImageView mIvTechIcon;
        @Bind(R.id.tv_tech_title)
        TextView mTvTechTitle;
        @Bind(R.id.tv_tech_author)
        TextView mTvTechAuthor;
        @Bind(R.id.tv_tech_time)
        TextView mTvTechTime;
        @Bind(R.id.cv_tech_content)
        MyCardView mCvTechContent;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
