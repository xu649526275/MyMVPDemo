package com.android.mvp.ui.wx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.dalong.MyRecyclerView;
import com.android.dalong.MyRefLayout;
import com.android.dalong.loadview.LoadView;
import com.android.mvp.R;
import com.android.mvp.base.BaseFragment;
import com.android.mvp.bean.WXItemBean;
import com.android.mvp.event.WxSearchEvent;
import com.android.mvp.presenter.WxPresenter;
import com.android.mvp.presenter.contract.WxContract;
import com.android.mvp.ui.wx.adapter.WxAdapter;
import com.android.mvp.util.GeneralUtil;
import com.android.mvp.util.ToastShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 微信列表
 *
 * @autor 徐文龙
 * @time 2016/9/19  16:11
 */
public class FragmentWx extends BaseFragment<WxPresenter,List<WXItemBean>> implements WxContract.View {

    private int pageSize=20;
    private int pageIndex=1;

    @Bind(R.id.wx_recycler)
    MyRecyclerView wxRecycler;
    @Bind(R.id.load_more_list_view_ptr_frame)
    MyRefLayout loadMoreListViewPtrFrame;
    private String searchStr;
    private WxAdapter mWxAdapter;

    private List<WXItemBean> mWXItemBeanList=new ArrayList<WXItemBean>();



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


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_wx;
    }

    @Override
    protected void initData() {
        showProgress();
        mWXItemBeanList.clear();
        pageIndex=1;
        loadData();

    }


    /**
     * 微信精选
     * */
    public void loadData(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("num",pageSize+"");
        map.put("page",pageIndex+"");
        mPresenter.getWxHost(map);
    }




    /**
     * 微信精选搜索
     * */
    public void loadSearchData(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("num",pageSize+"");
        map.put("page",pageIndex+"");
        map.put("word",searchStr);
        mPresenter.getWxHostSearch(map);
    }

    @Override
    protected void initView(View RootView) {
        mPresenter.attachView(this,getActivity().getApplicationContext());

        loadMoreListViewPtrFrame.initDefRefresh();
        loadMoreListViewPtrFrame.setRefresh(new MyRefLayout.PtrRefresh() {
            @Override
            public void ptrrefresh(com.android.dalong.ptr.PtrFrameLayout frame) {
                pageIndex = 1;
                searchStr=null;
                loadData();
            }
        });


        mWxAdapter=new WxAdapter(getActivity(),mWXItemBeanList);
        wxRecycler.setAdapter(mWxAdapter);

        wxRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        wxRecycler.setLoadView(getActivity(), pageSize, new LoadView.OnLoadRecyclerListener() {
            @Override
            public void LoadPage(View view) {
                if(!GeneralUtil.isEmpty(searchStr)){
                    loadSearchData();
                }else{
                    loadData();
                }
            }
        });


    }

    @Override
    protected void initToolbar() {

    }

    @Override
    public WxPresenter getPresenter() {
        return new WxPresenter();
    }




    @Override
    public void showDataSuccess(List<WXItemBean> datas) {
        super.showDataSuccess(datas);
        if(datas!=null){
            if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
                mWXItemBeanList.clear();
                loadMoreListViewPtrFrame.refreshComplete();
            }
            mWXItemBeanList.addAll(datas);
            if (datas.size() < pageSize) {
                wxRecycler.loadTheEnd();
            } else {
                wxRecycler.loadSuccess();
            }
            mWxAdapter.setData(mWXItemBeanList);
            pageIndex++;
        }
    }

    @Override
    public void showDataError(String errorMessage, int tag) {
        super.showDataError(errorMessage, tag);
        if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
            loadMoreListViewPtrFrame.refreshComplete();
        }

        if (mWXItemBeanList.size() > 0&&pageIndex!=1) {
            wxRecycler.loadNetWorkError(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wxRecycler.loadLoading();
                    if(!GeneralUtil.isEmpty(searchStr)){
                        loadSearchData();
                    }else{
                        loadData();
                    }
                }
            });
        }else{
            ToastShow.getInstance(getActivity().getApplicationContext()).toastShow(errorMessage);
        }
    }


    @Subscribe
    public void onSearchEvent(WxSearchEvent event){
        showProgress();
        searchStr=event.searchStr;
        pageIndex=1;
        loadSearchData();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView()
                    .setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}
