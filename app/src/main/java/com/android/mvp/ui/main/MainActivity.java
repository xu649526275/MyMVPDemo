package com.android.mvp.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.android.mvp.R;
import com.android.mvp.base.BaseActivity;
import com.android.mvp.event.WxSearchEvent;
import com.android.mvp.widget.view.MyToolbar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity {


    @Bind(R.id.my_fragment)
    FrameLayout myFragment;

    @Bind(R.id.mytoolbar)
    MyToolbar mMyToolbar;
    @Bind(R.id.my_drawer)
    DrawerLayout mMyDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    @Bind(R.id.navigation)
    NavigationView mNavigationView;
    @Bind(R.id.view_search)
    MaterialSearchView mSearchView;
    private FragmentManager mFragmentManager;
    private MenuItem mLastMenuItem;
    private FragmentFactory mFragmentFactory;

    private MyToolbar.Builder mBuilder;
    private MenuItem mSearchMenuItem;




    @Override
    public int getLayoutId() {
        return R.layout.activity_main;


    }

    @Override
    protected void initToolbar() {
        mBuilder=new MyToolbar.Builder(mMyToolbar).setTitle("首页");
        mDrawerToggle = new ActionBarDrawerToggle(this, mMyDrawer, mMyToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mMyDrawer.addDrawerListener(mDrawerToggle);


    }

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentFactory = new FragmentFactory(mFragmentManager, this);
        switchContent(R.id.drawer_dp);
        mLastMenuItem = mNavigationView.getMenu().findItem(R.id.drawer_dp);
        mNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.drawer_dp:
                    switchContent(R.id.drawer_dp);
                    mBuilder.setTitle("首页");
                    break;
//                case R.id.drawer_zhihu:
//                    switchContent(R.id.drawer_zhihu);
//                    break;
                case R.id.drawer_gank:
                    switchContent(R.id.drawer_gank);
                    mBuilder.setTitle("干货集中营");
                    break;
                case R.id.drawer_wechat:
                    switchContent(R.id.drawer_wechat);
                    mBuilder.setTitle("微信精选");
                    break;
                case R.id.drawer_news:
                    switchContent(R.id.drawer_news);
                    mBuilder.setTitle("网易新闻");
                    break;
                case R.id.drawer_video:
//                    switchContent(R.id.drawer_video);
                    break;
                case R.id.drawer_setting:
                    break;
                case R.id.drawer_like:
                    break;
                case R.id.drawer_about:
                    break;
            }
            if(mLastMenuItem!=null){
                mLastMenuItem.setChecked(false);
            }
            mLastMenuItem = item;
            item.setChecked(true);
            return true;
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                EventBus.getDefault().post(new WxSearchEvent(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }



    @Override
    protected void initData() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        item.setVisible(false);
        mSearchView.setMenuItem(item);
        mSearchMenuItem = item;
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * fragment切换
     */
    public Fragment switchContent(int resId) {

        Fragment fragment = (Fragment) mFragmentFactory.instantiateItem(
                myFragment, resId);
        mFragmentFactory.setPrimaryItem(myFragment, 0, fragment);
        mFragmentFactory.finishUpdate(myFragment);
        return fragment;
    }
}