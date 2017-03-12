package com.android.mvp.ui.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.bean.WYNewsBean;
import com.android.mvp.ui.news.activity.NewsDetialActivity;
import com.android.mvp.util.DimenUtil;
import com.android.mvp.util.GeneralUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/10/10  15:39
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context context;
    private List<WYNewsBean> newsList;
    private final int PhotoItem = 1;
    private final int NewsItem = 2;


    public NewsAdapter(Context context, List<WYNewsBean> newsList) {
        this.context = context;
        this.newsList = newsList;
    }


    public void setData( List<WYNewsBean> newsList){
        this.newsList = newsList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (!GeneralUtil.isEmpty(newsList.get(position).getDigest())) {
            return NewsItem;
        } else {
            return PhotoItem;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if (viewType == NewsItem) {
            view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
            return new MyItemHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_news_photo, parent, false);
            return new PhotoItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyItemHolder) {
            setNewsView(holder, position);
        } else if (holder instanceof PhotoItemHolder) {
            setPhotoView(holder, position);
        }
    }

    private void setNewsView(RecyclerView.ViewHolder holder, int position) {
        MyItemHolder myholder = (MyItemHolder) holder;
        WYNewsBean bean = newsList.get(position);
        String title = bean.getTitle();
        myholder.mbeanTitleTv.setText(title);
        myholder.mbeanDigestTv.setText(bean.getDigest());
        myholder.mbeanPtimeTv.setText(bean.getPtime());
        Glide.with(context).load(bean.getImgsrc()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL).
                placeholder(R.mipmap.defalt_img).
                error(R.mipmap.defalt_img).
                into(myholder.mbeanPhotoIv);

        myholder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetialActivity.class);
                intent.putExtra("postId",bean.getPostid());
                intent.putExtra("img", bean.getImgsrc());
                context.startActivity(intent);
            }
        });
    }

    private void setPhotoView(RecyclerView.ViewHolder holder, int position) {
        PhotoItemHolder myholder = (PhotoItemHolder) holder;
        WYNewsBean bean = newsList.get(position);

        myholder.mbeanPtimeTv.setText(bean.getPtime());
        String imgSrcLeft = null;
        String imgSrcMiddle = null;
        String imgSrcRight = null;

        int PhotoThreeHeight = (int) DimenUtil.dp2px(context, 90);
        int PhotoTwoHeight = (int) DimenUtil.dp2px(context, 120);
        int PhotoOneHeight = (int) DimenUtil.dp2px(context, 150);
        ViewGroup.LayoutParams layoutParams = ((PhotoItemHolder) holder).mbeanPhotoIvGroup.getLayoutParams();


        if(bean.getAds()!=null){
            List<WYNewsBean.AdsBean> adsBeanList = bean.getAds();
            if(GeneralUtil.isEmpty(adsBeanList.get(0).getTitle())){
                myholder.mbeanTitleTv.setVisibility(View.GONE);
            }else{
                myholder.mbeanTitleTv.setVisibility(View.VISIBLE);
                myholder.mbeanTitleTv.setText(adsBeanList.get(0).getTitle());
            }

            int size = adsBeanList.size();
            if (size >= 3) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                imgSrcRight = adsBeanList.get(2).getImgsrc();
                layoutParams.height = PhotoThreeHeight;
            } else if (size >= 2) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        }else if (bean.getImgextra() != null) {
            myholder.mbeanTitleTv.setVisibility(View.GONE);
            int size = bean.getImgextra().size();
            if (size >= 3) {
                imgSrcLeft = bean.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = bean.getImgextra().get(1).getImgsrc();
                imgSrcRight = bean.getImgextra().get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;
            } else if (size >= 2) {
                imgSrcLeft = bean.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = bean.getImgextra().get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = bean.getImgextra().get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else {
            imgSrcLeft = bean.getImgsrc();

            layoutParams.height = PhotoOneHeight;
        }

        setPhotoImageView(myholder, imgSrcLeft, imgSrcMiddle, imgSrcRight);
        myholder.mbeanPhotoIvGroup.setLayoutParams(layoutParams);

    }


    private void setPhotoImageView(PhotoItemHolder holder, String imgSrcLeft, String imgSrcMiddle, String imgSrcRight) {
        if (imgSrcLeft != null) {
            showAndSetPhoto(holder.mbeanPhotoIvLeft, imgSrcLeft);
        } else {
            hidePhoto(holder.mbeanPhotoIvLeft);
        }

        if (imgSrcMiddle != null) {
            showAndSetPhoto(holder.mbeanPhotoIvMiddle, imgSrcMiddle);
        } else {
            hidePhoto(holder.mbeanPhotoIvMiddle);
        }

        if (imgSrcRight != null) {
            showAndSetPhoto(holder.mbeanPhotoIvRight, imgSrcRight);
        } else {
            hidePhoto(holder.mbeanPhotoIvRight);
        }
    }

    private void showAndSetPhoto(ImageView imageView, String imgSrc) {
        imageView.setVisibility(View.VISIBLE);
        Glide.with(context).load(imgSrc).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.title_text_color)
                .error(R.mipmap.defalt_img)
                .into(imageView);
    }

    private void hidePhoto(ImageView imageView) {
        imageView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public class PhotoItemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.news_summary_title_tv)
        TextView mbeanTitleTv;
        @Bind(R.id.news_summary_photo_iv_left)
        ImageView mbeanPhotoIvLeft;
        @Bind(R.id.news_summary_photo_iv_middle)
        ImageView mbeanPhotoIvMiddle;
        @Bind(R.id.news_summary_photo_iv_right)
        ImageView mbeanPhotoIvRight;
        @Bind(R.id.news_summary_photo_iv_group)
        LinearLayout mbeanPhotoIvGroup;
        @Bind(R.id.news_summary_ptime_tv)
        TextView mbeanPtimeTv;

        public PhotoItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class MyItemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.news_summary_photo_iv)
        ImageView mbeanPhotoIv;
        @Bind(R.id.news_summary_title_tv)
        TextView mbeanTitleTv;
        @Bind(R.id.news_summary_digest_tv)
        TextView mbeanDigestTv;
        @Bind(R.id.news_summary_ptime_tv)
        TextView mbeanPtimeTv;
        @Bind(R.id.my_view)
        RelativeLayout myView;

        public MyItemHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
