package cn.mailu.LushX.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author: NULL
 * @Description:读取配置文件
 * @Date: Create in 2017/11/4 16:49
 */
public class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Properties props;

    //创建类时先执行读取配置文件
    static {
        String fileName = "lushx.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            logger.error("读取配置文件异常", e);
        }
    }

    public static String getProperty(String key) {
        String value=props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    /**
     * @Title: getProperty
     * @Description: 读取配置值，没有赋默认值
     * @param [key, defaultValue]    设定文件
     * @return java.lang.String    返回类型
     * @throws
     */
    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }


}
