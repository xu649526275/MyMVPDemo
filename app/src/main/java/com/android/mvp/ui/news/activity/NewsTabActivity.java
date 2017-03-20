package com.android.mvp.ui.news.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.base.BaseActivity;
import com.android.mvp.bean.NewTabs;
import com.android.mvp.db.DbUtils;
import com.android.mvp.event.DaLongEvent;
import com.android.mvp.event.TabEvent;
import com.android.mvp.presenter.NewTabPresenter;
import com.android.mvp.presenter.contract.NewsContract;
import com.android.mvp.ui.news.adapter.TabsAdapter;
import com.android.mvp.util.MLog;
import com.android.mvp.util.MyItemTouchHelper;
import com.android.mvp.util.RxBus;
import com.android.mvp.util.RxUtil;
import com.android.mvp.widget.view.MyToolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * 编辑新闻Tab的界面
 * Created by Administrator on 2016/10/15.
 */

public class NewsTabActivity extends BaseActivity<NewTabPresenter, List<NewTabs>> implements NewsContract.TAbView,TabsAdapter.OnMyTabItemLinstener,TabsAdapter.OnMoreTabItemLinstener,MyItemTouchHelper.OnRecyclerItemMoveLinestener {

    @Bind(R.id.my_text)
    TextView myText;
    @Bind(R.id.my_tabs)
    RecyclerView myTabsRecycler;//我的tab列表

    @Bind(R.id.more_tabs)
    RecyclerView moreTabsRecycler;//更多tab列表

    @Bind(R.id.my_toolbar)
    MyToolbar myToolbar;

    private List<NewTabs> myTabs=new ArrayList<NewTabs>();//我的
    private List<NewTabs> moreTabs=new ArrayList<NewTabs>();//更多
    private TabsAdapter myAdapter;
    private TabsAdapter moreAdapter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myTabs=null;
        moreTabs=null;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_tab;
    }

    @Override
    protected void initToolbar() {
        new MyToolbar.Builder(myToolbar).setLeftImg(R.mipmap.ic_arrow_back).setTitle("频道管理").setLeftImgListerner(v ->{
            EventBus.getDefault().post(new TabEvent(myTabs));
            this.finish();

        });
    }

    /**
     * 初始化View
     * */
    @Override
    protected void initView() {
        myAdapter=new TabsAdapter(this,myTabs);
        moreAdapter=new TabsAdapter(this,moreTabs);
        myAdapter.setOnMyTabItemLinstener(this);
        moreAdapter.setOnMoreTabItemLinstener(this);

        myTabsRecycler.setLayoutManager(new GridLayoutManager(this,4));
        moreTabsRecycler.setLayoutManager(new GridLayoutManager(this,4));

        myTabsRecycler.setItemAnimator(new DefaultItemAnimator());//添加默认动画
        moreTabsRecycler.setItemAnimator(new DefaultItemAnimator());

        myTabsRecycler.setAdapter(myAdapter);
        moreTabsRecycler.setAdapter(moreAdapter);

        mPresenter.attachView(this,getApplicationContext());

        ItemTouchHelper helper=new ItemTouchHelper(new MyItemTouchHelper(this));//添加我的tab长按拖动和单击删除
        helper.attachToRecyclerView(myTabsRecycler);

       RxBus.getDefault().post(new DaLongEvent("哈哈哈哈"));

    }

    //初始化数据
    @Override
    protected void initData() {
        mPresenter.getMyTabs();//
        mPresenter.getMoreTabs();
    }


    @Override
    public NewTabPresenter getPresenter() {
        return new NewTabPresenter();
    }






    /**
     * 获得更多tab的回调
     * */
    @Override
    public void showMoreTabSuccess(List<NewTabs> datas) {
        if(moreTabs!=null){
            moreTabs.clear();
            moreTabs.addAll(datas);
        }
        moreAdapter.setData(moreTabs);
    }

    /**
     * 获得我的tab的回调
     * */
    @Override
    public void showDataSuccess(List<NewTabs> datas) {
        super.showDataSuccess(datas);
        if(datas!=null){
            myTabs.clear();
            myTabs.addAll(datas);
        }
        myAdapter.setData(myTabs);
    }


    //Adapter的更多按钮回调
    @Override
    public void onMoreTabClick(NewTabs bean, int position) {
        mPresenter.updateMoreItemClick(bean,position);
    }
    //Adapter的我的按钮回调
    @Override
    public void onMyTabClick(NewTabs bean, int position) {
        mPresenter.updateMyItemClick(bean,position);
    }

    /**
     * 我的tab的长按事件监听
     * */
    @Override
    public void onMoveYidong(int position1, int position2) {
        MLog.v("DaLong", "开始移动");
        myAdapter.notifyItemMoved(position1, position2);
        Collections.swap(myTabs, position1, position2);
        DbUtils mDbUtils = new DbUtils(context);
        RxUtil.createData(mDbUtils.updateNewTab(myTabs.get(position1), myTabs.get(position2))).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> {
            if (aBoolean) {
                MLog.v("DaLong", "移动成功");
            } else {
                MLog.v("DaLong", "移动成功");
            }

        });
    }




    /**
     * 经过Presenter的回调，进行动画添加和删除
     * */

    @Override
    public void onMyItemClick(NewTabs tabs, int position) {
        MLog.v("DaLong",tabs.getNewTabName()+"    "+position);
        myAdapter.remove(tabs,position);
        moreAdapter.add(tabs,position);
    }

    @Override
    public void onMoreItemClick(NewTabs tabs, int position) {
        MLog.v("DaLong",tabs.getNewTabName()+"    "+position);
        moreAdapter.remove(tabs,position);
        myAdapter.add(tabs,position);
    }





    /**
     * 接受数据失败
     * */
    @Override
    public void showDataError(String errorMessage, int tag) {
        super.showDataError(errorMessage, tag);
    }






    /**
     * 返回监听，返回的时候发送eventbus
     * 上个界面动态更改tab
     * */
    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new TabEvent(myTabs));
        super.onBackPressed();

    }




}
