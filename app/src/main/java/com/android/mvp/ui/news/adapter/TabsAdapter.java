package com.android.mvp.ui.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.bean.NewTabs;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/15.
 */

public class TabsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context context;
    private List<NewTabs> mTabs;

    private  OnMyTabItemLinstener onMyTabItemLinstener;
    private  OnMoreTabItemLinstener onMoreTabItemLinstener;

    public void setOnMyTabItemLinstener(OnMyTabItemLinstener onMyTabItemLinstener) {
        this.onMyTabItemLinstener = onMyTabItemLinstener;
    }



    public void remove(NewTabs tabs,int position){
        this.notifyItemRemoved(position);
        mTabs.remove(tabs);
        this.notifyItemRangeChanged(position,mTabs.size());
    }


    public void add(NewTabs tabs,int position){
        this.notifyItemInserted(position);
        mTabs.add(tabs);
        this.notifyItemRangeChanged(position,mTabs.size());
    }

    public void setOnMoreTabItemLinstener(OnMoreTabItemLinstener onMoreTabItemLinstener) {
        this.onMoreTabItemLinstener = onMoreTabItemLinstener;
    }

    public TabsAdapter(Context context, List<NewTabs> mTabs) {
        this.context = context;
        this.mTabs = mTabs;
    }

    public void setData(List<NewTabs> mTabs) {
        this.mTabs = mTabs;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newstab, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewTabs bean = mTabs.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (bean != null) {
            myViewHolder.itemTabText.setText(bean.getNewTabName());
        }
        myViewHolder.itemTabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onMyTabItemLinstener!=null){
                    onMyTabItemLinstener.onMyTabClick(bean,position);
                }
                if(onMoreTabItemLinstener!=null){
                    onMoreTabItemLinstener.onMoreTabClick(bean,position);
                }
            }
        });

//        myViewHolder.itemTabText.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if(onMyTabItemLinstener!=null){
//                    onMyTabItemLinstener.onMyTabLongClick(bean,position);
//                }
//                return false;
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mTabs.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_tab_text)
        TextView itemTabText;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnMyTabItemLinstener{
        public void onMyTabClick(NewTabs bean,int position);
//        public void onMyTabLongClick(NewTabs bean,int position);
    }

    public interface OnMoreTabItemLinstener{
        public void onMoreTabClick(NewTabs bean,int position);

    }
}
