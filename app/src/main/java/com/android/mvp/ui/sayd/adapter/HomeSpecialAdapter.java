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
import com.android.mvp.bean.HomeSpecial;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/7/18  12:07
 */
public class HomeSpecialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeSpecial> mHomeSpecials = new ArrayList<HomeSpecial>();

    private Context context;

    public HomeSpecialAdapter(Context context) {
        this.context = context;
    }

    public void refData(List<HomeSpecial> homeSpecial) {
        this.mHomeSpecials = homeSpecial;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_special, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder holder1 = (MyHolder) holder;
        HomeSpecial special=mHomeSpecials.get(position);

        if(special==null){
            return;
        }
        Glide.with(context).load(special.getCoverUrl()).error(R.mipmap.home_special_default).placeholder(R.mipmap.home_special_default).into(holder1.mItemHomeSpecialImg);
        holder1.mItemHomeSpecialTitle.setText(special.getTitle());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder1.mMyLine.setLayoutParams(layoutParams);
        holder1.mMyLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, SpecialInfoActivity.class);
//                        intent.putExtra("id",special.getId());
//                        context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mHomeSpecials.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.myLine)
        LinearLayout mMyLine;
        @Bind(R.id.item_home_special_img)
        ImageView mItemHomeSpecialImg;
        @Bind(R.id.item_home_special_title)
        TextView mItemHomeSpecialTitle;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
