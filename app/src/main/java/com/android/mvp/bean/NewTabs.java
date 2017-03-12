package com.android.mvp.bean;

/**
 * Created by Administrator on 2016/9/25 0025.
 */

public class NewTabs{

    private String NewTabId;
    private String NewTabName;
    private String NewTabType;
    private boolean newsChannelSelect;//默认选中的
    private int newsChannelIndex;//索引
    private Boolean newsChannelFixed;//选中的

    public String getNewTabId() {
        return NewTabId;
    }

    public void setNewTabId(String newTabId) {
        NewTabId = newTabId;
    }

    public String getNewTabName() {
        return NewTabName;
    }

    public void setNewTabName(String newTabName) {
        NewTabName = newTabName;
    }

    public String getNewTabType() {
        return NewTabType;
    }

    public void setNewTabType(String newTabType) {
        NewTabType = newTabType;
    }

    public boolean isNewsChannelSelect() {
        return newsChannelSelect;
    }

    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }

    public int getNewsChannelIndex() {
        return newsChannelIndex;
    }

    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }

    public Boolean getNewsChannelFixed() {
        return newsChannelFixed;
    }

    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }
}
