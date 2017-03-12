package com.android.mvp.ui.sayd.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.dalong.MyRecyclerView;
import com.android.dalong.MyRefLayout;
import com.android.dalong.loadview.LoadView;
import com.android.mvp.R;
import com.android.mvp.base.BaseFragment;
import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.HomeBanner;
import com.android.mvp.bean.HomePost;
import com.android.mvp.bean.HomeSpecial;
import com.android.mvp.presenter.HomePresenter;
import com.android.mvp.presenter.contract.HomeContract;
import com.android.mvp.ui.sayd.adapter.HomeBannerAdapter;
import com.android.mvp.ui.sayd.adapter.HomeBarandAdapter;
import com.android.mvp.ui.sayd.adapter.HomePostAdapter;
import com.android.mvp.ui.sayd.adapter.HomeSpecialAdapter;
import com.android.mvp.util.FullyGridLayoutManager;
import com.android.mvp.util.FullyLinearLayoutManager;
import com.android.mvp.util.GeneralUtil;
import com.android.mvp.util.ToastShow;
import com.android.mvp.widget.view.AutoPlayViewPager;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/18  14:36
 */
public class FragmentSAYDHome extends BaseFragment<HomePresenter, List<BrandBean>> implements HomeContract.View {


    @Bind(R.id.home_special_recycler)
    MyRecyclerView mHomeSpecialRecycler;//精选专题，可以加载更多的
    @Bind(R.id.load_more_list_view_ptr_frame)
    MyRefLayout loadMoreListViewPtrFrame;

    AutoPlayViewPager mMyViewPager;

    private LinearLayout mViewpagerLView;
    RecyclerView mHomeBarandRecycler;//handerView的
    RecyclerView mHomeBoradRecycler;//handerView的社区RecyclerView


    private HomeBarandAdapter barandAdapter;//分类
    private HomePostAdapter mBoradAdapter;//社区
    private HomeSpecialAdapter mSpecialAdapter;//精选专题



    private List<HomeBanner> mBannerDatas;

    private List<BrandBean> mBrandDatas = new ArrayList<BrandBean>();//分类

    private List<HomePost> mHomePosts = new ArrayList<HomePost>();//社区

    private List<HomeSpecial> mHomeSpecials = new ArrayList<HomeSpecial>();//精选专题
    private int pageSize = 5;
    private int pageIndex = 1;
    private View mHeadView;



    @Override
    protected void initView(View RootView) {
        mPresenter.attachView(this,getActivity());
        initSpecialView();
        showProgress();
    }

