package cn.mailu.LushX.constant;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 2:11 2017-11-06
 * @Modified By:
 */
public enum  LoginTypeEnum {
    GitHub("1"),
    QQ("2"),
    WeChat("3");


    private String code;

    LoginTypeEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
