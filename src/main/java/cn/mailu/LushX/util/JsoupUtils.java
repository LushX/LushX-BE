package cn.mailu.LushX.util;

import cn.mailu.LushX.exception.LushXException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Jsoup 工具类
 * Created by Silence on 2017/1/25.
 */
public class JsoupUtils {

    private static Logger logger = LoggerFactory.getLogger(JsoupUtils.class);
    private static final String UA_PHONE = "Mozilla/5.0 (Linux; Android 4.3; Nexus 10 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Safari/537.36";
    private static final String UA_PC = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
    private static final String UA_PAD = "Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    private static final int TIME_OUT = 10 * 1000;

    public static Document getDocWithPC(String url) {

        int times = 1;

        Document document = null;

       /* if(url.contains("http:h")){

         url= url.replace("http:h","h");
        }*/

            try {
                document=Jsoup.connect(url).userAgent(UA_PC).timeout(5*1000).ignoreContentType(true).get();
            } catch (IOException e) {
                logger.error("网址请求失败：" + url);
                throw new LushXException("网址请求失败");
            }
        return document;
    }

    public static Document getDocWithPhone(String url) {
        try {
            return Jsoup.connect(url).userAgent(UA_PHONE).timeout(TIME_OUT).ignoreContentType(true).validateTLSCertificates(false).get();
        } catch (IOException e) {
            logger.error("网址请求失败：" + url);
            throw new LushXException("网址请求失败：" + url);
        }
    }

    public static Document getDocWithPad(String url) {
        try {
            return Jsoup.connect(url).userAgent(UA_PAD).timeout(TIME_OUT).ignoreContentType(true).validateTLSCertificates(false).get();
        } catch (IOException e) {
            logger.error("网址请求失败：" + url);
            throw new LushXException("网址请求失败：" + url);
        }
    }

    public static Document getDocWithPhone(String url, String cookie) {
        try {
            return Jsoup.connect(url).userAgent(UA_PHONE).timeout(TIME_OUT).header("Cookie", cookie).ignoreContentType(true).get();
        } catch (IOException e) {
            logger.error("网址请求失败：" + url);
            throw new LushXException("网址请求失败：" + url);
        }
    }

    public static Document getDocWithPC(String url, String cookie) {
        try {
            return Jsoup.connect(url).userAgent(UA_PHONE).timeout(TIME_OUT).header("Cookie", cookie).ignoreContentType(true).get();
        } catch (IOException e) {
            logger.error("网址请求失败：" + url);
            throw new LushXException("网址请求失败：" + url);
        }
    }

    public static String getUaPad(){
        return UA_PAD;
    }




    /**
     * 获取连接
     *
     * @param url     请求url
     * @param params  参数
     * @param charset 参数编码方式
     * @param headers 头部信息
     * @return
     */
    public static Connection getConnection(String url, Map<String, String> params, String charset,
                                           Map<String, String> headers) {
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                paramList.add(new BasicNameValuePair(key, value));
            }
            try {
                String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramList, charset));
                StringBuffer sb = new StringBuffer();
                sb.append(url);
                if (url.indexOf("?") > 0) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }
                sb.append(paramStr);
                url = sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Connection conn = Jsoup.connect(url);
        conn.timeout(10000); // 10秒超时
        conn.ignoreContentType(true);

        if (headers != null) {
            Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                conn.header(key, value);
            }
        }

        return conn;
    }

    public static String get(String url, Map<String, String> params, String charset,
                             Map<String, String> headers) {
        String result = "";
        try {
            Connection conn = getConnection(url, params, charset, headers);
            Response response = conn.execute();
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
