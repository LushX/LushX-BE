package cn.mailu.LushX.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


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


    /**
     * 模拟请求
     *
     * @param url       资源地址
     * @param params   参数列表
     * @param encoding  编码
     * @param headers   请求头参数
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String doPost(String url, Map<String,String> params,String encoding,Map<String,String> headers) throws ParseException, IOException{
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(params!=null){
            for (Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

//        System.out.println("请求地址："+url);
//        System.out.println("请求参数："+nvps.toString());

        //设置header信息
        if (headers != null) {
            Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                httpPost.setHeader(key, value);
            }
        }


        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


}
