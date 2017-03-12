package com.android.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/9/25 0025.
 */

public class MyConfig {

    public static final String ConfigName="dalong";


    private DaLongSharePreferences sharedPreferences;

    private static MyConfig config;
    private Context context;


    public static   MyConfig  getInstance(Context context){
        if(config==null){
            config=new MyConfig(context);
        }
        return config;
    }

    public void setNewTab(boolean flag) {
        sharedPreferences.setNewTab(flag);
    }

    public boolean isNewTab() {
        return sharedPreferences.isNewTab();
    }




    public MyConfig(Context context) {
        this.context = context;
        sharedPreferences=new DaLongSharePreferences(context);
    }

    public class DaLongSharePreferences{


        public static final String IS_NewTab= "isNewTab";

        private SharedPreferences sharedPreferences;

        public DaLongSharePreferences(Context context) {
            this.sharedPreferences =context.getSharedPreferences(ConfigName,Context.MODE_APPEND);
        }



        public void setNewTab(boolean flag) {
            sharedPreferences.edit().putBoolean(IS_NewTab, flag).commit();
        }

        public boolean isNewTab() {
            return sharedPreferences.getBoolean(IS_NewTab, false);
        }


    }

}
