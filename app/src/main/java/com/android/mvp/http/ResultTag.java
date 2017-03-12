package com.android.mvp.http;

/**
 * Created by Administrator on 2016/3/30.
 */
public class ResultTag {

    public static final int SUCCESS = 1;
    public static final int ERROR = 0;
    public static final int MISSING_PARAMETERS = 2;
    public static final int SOUCE_ERROR = 3;
    public static final int USERINFO_ERROR  = 4;
    public static final int LOGINSTTUS_ERROR = 5;
    public static final int PARAMETER_FORMAT_ERROR = 6;
    public static final int NO_DATA = 7;
    public static final int CONDITION_ERROR = 8;
    public static final int REPETITIVE_OPERATION = 9;
    public static final int SIGNATURE = 10;
    public static final int TIMESTAMP_ERROR = 11;
    public static final int TIMEOUT = 12;
    public static final int UPLOAD_FAIL = 13;
    public static final int NOT_ZYXK_ERROR  = 14;
    public static final int VERIFICATION_CODE_NONENTITY = 16;
    public static final int VERIFICATION_CODE_ERROR = 17;
    public static final int NO_LOGIN = 18;
    public static final int USER_REPETION = 27;
    public static final int SHOPCAR_ADD_RESULT = 31;
    public static final int BOARD_DELETE= 32;

    public static final int VERSION=30;





    /**0：服务器繁忙,操作失败
     * 1：成功
     *2：缺少必要参数
     *3：请求来源不正确
     *4：云码或密码错误
     *5：登录状态不正确，您的登录状态可能已丢失，请重新登录
     *6：参数格式不正确
     * 7：没有数据
     *8：条件不符合
     * 9：重复操作
     *10：签名验证不正确
     *11：签名时间戳不正确
     * 12：请求超时
     * 13：上传失败
     *14：对不起,您不是智云小库手机用户
     *16：验证码不存在
     *17：验证码错误
     * 18：没有登录
     * 19：已经申请过端码狂,还没有付款
     * 20：对不起，您既不是中国创客伙伴也不是中国食养家庭VIP 21：您的端码狂不符合条件，你需要在真美食安卫士网站租赁行头包之后，才能申请智云小库抢租资格 22：推送失败 23：发送短信失败 24：对不起，您已经获得了一个狂码，不能再次获取 25：对不起，狂码号段已经发放完毕 26：该用户不存在
     * 27：用户重复
     * 30:提示版本更新
     * 31：如果单个商品添加的数量大于99会返回Status=31,Mssage="该商品购买数量最多为99"
     * 32：帖子已删除
     **/

}
