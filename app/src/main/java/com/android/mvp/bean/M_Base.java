package com.android.mvp.bean;

import java.io.Serializable;

/**
 * Created by 徐文龙 on 2015/11/26.
 */
public class M_Base<T> implements Serializable {


    /**
     * Data : {"cardname":"1","memo":"区域改为703409[2015/2/2 14:06:48]","idcard":"440301111111111111","addr":"深圳市罗湖区发展中心","cardnumber":"1","postcode":"123456","weixin":"","token":"af3eb809-76ea-45c6-b033-860f9b7d2ae2","CloudID":"00000000","email":"12345@qq.com","QQ":"","name":"食安互智社群","gender":"男","branch":"河北省","bank":"中国建设银行","mobile":"13631622222","weibo":""}
     * Status : 1
     * RealTime : 2015-11-26 10:46:21
     * Mssage : 成功
     */
    private T Data;
    private int Status;
    private String RealTime;
    private String Mssage;


    public void setData(T Data) {
        this.Data = Data;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setRealTime(String RealTime) {
        this.RealTime = RealTime;
    }

    public void setMssage(String Mssage) {
        this.Mssage = Mssage;
    }

    public T getData() {
        return Data;
    }

    public int getStatus() {
        return Status;
    }

    public String getRealTime() {
        return RealTime;
    }

    public String getMssage() {
        return Mssage;
    }


}
