package com.android.mvp.ui.main;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.mvp.ui.gank.fragment.FragmentGank;
import com.android.mvp.ui.news.fragment.FragmentNew;
import com.android.mvp.ui.sayd.fragment.FragmentSAYDHome;
import com.android.mvp.ui.wx.fragment.FragmentWx;
import com.android.mvp.ui.zhihu.fragment.FragmentZhihu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class FragmentController {



    private static FragmentController fragmentController;

    private int viewId;

    private List<Fragment> fragments;

    private FragmentManager fragmentManager;


    public static FragmentController  getInstance(FragmentActivity activity,int viewId){
        if(fragmentController==null){
            fragmentController=new FragmentController(activity,viewId);
        }
        return fragmentController;
    }

    public FragmentController(FragmentActivity activity, int viewId){
        fragmentManager=activity.getSupportFragmentManager();
        this.viewId=viewId;
        initFragments();
        queryFragment(0);
    }



    private void initFragments() {
        FragmentTransaction  transaction=fragmentManager.beginTransaction();
        fragments=new ArrayList<Fragment>();
        fragments.add(new FragmentSAYDHome());
        fragments.add(new FragmentZhihu());
//        fragments.add(new FragmentGank());
        fragments.add(new FragmentWx());
        fragments.add(new FragmentNew());
        for (Fragment f:fragments){
            transaction.add(viewId,f);
        }
        transaction.commit();

    }

    public void queryFragment(int i) {
        hideFragment();
        FragmentTransaction trans=fragmentManager.beginTransaction();
        trans.show(fragments.get(i));
        trans.commit();

    }

    private void hideFragment(){
        FragmentTransaction trans=fragmentManager.beginTransaction();
        for(Fragment f:fragments){
            trans.hide(f);
        }
        trans.commit();
    }


}
