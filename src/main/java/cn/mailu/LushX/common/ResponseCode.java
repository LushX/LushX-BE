package cn.mailu.LushX.common;

/**
 * Created by null on 2017/11/3.
 */

public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),   //成功
    ERROR(1,"ERROR"),       //失败
    NEED_LOGIN(10,"NEED_LOGIN"),   //未登录
    ACCESS_DENIED(20,"ACCESS DENIED"),   //权限不足
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");   //非法参数

    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {

        return code;
    }

    public String getDesc() {
        return desc;
    }
}
