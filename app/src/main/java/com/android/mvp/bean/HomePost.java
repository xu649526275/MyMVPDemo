package com.android.mvp.bean;

import java.io.Serializable;

/**
 * 首页热帖
 *
 * @autor 徐文龙
 * @time 2016/7/18  13:42
 */
public class HomePost implements Serializable {


    /**
     * CoverImg : http://shianyun-oss.oss-cn-beijing.aliyuncs.com/uploads/DianPing3.0CommunityFile/57190DF71BE15B7049EE439A1C8B2AE8.jpg
     * BoardName : 饮食养生
     * Title : 《饮食养生》全本_图文精排版_在线阅读-百度阅读
     * Id : 22
     */

    private String CoverImg;
    private String BoardName;
    private String Title;
    private String Id;

    public String getCoverImg() {
        return CoverImg;
    }

    public void setCoverImg(String CoverImg) {
        this.CoverImg = CoverImg;
    }

    public String getBoardName() {
        return BoardName;
    }

    public void setBoardName(String BoardName) {
        this.BoardName = BoardName;
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
