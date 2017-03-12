package com.android.mvp.presenter.contract;

import com.android.mvp.base.BaseViewI;
import com.android.mvp.bean.NewTabs;
import com.android.mvp.bean.WYNewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public interface NewsContract {
    /**
     * 新闻总界面的视图View
     * 主要负责初始化tab数据库和查询tab
     * */
    public interface View extends BaseViewI<List<NewTabs>>{

    };

    /**
     * 新闻总界面的视图Presenter
     * 主要负责初始化tab数据库和查询tab
     * */
    public interface Presenter{
        public void initTabs();
        public void queryTabs();
    }


    /**
     * tab编辑界面的 Presenter
     * */
    public interface TAbView extends BaseViewI<List<NewTabs>>{

        public void showMoreTabSuccess(List<NewTabs> moreTabs);
        public void onMyItemClick(NewTabs tabs,int position);
        public void onMoreItemClick(NewTabs tabs,int position);
    };



    /**
     * tab编辑界面的 Presenter
     * */
    public interface TabPresenter{
        public void getMyTabs();
        public void getMoreTabs();
        public void updateMyItemClick(NewTabs tabs, int position);
        public void updateMoreItemClick(NewTabs tabs, int position);
    }



    /**
     * 新闻内容列表Fragmment的View
     * */
    public interface NewsView extends  BaseViewI<List<WYNewsBean>>{

    };
    /**
     * 新闻内容列表Fragmment的Presenter
     * */
    public interface NewByIdPresenterI{
        public void getNewById(String type,String id,int pageIndex);

    }

}
