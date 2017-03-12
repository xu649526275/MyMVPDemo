package com.android.mvp.bean;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/9  14:22
 */
public class MyUrl {
    private String webUrl;
    private String webTitle;

    public MyUrl(String webUrl, String webTitle) {
        this.webUrl = webUrl;
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }
}
