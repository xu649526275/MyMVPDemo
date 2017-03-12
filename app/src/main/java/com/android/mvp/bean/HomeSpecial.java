package com.android.mvp.bean;

import java.io.Serializable;

/**
 * 热门专题
 *
 * @autor 徐文龙
 * @time 2016/7/18  13:59
 */
public class HomeSpecial implements Serializable{

    /**
     * CoverUrl : http://shianyun-oss.oss-cn-beijing.aliyuncs.com/uploads/Community_STCover/546BE9255D455C3A3EE4DD57B8E1B06B.png
     * ShortDesc : 烦烦烦烦烦福福福福福福福福福
     * Title : 我测测测测
     * Id : 21
     */

    private String CoverUrl;
    private String ShortDesc;
    private String Title;
    private String Id;

    public String getCoverUrl() {
        return CoverUrl;
    }

    public void setCoverUrl(String CoverUrl) {
        this.CoverUrl = CoverUrl;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String ShortDesc) {
        this.ShortDesc = ShortDesc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
}
