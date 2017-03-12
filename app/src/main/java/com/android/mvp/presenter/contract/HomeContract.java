package com.android.mvp.presenter.contract;

import com.android.mvp.base.BaseViewI;
import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.HomeBanner;
import com.android.mvp.bean.HomePost;
import com.android.mvp.bean.HomeSpecial;

import java.util.List;
import java.util.Map;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/18  11:08
 */
public interface HomeContract {



    interface View extends BaseViewI<List<BrandBean>> {


        public void onPostSuccess(List<HomePost> homePosts);

        public void onBannerSuccess(List<HomeBanner> homeBanner);

        public void onSpecialSuccess(List<HomeSpecial> homeSpecial);

        public void onSpecialError(String Message,int errorTag);

    }

    interface  Presenter{
        /**
         *  首页Banner界面
         * */
        public void getHomeBanner();
        public void getBrand();
        public void getPost();
        public void getSpecial(Map<String, String> map);
    }

}
