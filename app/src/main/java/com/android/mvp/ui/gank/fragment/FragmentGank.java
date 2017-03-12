package com.android.mvp.ui.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvp.R;
import com.android.mvp.base.BaseFragment;
import com.android.mvp.ui.MyPagerAdapter;
import com.android.mvp.ui.news.fragment.FragmentNewById;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 干货Fragment
 *
 * @autor 徐文龙
 * @time 2016/9/19  16:15
 */
public class FragmentGank extends BaseFragment {


    @Bind(R.id.gank_tab)
    TabLayout mMyTab;
    @Bind(R.id.gank_viewpage)
    ViewPager mViewpager;
    private MyPagerAdapter mAdapter;
    private List<String> mTitles=new ArrayList<String>();
    private List<Fragment> mFragmentList=new ArrayList<Fragment>();



    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View RootView) {

        mViewpager.setOffscreenPageLimit(1);
        mFragmentList.add(createFragment("Android"));
        mFragmentList.add(createFragment("iOS"));
        mFragmentList.add(createFragment("前端"));
        mFragmentList.add(new FragmentGankGirl());

        mTitles.add("Android");
        mTitles.add("IOS");
        mTitles.add("前端");
        mTitles.add("美女");

        mAdapter=new MyPagerAdapter(getFragmentManager(),mFragmentList,mTitles);
        setMyTabMode(mMyTab);
        mViewpager.setAdapter(mAdapter);
        mMyTab.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);


    }


    public FragmentGankAndroid createFragment(String tech){
        FragmentGankAndroid mFragment=new FragmentGankAndroid();
        Bundle bundle=new Bundle();
        bundle.putString("tech",tech);
        mFragment.setArguments(bundle);
        return mFragment;
    }





    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_gank;
    }



    public void setMyTabMode(TabLayout tabLayout){


        if (mTitles.size()<=4) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView()
                    .setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}
