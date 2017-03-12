package com.android.mvp.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.mvp.http.ConstantValues;

import java.io.File;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtil {

    public static Map<String, String> getTokenMap(String token) {
        String[] str = GeneralUtil.getSha1(token, ConstantValues.packageName);
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("timestamp", str[1]);
        map.put("signature", str[0]);
        MLog.i("mus", map.toString());
        return map;
    }

    /**
     * 检查内存卡是否存在
     */
    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    //SHA1 加密实例
    public static String[] getSha1(String token, String pName) {

        String timestamp = getNewTime();

        StringBuffer stringBuffer = new StringBuffer();
        String packageName = MD5Util.ToMD5(pName);

        stringBuffer.append(packageName);
        stringBuffer.append(token);
        stringBuffer.append(timestamp);

        String val = stringBuffer.toString();

        byte[] digesta = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            // 添加要进行计算摘要的信息
            alga.update(val.getBytes());
            // 得到该摘要
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        String[] rss = new String[2];
        rss[0] = byte2hex(digesta);
        rss[1] = timestamp;

        return rss;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }


    /**
     * 获取屏幕宽
     */
    public static int getWindowWidth(Context ctx) {
        Display screenSize = ((WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = screenSize.getWidth();
        return width;
    }

    /**
     * 获取屏幕高
     */
    public static int getWindowHeight(Context ctx) {
        Display screenSize = ((WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int height = screenSize.getHeight();
        return height;
    }


    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null && !"".equals(str)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 是否发现新版本
     */
    public static boolean isFindNewVersion(int newVersioncode, Context context) {

        boolean flag = false;

        if (newVersioncode > getVersionCode(context)) {
            flag = true;
        }

        return flag;

    }


    /**
     * 价格转换
     */
    public static String getProductPrice(Double price) {

        DecimalFormat decimalFormat = new DecimalFormat("0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
        String newPrice = decimalFormat.format(price);// format 返回的是字符串
        return newPrice;
    }

    /**
     * 获取版本名字
     *
     * @param context
     * @return
     */
    public static String getVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    @SuppressLint("NewApi")
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    /**
     * 获取系统已安装APP
     *
     * @param context
     * @param
     * @return
     */
    public static boolean getOneInstalledApp(Context context, String pkgName) {
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packs = pm.getInstalledPackages(0);
        int length = packs.size();
        boolean info = false;
        PackageInfo p;
        for (int i = 0; i < length; i++) {
            p = packs.get(i);
            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) { // 系统程序
                // || p.applicationInfo.packageName.equals(myPackageName)) {
                // //是否包括程序自身
                continue;
            }
            if (p.packageName.equals(pkgName)) {
                info = true;
                break;
            }
        }
        return info;
    }

    /**
     * 获取sdCard路径
     */
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
    public static String getDataDirectory() {
        String external = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            external = Environment.getExternalStorageDirectory()
                    + "/dpImg";
        }

        return external;
    }

    /**
     * 安装APP
     */
    public static void installApp(Context context, String urlStr) {
        if (urlStr != null) {
            File file = new File(urlStr);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    public static String getOneApkPackageName(String apk_path, Context ctx) {
        String packageName = null;
        PackageManager pm = null;
        PackageInfo packageInfo = null;
        if (ctx != null)
            pm = ctx.getPackageManager();
        if (pm != null)
            packageInfo = pm.getPackageArchiveInfo(apk_path,
                    PackageManager.GET_ACTIVITIES);

        if (packageInfo != null) {
            packageName = packageInfo.packageName;
        }
        return packageName;
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前时间
     */
    public static String getNewTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 获取当前时间
     */
    public static String getNewTimes() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-ddHH:mm:ss");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 获取当前日期
     */
    public static String getNewDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 判定是否需要隐藏
     */
    public static boolean isHideInput(View v, MotionEvent ev) {

        if (v != null && (v instanceof EditText)) {

            int[] l = {0, 0};

            v.getLocationInWindow(l);

            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left

                    + v.getWidth();

            if (ev.getX() > left && ev.getX() < right && ev.getY() > top

                    && ev.getY() < bottom) {

                return false;

            } else {

                return true;

            }

        }

        return false;

    }

    /**
     * 隐藏软键盘
     */
    public static void HideSoftInput(IBinder token, Context context) {

        if (token != null) {

            InputMethodManager manager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            manager.hideSoftInputFromWindow(token,

                    InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }

    /**
     * 关闭键盘
     *
     * @param context
     */
    public static void closeSoftInputKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                        .getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     */
    public static void openSoftInputKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 跳转拨号页面
     */
    public static void gotoPhone(Context context, String phoneNum) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNum));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static int px2dip(Context context, int pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 跳转到新浪微博
     */
    public static void gotoSina(Context context) {
        String intentUri = "intent:#Intent;action=android.intent.action.MAIN;category=android.intent.category.LAUNCHER;launchFlags=0x10000000;component=com.sina.weibo/.MainTabActivity;end";
        Intent notificationIntent = null;
        try {
            notificationIntent = Intent.parseUri(intentUri,
                    Intent.URI_INTENT_SCHEME);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        context.startActivity(notificationIntent);
    }

    /**
     * 获取纯正的手机号码
     *
     * @param strPhoneNumber
     * @return
     */
    public static String getPhoneNumber(String strPhoneNumber) {
        String strNumber = strPhoneNumber == null ? "" : strPhoneNumber;
        if (strPhoneNumber != null) {
            if (strPhoneNumber.startsWith("+86")) {
                strNumber = strPhoneNumber.substring(3);
            } else if (strPhoneNumber.startsWith("17951")
                    || strPhoneNumber.startsWith("17909")
                    || strPhoneNumber.startsWith("12593")) {
                strNumber = strPhoneNumber.substring(5);
            }
        }
        return strNumber;
    }

    /**
     * 输入框获取焦点时清空默认文字，失去焦点后显示
     */
    public static void closeEditHint(EditText etView, boolean hasFocus) {
        String hint;

        if (hasFocus) {

            hint = etView.getHint().toString();

            etView.setTag(hint);

            etView.setHint("");

        } else {

            hint = etView.getTag().toString();

            etView.setHint(hint);

        }

    }

    /**
     * 验证手机号码格式
     */
    public static boolean isPhoneNum(String mobiles) {

        // 第一位必定为1，第二位必定为3或5或7或8，其他位置的可以为0-9
        String telRegex = "[1][3578]\\d{9}";
        if (isEmpty(mobiles)) {
            return false;
        }
        return mobiles.matches(telRegex);
    }

    /*
     * 判断输入内容是否包含中文
     */
    public boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否包含特殊字符
     */
    public static boolean isTeShuChar(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        return m.find();

    }

    /**
     * 判断是否包含特殊字符
     */
    public static boolean hasTeShuChar(String str) {
        String regEx = "[`~_～!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？0123456789]";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        return m.find();

    }

    public static void showToast(Context conext, String content) {
        ToastShow.getInstance(conext).toastShow(content);
    }

    /**
     * 获取之间的随机数
     */
    public static int getRandomNum(int maxNum) {
        double num = Math.random() * maxNum;
        long newNum = Math.round(num);
        return (int) newNum;
    }


    /**
     * 时间转毫秒
     */
    public static final int IS_GRAB = 0;
    public static final int NO_START = 1;
    public static final int IS_END = 2;

    public static int compareTime(String realTime, String timeFirst, String timeLast) {
        int flag = 0;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date real = null;
        Date d1 = null;
        Date d2 = null;
        try {
            real = df.parse(realTime);
            d1 = df.parse(timeFirst);
            d2 = df.parse(timeLast);

            if ((real.getTime() - d1.getTime()) < 0) {

                return NO_START;
            } else if ((real.getTime() - d1.getTime()) >= 0 && (d2.getTime() - real.getTime()) >= 0) {
                return IS_GRAB;
            } else {
                return IS_END;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return flag;
    }


    /**
     * 获取时间差距
     */
    public static long getTimeGap(String timeFirst, String timeLast) {
        long timegap = 0;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = df.parse(timeFirst);
            d2 = df.parse(timeLast);

            timegap = d2.getTime() - d1.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timegap;
    }

    /**
     * 时间转天、小时、分、秒
     */

    public static String getTimeStr(long timeGap) {

//        timeGap = 1325676000;

        String time;
        int minute = 1000 * 60;
        int hours = minute * 60;
        int day = hours * 24;

        long dayStr = 0;
        long hoursStr = 0;
        long minuteStr = 0;
        long secondStr = 0;


        if (timeGap >= day) {

            dayStr = timeGap / day;
            timeGap = timeGap - (day * dayStr);

        }

        if (timeGap >= hours) {

            hoursStr = timeGap / hours;
            timeGap = timeGap - (hours * hoursStr);

        }

        if (timeGap >= minute) {

            minuteStr = timeGap / minute;
            timeGap = timeGap - (minute * minuteStr);

        }

        if (timeGap >= 1000) {

            secondStr = timeGap / 1000;

        }

        time = dayStr + "天" + hoursStr + "小时" + minuteStr + "分" + secondStr + "秒";

        return time;
    }


    /**
     * 获取当前时间
     */
    public static String getTimeStr() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyyMMddhhmmss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
