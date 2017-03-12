package com.android.mvp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.mvp.R;
import com.android.mvp.bean.NewTabs;
import com.android.mvp.http.WyConstantValues;
import com.android.mvp.util.MLog;
import com.android.mvp.util.MyConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27 0027.
 */

public class DbUtils {

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase mDb;


    public DbUtils(Context context) {
        this.context = context;
        mDb=new DbHelper(context).getWritableDatabase();
        dbHelper=new DbHelper(context);
    }



    public  boolean initTab(){
        boolean flag=false;
        if (!MyConfig.getInstance(context).isNewTab()){
        List<String> channelName = Arrays.asList(context.getApplicationContext().getResources()
                .getStringArray(R.array.news_channel_name));
        List<String> channelId = Arrays.asList(context.getApplicationContext().getResources()
                .getStringArray(R.array.news_channel_id));
        mDb.beginTransaction();
        for (int i = 0; i < channelName.size(); i++) {
            NewTabs entity =new NewTabs();
            entity.setNewTabId(channelId.get(i));
            entity.setNewTabName(channelName.get(i));
            entity.setNewTabType(WyConstantValues.getType(channelId.get(i)));
            entity.setNewsChannelSelect(i<=4);
            entity.setNewsChannelIndex(i);
            entity.setNewsChannelFixed(i==0);
            flag=insertNewTab(entity);
        }
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        MyConfig.getInstance(context).setNewTab(flag);
        }
        return flag;
    }


    public boolean insertNewTab(NewTabs newTabs){
    //    MLog.v("DaLongs添加中",newTabs.isNewsChannelSelect()==true?"1":"0");
        ContentValues contentValues=new ContentValues();
        contentValues.put(DbModel.NewId,newTabs.getNewTabId());
        contentValues.put(DbModel.NewName,newTabs.getNewTabName());
        contentValues.put(DbModel.NewType,newTabs.getNewTabType());
        contentValues.put(DbModel.NewSelect,newTabs.isNewsChannelSelect()==true?"1":"0");
        contentValues.put(DbModel.NewIndex,newTabs.getNewsChannelIndex());
        contentValues.put(DbModel.NewFixed,newTabs.getNewsChannelFixed()==true?"1":"0");
        boolean flag=false;
        try {
            mDb.insertOrThrow(DbModel.NewTable, null, contentValues);
            flag=true;
        } catch (Exception e) {
            // TODO: handle exception
            flag=false;
        }
        MLog.v("DaLong","单个添加"+flag+"");
        return flag;
    }


    public List<NewTabs> queryAllNewTabs(){

        String sql="select * from "+DbModel.NewTable;
        Cursor c=mDb.rawQuery(sql,null);
        if (c == null) {
            throw new RuntimeException("Can't get proper result for ");
        }
        List<NewTabs> tabs=new ArrayList<NewTabs>();
        while (c.moveToNext()){
            NewTabs entity=new NewTabs();
            entity.setNewTabId(c.getString(c.getColumnIndex(DbModel.NewId)));
            entity.setNewTabName(c.getString(c.getColumnIndex(DbModel.NewName)));
            entity.setNewTabType(c.getString(c.getColumnIndex(DbModel.NewType)));
            entity.setNewsChannelSelect(c.getString(c.getColumnIndex(DbModel.NewSelect)).equals("1")?true:false);
            entity.setNewsChannelIndex(Integer.parseInt(c.getString(c.getColumnIndex(DbModel.NewIndex))));
            entity.setNewsChannelFixed(c.getString(c.getColumnIndex(DbModel.NewFixed)).equals("1")?true:false);
            tabs.add(entity);
        }

        return tabs;

    }


    public List<NewTabs> queryNewTabs(boolean isMyTab){

        String sql="select * from "+DbModel.NewTable+"  where "+DbModel.NewSelect+" = ?";  // 1是true  0 false
        Cursor c=mDb.rawQuery(sql,new String[]{isMyTab==true?"1":"0"});
        if (c == null) {
            throw new RuntimeException("Can't get proper result for ");
        }
        List<NewTabs> tabs=new ArrayList<NewTabs>();
        while (c.moveToNext()){
            NewTabs entity=new NewTabs();
            entity.setNewTabId(c.getString(c.getColumnIndex(DbModel.NewId)));
            entity.setNewTabName(c.getString(c.getColumnIndex(DbModel.NewName)));
            entity.setNewTabType(c.getString(c.getColumnIndex(DbModel.NewType)));
            entity.setNewsChannelSelect(c.getString(c.getColumnIndex(DbModel.NewSelect)).equals("1")?true:false);
            entity.setNewsChannelIndex(Integer.parseInt(c.getString(c.getColumnIndex(DbModel.NewIndex))));
            entity.setNewsChannelFixed(c.getString(c.getColumnIndex(DbModel.NewFixed)).equals("1")?true:false);
            tabs.add(entity);
//            MLog.v("DaLong",""+entity.getNewTabName()+"   "+entity.isNewsChannelSelect()+"     "+c.getString(c.getColumnIndex(DbModel.NewSelect)));
        }

        return tabs;
    }




    public boolean  deleteNewsTab(String TabId){
        int flag=0;
        flag= mDb.delete(DbModel.NewTable,DbModel.NewId+" = ?",new String[]{TabId});
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    public int updateNewTab(NewTabs newTabs,boolean flag){

        ContentValues contentValues=new ContentValues();
        contentValues.put(DbModel.NewName,newTabs.getNewTabName());
//        contentValues.put(DbModel.NewType,newTabs.getNewTabType());
        MLog.v("DaLong",newTabs.isNewsChannelSelect()+"");
        contentValues.put(DbModel.NewSelect,flag==true?"1":"0");
//        contentValues.put(DbModel.NewIndex,newTabs.getNewsChannelIndex());
//        contentValues.put(DbModel.NewFixed,newTabs.getNewsChannelFixed());
        return mDb.update(DbModel.NewTable,contentValues,DbModel.NewId+" = ?" ,new String[]{newTabs.getNewTabId()});
    }




    /**
     * 我的tab的长按事件监听
     * 将两个tab位置调换
     * */
    public  boolean updateNewTab(NewTabs newTabs1,NewTabs newTabs2){

        int count=0;

        mDb.beginTransaction();


        ContentValues contentValues=new ContentValues();

        contentValues.put(DbModel.NewIndex,newTabs2.getNewsChannelIndex());
         count+=mDb.update(DbModel.NewTable,contentValues,DbModel.NewId+" = ?" ,new String[]{newTabs1.getNewTabId()});
        ContentValues contentValues2=new ContentValues();
        contentValues2.put(DbModel.NewIndex,newTabs1.getNewsChannelIndex());
        count+=mDb.update(DbModel.NewTable,contentValues2,DbModel.NewId+" = ?" ,new String[]{newTabs2.getNewTabId()});
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        if(count>=2){
            return true;
        }
        return false;
    }


}
