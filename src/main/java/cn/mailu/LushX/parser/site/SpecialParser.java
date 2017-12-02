package cn.mailu.LushX.parser.site;

import cn.mailu.LushX.util.HttpClientUtils;
import cn.mailu.LushX.util.JsoupUtils;
import cn.mailu.LushX.util.UrlUtils;
import cn.mailu.LushX.util.XmlUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:35 2017-11-24
 * @Modified By:
 */
@Component
@EnableAsync
public class SpecialParser {
    private static Logger logger= LoggerFactory.getLogger(SpecialParser.class);

    private final static String PREFIX_URL="https://www.50s.cc";

    private final static String API_PREFIX_URL="https://chenluo2.duapp.com/chenluomd/url.php?xml=";
    private final static String API_SUFFIX_URL="&type=auto&hd=yh&wap=0&siteuser=&lg=";

    private final static String MOVIE_URL="https://www.50s.cc/whole/1~~~~~~~0~hits~~";

    private static int pageNum = 1;

//    @Autowired
//    private RedisService redisService;
//
//    @Async
//    public void start(){
//        getMovieList("test");
//    }
//
//    public void getMovieList(String key){
//        for (int i = 1; i <= pageNum; i++){
//            getMovieDetail(MOVIE_URL + i + ".html");
//        }
//    }
//
//    public void getMovieDetail(String url){
//        Document document = JsoupUtils.getDocWithPC(url);
//        Elements elements = document.select(".movie-item-in");
//        elements.forEach(element ->
//                getApi(PREFIX_URL + element.select("a").first().attr("href").toString()));
//
//    }

    // 传入详情页面
    public String getApi(String url){
        Document document = JsoupUtils.getDocWithPC(url); //爬取详情页面
        String playUrl = PREFIX_URL + document.select(".tv-js-btn").first().attr("href").toString();
        System.out.println(playUrl);
        Document detail = JsoupUtils.getDocWithPC(playUrl);

        String src = detail.select("#player iframe").attr("src").toString();
        String[] split = null;

        if (src.contains("letv")){
            return getLeTvApi(src,playUrl);
        }else if (src.contains("iqiyi")){
            String iqiyiApi = getIqiyiOrMgTvApi(src, playUrl);
            return parseIqiyi(iqiyiApi,playUrl);
        }else if (src.contains("mgtv")){
            String mgtvApi = getIqiyiOrMgTvApi(src, playUrl);
            return parseMgTv(mgtvApi,playUrl);
        }else{
            return ("资源目前不支持");
        }

    }

    public String getOldMd5(String src,String refererUrl) {

        String indexUrl = "https:" + src;
        Map map = new HashMap<String, String>();
        map.put("Referer", refererUrl);
        Connection connection = JsoupUtils.getConnection(indexUrl, null, null, map);
        try {
            Document indexHtml = connection.get();
            Elements scripts = indexHtml.getElementsByTag("script");
            Pattern pattern = Pattern.compile("(?<=eval\\(\")(.*?)(?=\")");//正则表达式 匹配md5
            Matcher matcher = pattern.matcher(scripts.last().toString());
            if (matcher.find()) {

                return matcher.group();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIqiyiOrMgTvApi(String indexUrl,String playUrl){
        String[] split = indexUrl.split("id=");
        String oldMd5 = getOldMd5(indexUrl, playUrl);
        if (oldMd5 != null){
            String newMd5 = getNewMd5(oldMd5);
            String m3u8 = API_PREFIX_URL + split[1] +"&md5=" + newMd5 + API_SUFFIX_URL;
            m3u8 = getM3u8(m3u8);
            return m3u8;
        }else {
            logger.info("解析失败");
            return "解析失败";
        }


    }


    public String getLeTvApi(String url,String playUrl){
        String[] split = url.split("id=");
        String targetUrl = "https://chenluo2.duapp.com/chenluomd/url.php";
        Map headers = new HashMap<String,String>();
        System.out.println("https:"+url);
        headers.put("Referer","https:"+url);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

        Map params = new HashMap<String,String>();
        if (split.length > 1){
            params.put("id",split[1]);
            params.put("type","auto");
            params.put("siteuser","");
            String oldMd5 = getOldMd5(url, playUrl);
            if (oldMd5 != null){
                params.put("md5",getNewMd5(oldMd5));
            }else {
                logger.info("解析失败");
                return "解析失败";
            }

            params.put("hd","");
            params.put("lg","");

            String json = null;
            String m3u8 = null;
            try {
                json = HttpClientUtils.doPost(targetUrl, params, "GBK", headers);
                JsonParser parser=new JsonParser();  //创建JSON解析器
                JsonObject object=(JsonObject) parser.parse(json);  //解析返回的json数据 创建JsonObject对象
                m3u8 = object.get("url").getAsString();
                m3u8 = UrlUtils.getURLDecoderString(m3u8,"GBK");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return m3u8;
        }else{
            return "解析失败";
        }


    }

    public String parseIqiyi(String api,String playUrl){
        Map headers = new HashMap<String,String>();
        headers.put("Referer",playUrl);
        String str = JsoupUtils.get(api, null, null, headers);
        return str;
    }

    public String parseMgTv(String api,String playUrl){
        Map headers = new HashMap<String,String>();
        headers.put("Referer",playUrl);
        String str = JsoupUtils.get(api, null, null, headers);
        String realStr = XmlUtils.parseFile(str);
        return realStr;
    }


    public String getNewMd5(String md5){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String jsFileName = "data/js/tools.js";   // 读取js文件

        FileReader reader = null;   // 执行指定脚本

        String deMd5 = null;
        String newMd5 = null;
        try {
            reader = new FileReader(jsFileName);
            engine.eval(reader);
            if(engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;    // 调用sign方法
                deMd5 = (String) invoke.invokeFunction("JavaDe", md5);
                Pattern pattern = Pattern.compile("(?<=val\\(\')(.*?)(?=\')");
                Matcher matcher = pattern.matcher(deMd5);
                if (matcher.find()) {
                    deMd5 = matcher.group();
                    newMd5 = (String) invoke.invokeFunction("sign", deMd5);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newMd5;

    }

    //爱奇艺 和 芒果TV
    public String getM3u8(String str){
        if(str.contains("mgtv"))
            return str + "&sohuuid=";
        else if(str.contains("iqiyi"))
            return str + "&cip=yh&iqiyicip=yh";
        else
            return str;
    }

    public void saveToRedis(){

    }

}
