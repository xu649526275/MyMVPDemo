package com.android.mvp.base;

import com.android.mvp.http.Http;
import com.android.mvp.util.NetUtil;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class BaseModel  {


    public String getCache() {
            return NetUtil.isNetworkAvailable() ? Http.CACHE_CONTROL_AGE : Http.CACHE_CONTROL_CACHE;
    }
}
