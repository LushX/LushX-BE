package cn.mailu.LushX.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static cn.mailu.LushX.util.DateUtils.format;

/**
 * @Author:Drohe
 * @Description:时间处理类
 * @Date:Created in 10:17 2017/11/6
 * @Modified By:
 */

public class TimeUtils {

    private final static long YEAR = 1000 * 60 * 60 * 24 * 365L;
    private final static long MONTH = 1000 * 60 * 60 * 24 * 30L;
    private final static long DAY = 1000 * 60 * 60 * 24L;
    private final static long HOUR = 1000 * 60 * 60L;
    private final static long MINUTE = 1000 * 60L;
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";



    /**
 *
 *获取当前时间
 *
 *
 *@Date: Created in 15:28 2017/11/12
 *
 */
    public static String getCurrentTime(){

return DateUtils.format(new Date(),DATE_TIME_PATTERN);


    }

    public static Date stringToDate(String strDate) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {

            return  sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String natureTime(Date date){
        
        /**
         *@Author:Drohe
         *
         *@params: [date]
         *          准确时间
         *@return: java.lang.String
         *          返回通俗时间
         * @Date:Created in 10:44 2017/11/6
         *
         */
        Date now = new Date();

        long between = now.getTime() - date.getTime();
        if (between > YEAR){
            return ((between - YEAR) / YEAR + 1) + "年前，";
        }
        if (between > MONTH){
            return ((between - MONTH) / MONTH + 1) + "月前，";
        }
        if (between > DAY){
            return ((between - DAY) / DAY + 1) + "天前，";
        }
        if (between > HOUR){
            return ((between - HOUR) / HOUR + 1) + "小时前，";
        }
        if (between > MINUTE){
            return ((between - MINUTE) / MINUTE + 1) + "分钟前，";
        }
        return "刚刚，";
    }

}
