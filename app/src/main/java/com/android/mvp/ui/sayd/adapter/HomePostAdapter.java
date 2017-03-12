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
import com.android.mvp.bean.HomePost;
import com.android.mvp.util.GeneralUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 热帖推荐
 * 靓贴推荐
 *
 * @autor 徐文龙
 * @time 2016/7/18  11:42
 */
public class HomePostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context context;
    private List<HomePost> mHomePosts = new ArrayList<HomePost>();

    private OnBoardIdItemLinstener mOnBoardIdItemLinstener;

    public void setOnBoardIdItemLinstener(OnBoardIdItemLinstener onBoardIdItemLinstener) {
        mOnBoardIdItemLinstener = onBoardIdItemLinstener;
    }

    public HomePostAdapter(Context context) {
        this.context = context;
    }

    public void refData(List<HomePost> homePostList) {
        this.mHomePosts = homePostList;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_borad, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;

        HomePost post=mHomePosts.get(position);
        if(post==null){
            return;
        }

        myHolder.mItemHomePostTitle.setText(post.getTitle());

        myHolder.mItemHomePostBtn.setText(post.getBoardName());




        if(!GeneralUtil.isEmpty(post.getCoverImg())){
            myHolder.mItemHomePostImg.setVisibility(View.VISIBLE);
            Glide.with(context).load(post.getCoverImg()).error(R.mipmap.home_board_default).placeholder(R.mipmap.home_board_default).into(myHolder.mItemHomePostImg);
        }else{
            myHolder.mItemHomePostImg.setVisibility(View.GONE);
        }




        myHolder.myLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBoardIdItemLinstener.onBoardIdItemClick(post.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mHomePosts.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_home_post_title)
        TextView mItemHomePostTitle;
        @Bind(R.id.item_home_post_btn)
        TextView mItemHomePostBtn;
        @Bind(R.id.item_home_post_img)
        ImageView mItemHomePostImg;
        @Bind(R.id.myLine)
        LinearLayout myLine;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public interface  OnBoardIdItemLinstener{
        public void onBoardIdItemClick(String boardId);
    }
}
