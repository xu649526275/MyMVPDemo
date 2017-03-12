package com.android.mvp.event;

import com.android.mvp.bean.NewTabs;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class TabEvent {

    public List<NewTabs> tabs;

    public TabEvent(List<NewTabs> tabs) {
        this.tabs = tabs;
    }
}
