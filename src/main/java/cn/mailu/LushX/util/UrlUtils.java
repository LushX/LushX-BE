package cn.mailu.LushX.util;

import lombok.extern.log4j.Log4j2;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author:Drohe
 * @Description:URL工具类
 * @Date:Created in 23:01 2017/11/5
 * @Modified By:
 */
@Log4j2
public class UrlUtils {

    public static String getDomain(String url) {
        String domain = "";
        try {
            URL target = new URL(url);
            domain = target.getHost();
        } catch (MalformedURLException e) {
            log.error("Url(" + url + ") Cannot convert to BASIC-URL");
            e.printStackTrace();
        }
        return domain;
    }

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
            log.error("Url(" + url + ") Cannot convert to BASIC-URL");
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

}
