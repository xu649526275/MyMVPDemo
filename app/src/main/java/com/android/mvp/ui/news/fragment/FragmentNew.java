package com.android.mvp.ui.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.android.mvp.R;
import com.android.mvp.base.BaseFragment;
import com.android.mvp.bean.NewTabs;
import com.android.mvp.event.DaLongEvent;
import com.android.mvp.event.TabEvent;
import com.android.mvp.presenter.NewPresenter;
import com.android.mvp.presenter.contract.NewsContract;
import com.android.mvp.ui.MyPagerAdapter;
import com.android.mvp.ui.news.activity.NewsTabActivity;
import com.android.mvp.util.MLog;
import com.android.mvp.util.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import rx.functions.Action1;

/**
 *网易新闻总界面
 * Created by Administrator on 2016/9/29 0029.
 */

public class FragmentNew extends BaseFragment<NewPresenter, List<NewTabs>> implements NewsContract.View {


    @Bind(R.id.new_tab)
    TabLayout newTab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Bind(R.id.add_tabs)
    ImageView addTabs;

    private MyPagerAdapter adapter;

    private String mySelectName;
    private List<String> mTitles=new ArrayList<String>();
    private List<Fragment> mFragmentList=new ArrayList<Fragment>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private static final String TAG = "FragmentNew";

    /**
     * 通过新闻编辑界面的更改抛出的EventBus
     * 来进行接受，并且刷新界面
     *
     * */
    @Subscribe
    public void onTabEventLinsenter(TabEvent event){
        MLog.v(TAG,"开始执行");
        mPresenter.initTabs();//初始化数据库
        if(event.tabs!=null){
            suceeessData(event.tabs);
        }
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initToolbar() {

    }

    /**
     * 初始化界面，并且初始化新闻tab的数据库
     * */
    @Override
    protected void initView(View RootView) {
        mPresenter.attachView(this, getActivity());
        mPresenter.initTabs();
        addTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NewsTabActivity.class);
                startActivity(intent);
            }
        });

        mPresenter.addSubscription(RxBus.getDefault().toObservable(DaLongEvent.class).subscribe(new Action1<DaLongEvent>() {
            @Override
            public void call(DaLongEvent daLongEvent) {
                MLog.v("DaLongRxBus",daLongEvent.getName());
            }
        }));

    }

    @Override
    protected void initData() {
    }


    @Override
    public NewPresenter getPresenter() {
        return new NewPresenter();
    }


    /**
     * 根据tab的数目不同，来修改tabLayout的mode(可滑动，不可滑动)
     * */
    public void setMyTabMode(TabLayout tabLayout){

        if (mTitles.size()<=4) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }


    @Override
    public void showDataSuccess(List<NewTabs> datas) {
        super.showDataSuccess(datas);
        if(datas!=null){
//            mySelectName=datas.get(0).getNewTabName();
            suceeessData(datas);
        }
    }


    /**
     * 刷新数据
     * */
    public void suceeessData(List<NewTabs> datas){
        mFragmentList.clear();
        mTitles.clear();
        for(int i=0;i<datas.size();i++){
            MLog.v(TAG,datas.get(i).getNewTabName());
            mTitles.add(datas.get(i).getNewTabName());
            mFragmentList.add(createFragment(datas.get(i)));
        }



        adapter=new MyPagerAdapter(getFragmentManager(),mFragmentList,mTitles);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mySelectName=mTitles.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewpager.setAdapter(adapter);
        newTab.setupWithViewPager(viewpager);
        setMyTabMode(newTab);
        int position=getSelectIndex();
        viewpager.setCurrentItem(position);
        viewpager.setOffscreenPageLimit(1);

    }

    /**
     * 创建Fragment
     * */
    public FragmentNewById createFragment(NewTabs datas){
        FragmentNewById fragment=new FragmentNewById();
        Bundle bundle = new Bundle();
        bundle.putString("type", datas.getNewTabType());
        bundle.putString("id", datas.getNewTabId());
        fragment.setArguments(bundle);
        return fragment;
    }


    /**
     * 获得选中的索引
     * */
    public int getSelectIndex() {
        int position=0;
        if(mySelectName!=null){
            for(int i=0;i<mTitles.size();i++){
                if(mySelectName.equals(mTitles.get(i))){
                    position=i;
                    MLog.v(TAG,"select"+mySelectName+"   "+i);
                }
            }
        }

        return position;
    }



    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView()
                    .setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}