package com.android.mvp.bean;

import java.io.Serializable;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/7/15  15:41
 */
public class HomeBanner implements Serializable {
    /**
     * Url : http://shianyun-oss.oss-cn-beijing.aliyuncs.com/uploads/AppBanner/dpbanner2.2-3.png
     * PageUrl : http://www.baidu.com
     * PageTitle : null
     * PageDescribe : 3
     */

    private String Url;
    private String PageUrl;
    private String PageTitle;
    private String PageDescribe;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getPageUrl() {
        return PageUrl;
    }

    public void setPageUrl(String PageUrl) {
        this.PageUrl = PageUrl;
    }

    public String getPageTitle() {
        return PageTitle;
    }

    public void setPageTitle(String PageTitle) {
        this.PageTitle = PageTitle;
    }

    public String getPageDescribe() {
        return PageDescribe;
    }

    public void setPageDescribe(String PageDescribe) {
        this.PageDescribe = PageDescribe;
    }
}
