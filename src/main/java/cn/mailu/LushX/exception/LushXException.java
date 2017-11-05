package cn.mailu.LushX.exception;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

/**
 * @Author:Drohe
 * @Description:自定义异常类（继承运行异常类）
 * @Date:Created in 22:18 2017/11/5
 * @Modified By:
 */
public class LushXException extends RuntimeException {

    //异常编码
    private String errorCode;

    //消息是否为属性文件中的Key
    private boolean propertiesKey = true;

    /**
     * 构造一个基本异常.
     *
     * @param message
     *          信息描述
     * @Date: Created in 22:34 2017/11/5
     */
    public LushXException(String message){
        super(message);
    }

    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     * @param cause
     *            根异常类（可以存入任何异常）
     * @Date: Created in 22:34 2017/11/5
     */
    public LushXException(String message, Throwable cause)
    {
        super(message, cause);
    }
    /**
     * 构造一个基本异常.
     *
     * @param errorCode
     *            错误代码
     * @param message
     *            错误信息
     * @Date: Created in 22:34 2017/11/5
     */
    public LushXException(String errorCode, String message){
        this(errorCode, message, true);
    }
     
    /**
     * 构造一个基本异常.
     *
     * @param errorCode
     *          错误代码
     * @param message
     *          错误信息
     * @param propertiesKey
     *          是否是属性中的键
     *@Date: Created in 22:56 2017/11/5
     */
    public LushXException(String errorCode, String message, boolean propertiesKey)
    {
        super(message);
        setErrorCode(errorCode);
        setPropertiesKey(propertiesKey);
    }
    /**
     * 构造一个基本异常.
     *
     * @param errorCode
     *          错误代码
     * @param message
     *          错误信息
     * @param cause
     *          根异常类（可以存入任何异常）
     *@Date: Created in 22:56 2017/11/5
     */
    public LushXException(String errorCode, String message, Throwable cause)
    {
        this(errorCode, message, cause, true);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode
     *            错误编码
     * @param message
     *            信息描述
     *@Date: Created in 22:56 2017/11/5
     */
    public LushXException(String errorCode, String message, Throwable cause, boolean propertiesKey)
    {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }








}