    @Override
    protected void initData() {
        mPresenter.getHomeBanner();
        mPresenter.getBrand();
        mPresenter.getPost();

        initDataSpecial();

    }


    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }



    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_home;
    }




    @Override
    protected View getLoadingTargetView() {
        return loadMoreListViewPtrFrame;
    }









    /**
     * 初始化专题推荐
     */
    private void initSpecialView() {

        loadMoreListViewPtrFrame.initDefRefresh();
        loadMoreListViewPtrFrame.setRefresh(new MyRefLayout.PtrRefresh() {
            @Override
            public void ptrrefresh(com.android.dalong.ptr.PtrFrameLayout frame) {
                pageIndex = 1;
                mPresenter.getHomeBanner();
                mPresenter.getBrand();
                mPresenter.getPost();
                initDataSpecial();
            }
        });

        /**
         * 初始化headerView的 banner,分类，和社区热帖
         * */
        mHeadView = LinearLayout.inflate(getActivity(), R.layout.view_home_heard, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initBoradView(mHeadView);
        initBarandView(mHeadView);
        initBannerView(mHeadView);
        mHeadView.setLayoutParams(lp);



        mSpecialAdapter = new HomeSpecialAdapter(getActivity());
        mSpecialAdapter.refData(mHomeSpecials);
        mHomeSpecialRecycler.setAdapter(mSpecialAdapter);
        mHomeSpecialRecycler.setItemAnimator(new DefaultItemAnimator());
        mHomeSpecialRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeSpecialRecycler.setLoadView(getActivity(), pageSize, new LoadView.OnLoadRecyclerListener() {
            @Override
            public void LoadPage(View view) {
                initDataSpecial();
            }
        });

        mHomeSpecialRecycler.addHeaderView(mHeadView);//把headerView加入

    }


    public void initDataSpecial() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("pageIndex", pageIndex + "");
        map.put("pageSize", pageSize + "");
        mPresenter.getSpecial(map);
    }







    /**
     * 初始化社区热帖
     */
    private void initBoradView(View view) {
        mHomeBoradRecycler = (RecyclerView) view.findViewById(R.id.home_borad_recycler);
        mBoradAdapter = new HomePostAdapter(getActivity());
        mHomeBoradRecycler.setAdapter(mBoradAdapter);
        FullyLinearLayoutManager lm = new FullyLinearLayoutManager(getActivity());
        mHomeBoradRecycler.setLayoutManager(lm);
        mBoradAdapter.setOnBoardIdItemLinstener(new HomePostAdapter.OnBoardIdItemLinstener() {
            @Override
            public void onBoardIdItemClick(String boardId) {

            }
        });

    }

    /**
     * 初始化食品分类
     */
    private void initBarandView(View view) {
        mHomeBarandRecycler = (RecyclerView) view.findViewById(R.id.home_barand_recycler);
        barandAdapter = new HomeBarandAdapter(getActivity());
        mHomeBarandRecycler.setAdapter(barandAdapter);
        FullyGridLayoutManager gm = new FullyGridLayoutManager(getActivity(), 3);
        mHomeBarandRecycler.setLayoutManager(gm);
    }

    /**
     * 初始化Banner的View
     */
    private void initBannerView(View view) {
        mMyViewPager = (AutoPlayViewPager) view.findViewById(R.id.myViewPager);
        int windowwidth = GeneralUtil.getWindowWidth(getActivity());
        float sc = windowwidth / 750f;
        float imageHeight = 330 * sc;
        mMyViewPager.getLayoutParams().width = windowwidth;
        mMyViewPager.getLayoutParams().height = (int) imageHeight;

        mViewpagerLView = (LinearLayout) view.findViewById(R.id.recommend_viewpage_lin);
        mMyViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mViewpagerLView.getChildCount(); i++) {
                    ImageView img = (ImageView) mViewpagerLView
                            .getChildAt(i);
                    if (i != position % 3) {
                        img.setImageResource(R.mipmap.viewpage_noselected);
                    } else {
                        img.setImageResource(R.mipmap.viewpage_selected);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }






    /**
     * 得到社区数据
     */
    @Override
    public void onPostSuccess(List<HomePost> homePosts) {
        if (homePosts != null) {
            this.mHomePosts = homePosts;
            mBoradAdapter.refData(mHomePosts);
        }
    }


    /**
     * 得到banner数据
     */
    private ImageView img;
    @Override
    public void onBannerSuccess(List<HomeBanner> homeBanner) {
        if (homeBanner != null) {
            mBannerDatas = homeBanner;
            ArrayList<ImageView> views = new ArrayList<ImageView>();
            for (int i = 0; i < mBannerDatas.size(); i++) {
                View view = LayoutInflater.from(getActivity())
                        .inflate(R.layout.view_fragment_home_banner_img,
                                null);
                img = (ImageView) view
                        .findViewById(R.id.product_info_img);
                final int finalI = i;

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                String imgUrl = mBannerDatas.get(i).getUrl();

                if (!GeneralUtil.isEmpty(imgUrl)) {
                    Glide.with(getActivity())
                            .load(imgUrl)
                            .error(R.mipmap.home_banner_default)
                            .placeholder(R.mipmap.home_banner_default)
                            .into(img);
                }

                views.add(img);
            }
            mMyViewPager.setAdapter(new HomeBannerAdapter(views));

            mMyViewPager.start();
        }
    }


    /**
     * 得到精选专题数据
     */
    @Override
    public void onSpecialSuccess(List<HomeSpecial> homeSpecial) {
        hideProgress();
        if (homeSpecial != null) {
            if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
                mHomeSpecials.clear();
                loadMoreListViewPtrFrame.refreshComplete();
            }

            mHomeSpecials.addAll(homeSpecial);
            mSpecialAdapter.refData(mHomeSpecials);
            if (homeSpecial.size() < pageSize) {
                mHomeSpecialRecycler.loadTheEnd();
            } else {
                mHomeSpecialRecycler.loadSuccess();
            }
            pageIndex++;
        }
    }

    /**
     * 精选专题失败
     * */
    @Override
    public void onSpecialError(String Message, int errorTag) {

        if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
            loadMoreListViewPtrFrame.refreshComplete();
        }
        if (errorTag == 7) {
            if (mHomeSpecials.size() > 0) {
                mHomeSpecialRecycler.loadTheEnd();
            }
        } else {
            if (mHomeSpecials.size() > 0) {
                mHomeSpecialRecycler.loadNetWorkError(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mHomeSpecialRecycler.loadLoading();
                        initDataSpecial();
                    }
                });

            }

        }
    }

    /**
     * 得到分类成功
     * */
    @Override
    public void showDataSuccess(List<BrandBean> datas) {
        if (datas != null) {
            mBrandDatas = datas;
            this.barandAdapter.refData(mBrandDatas);
        }
    }

    /**
     * 其他失败
     * */
    @Override
    public void showDataError(String errorMessage, int tag) {
        super.showDataError(errorMessage, tag);
        ToastShow.getInstance(getActivity().getApplicationContext()).toastShow(errorMessage);
    }






    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView()
                    .setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

}
