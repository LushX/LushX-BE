package cn.mailu.LushX.util;

import cn.mailu.LushX.exception.LushXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author:Drohe
 * @Description:URL工具类
 * @Date:Created in 23:01 2017/11/5
 * @Modified By:
 */
public class UrlUtils {

    private static Logger logger= LoggerFactory.getLogger(UrlUtils.class);
    
    public static String getDomain(String url) {
        String domain = "";
        try {
            URL target = new URL(url);
            domain = target.getHost();
        } catch (MalformedURLException e) {
            logger.error("Url(" + url + ") Cannot convert to BASIC-URL");
            e.printStackTrace();
        }
        return domain;
    }
    /**
     * 取得顶级域名
     *
     * @param url
     *
     *
     *@Date: Created in 22:15 2017/11/8
     *
     */

    public static String getTopDomain(String url) {
        String domain = "";
        try {
            URL target = new URL(url);
            domain = target.getHost();
            String[] part = domain.split("\\.");
            if (part.length > 2) {
                domain =  part[part.length - 3] + "." + part[part.length - 2] + "." + part[part.length - 1];
                domain = domain.replace("www.", "");
                domain = domain.replace("m.", "");
            }
        } catch (MalformedURLException e) {
            logger.error("Url(" + url + ") Cannot convert to BASIC-URL");
            e.printStackTrace();
        }
        return domain;
    }

    public static String adjustUrl(String url) {
        if (url.contains("http://")) {
            return url;
        }
        return "http://" + url;
    }
    /**
     * 获取 HTTP 请求返回的结果
     */
    public static String getResponse(String api) {
        try {
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("user-agent", JsoupUtils.getUaPad());
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 得到响应信息
                InputStream is = connection.getInputStream();
                byte[] bs = new byte[1024];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = is.read(bs)) != -1) {
                    String str = new String(bs, 0, len);
                    sb.append(str);
                }
                return sb.toString();
            }
            throw new LushXException("HTTP 请求错误");
        } catch (IOException exception) {
            throw new LushXException("youku api request error: " + api);
        }
    }

}
