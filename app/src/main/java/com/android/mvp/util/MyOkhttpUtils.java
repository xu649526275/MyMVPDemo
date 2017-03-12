package com.android.mvp.util;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/12  14:55
 */
public class MyOkhttpUtils {


    private static final String TAG = "MyOkhttpUtils";


    /**
     * git提交
     * */
    public static void getHttp(String url, Map<String,String> map){
        //Get请求

        MLog.v(TAG,url);

        GetBuilder builder=OkHttpUtils.get().url(url);
        if(map!=null){
            for(Map.Entry<String,String> mapkey:map.entrySet()){
                builder.addParams(mapkey.getKey(),mapkey.getValue());
            }
        }
        builder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MLog.v(TAG,"error"+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {

                MLog.v(TAG,"成功了");
                MLog.json(TAG,response);
            }
        });
    }


    /**
     *post提交
     * */
    public static void postHttp(String url, Map<String,String> map){
        //Get请求

        PostFormBuilder builder=OkHttpUtils.post().url(url);
        if(map!=null){
            for(Map.Entry<String,String> mapkey:map.entrySet()){
                builder.addParams(mapkey.getKey(),mapkey.getValue());
            }
        }
        builder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MLog.json(TAG,"error");
            }

            @Override
            public void onResponse(String response, int id) {
                MLog.json(TAG,response);
            }
        });
    }


    /**
     * json提交
     * */
    public static void  jsonHttp(String url){
        //Get请求

        PostStringBuilder builder=OkHttpUtils.postString().url(url);
        builder.content(new Gson().toJson("a啊啊 ")).mediaType(MediaType.parse("application/json; charset=utf-8"));

        builder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                MLog.json(TAG,response);
            }
        });
    }



    public static void dowmHttp(String url,Map<String,String> map,String filePath,String fileName,FileCallBack callback){

        GetBuilder builder=OkHttpUtils.get().url(url);
        if(map!=null){
            for(Map.Entry<String,String> mapkey:map.entrySet()){
                builder.addParams(mapkey.getKey(),mapkey.getValue());
            }
        }
        builder.build().execute(new FileCallBack(filePath,fileName) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(File response, int id) {

            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
            }
        });
    }


    public static void fromHttp(String url, Map<String,String> map, List<File> files){

        PostFormBuilder builder=OkHttpUtils.post().url(url);

        for(int i=0;i<files.size();i++){
            builder.addFile("mFile",files.get(i).getName(),files.get(i));
        }
        if(map!=null){
            for(Map.Entry<String,String> mapkey:map.entrySet()){
                builder.addParams(mapkey.getKey(),mapkey.getValue());
            }
        }

        builder.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }



}
