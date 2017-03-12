package com.android.mvp.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by 徐文龙 on 2015/10/14.
 */
public class FileUtil {

    public static String ROOT= Environment.getExternalStorageDirectory().getAbsolutePath();





    /**
     * 判断SD卡是否存在
     * */
    public static boolean isFileSD(){
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 获取sdCard路径
     * */
    public static String getExternalStorageDirectory() {

        String external = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            external = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        }

        return external;
    }




    /**
     * 获取data目录
     */
    public static String getDataPath() {
        String external = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            external = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/SAYD";
        }
        return external;
    }



    /**
     * 如果文件夹没创建，创建文件夹
     * */
    public static  void  createFileDirectory(File file){
        if(!file.exists()){
            file.mkdirs();
        }
    }
    public static void deleteFileDirectory(File file){

        if(file.isFile()){
            file.delete();
        }

        if(file.isDirectory()){
            File[] files=file.listFiles();
            if(files.length==0||files==null){
                file.delete();
                return;
            }
            for(File f:files){
                deleteFileDirectory(f);
            }
            file.delete();
        }
    }

    /**
     * 如果文件不存在，则创建文件
     * */
    public static void createFileJpg(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将文件转化成围巾织
     * */
    public StringBuilder saveToImgByBytes(String path) throws IOException {
        File file = new File(path);
        StringBuilder str = new StringBuilder();//不建议用String
        try {
        } catch (Exception e) {
            // TODO: handle exception
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            for(byte bs:b)
            {
                str.append(Integer.toBinaryString(bs));//转换为二进制
            }
        }
        return str;
    }



}
