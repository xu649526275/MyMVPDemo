package com.android.mvp.ui.sayd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.M_Base;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 食品分类
 *
 * @autor 徐文龙
 * @time 2016/7/15  18:20
 */
public class HomeBarandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private List<BrandBean> mBrandDatas = new ArrayList<BrandBean>();

    public HomeBarandAdapter(Context context) {
        this.context = context;
    }

    public void refData(List<BrandBean> brandBeanList) {
        this.mBrandDatas = brandBeanList;
        this.notifyDataSetChanged();
    }

    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_barand, null);
        return new MyHolder(view);
    }

    private M_Base m_base;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolde = (MyHolder) holder;
        BrandBean brand = mBrandDatas.get(position);

        if(brand==null){
            return;
        }
        myHolde.mItemHomeBarandTitle.setText(brand.getAlias());
        Glide.with(context).load(brand.getIconUrl()).into(myHolde.mItemHomeBarandImg);
    }


    @Override
    public int getItemCount() {
        return mBrandDatas.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_home_barand_img)
        ImageView mItemHomeBarandImg;
        @Bind(R.id.item_home_barand_title)
        TextView mItemHomeBarandTitle;
        @Bind(R.id.myLine)
        LinearLayout mMyLine;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
