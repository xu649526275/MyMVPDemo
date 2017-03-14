package com.android.mvp.presenter;

import com.android.mvp.base.BasePresenter;
import com.android.mvp.bean.WYNewsBean;
import com.android.mvp.http.WyConstantValues;
import com.android.mvp.model.NewModel;
import com.android.mvp.presenter.contract.NewsContract;
import com.android.mvp.util.DateUtil;
import com.android.mvp.util.MLog;
import com.android.mvp.util.RxUtil;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/10/10  14:55
 */
public class NewByIdPresenter extends BasePresenter<NewsContract.NewsView > implements NewsContract.NewByIdPresenterI {


    private NewModel mModel;


    public NewByIdPresenter() {
        this.mModel=new NewModel();
    }

    @Override
    public void getNewById(String type,String id,int pageIndex) {
        MLog.v("DaLongNews","进入了"+type+"    "+id+"    "+pageIndex);

        mModel.getNewsList(type,id,pageIndex).flatMap(new Func1<Map<String, List<WYNewsBean>>, Observable<WYNewsBean>>() {
            @Override
            public Observable<WYNewsBean> call(Map<String, List<WYNewsBean>> map) {
                if (id.endsWith(WyConstantValues.HOUSE_ID)) {
                    // 房产实际上针对地区的它的id与返回key不同
                    return Observable.from(map.get("北京"));
                }
                return Observable.from(map.get(id));
            }
        }) .map(new Func1<WYNewsBean, WYNewsBean>() {
                    @Override
                    public WYNewsBean call(WYNewsBean wyNewsBean) {
                        String ptime = DateUtil.formatDate(wyNewsBean.getPtime());
                        wyNewsBean.setPtime(ptime);
                        return wyNewsBean;
                    }
                })
                .distinct()
        .toSortedList(new Func2<WYNewsBean, WYNewsBean, Integer>() {
            @Override
            public Integer call(WYNewsBean wyNewsBean, WYNewsBean wyNewsBean2) {
                return wyNewsBean2.getPtime().compareTo(wyNewsBean.getPtime());
            }
        })
                .compose(RxUtil.<List<WYNewsBean>>rxSchedulerHelper()).subscribe(new Subscriber<List<WYNewsBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                MLog.v("DaLongNews","异常"+e.getMessage());
            }

            @Override
            public void onNext( List<WYNewsBean> stringListMap) {
//                if(id.equals()))
                mView.showDataSuccess(stringListMap);
            }
        });

    }
}
