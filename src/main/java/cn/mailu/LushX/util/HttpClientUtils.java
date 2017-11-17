package cn.mailu.LushX.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * @Author:Drohe
 * @Description:利用Httpclient爬取页面
 * @Date:Created in 22:18 2017/11/12
 * @Modified By:
 */
public class HttpClientUtils {

    public static String fetch(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String pageStr = "";

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() == 200 && entity != null) {
            pageStr = EntityUtils.toString(entity, "utf-8");
        }

        response.close();
        httpclient.close();
        return pageStr;
    }



}
