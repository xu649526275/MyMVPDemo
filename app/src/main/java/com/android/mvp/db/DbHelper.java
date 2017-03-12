package com.android.mvp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public class DbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "dalong.db";
    private static final int DB_VERSION = 1; // 添加环信用户信息表


    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context=context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DbModel.NewTable+"(_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ","+DbModel.NewId+" text," +
                DbModel.NewName+" text," +
                DbModel.NewType+" text," +
                DbModel.NewSelect+" text," +
                DbModel.NewIndex+" text," +
                DbModel.NewFixed+" text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
