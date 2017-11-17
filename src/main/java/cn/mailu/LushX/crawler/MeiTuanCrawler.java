package cn.mailu.LushX.crawler;

import cn.mailu.LushX.entity.HotelComment;
import cn.mailu.LushX.entity.HotelInfo;
import cn.mailu.LushX.util.JsoupUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 10:27 2017-11-08
 * @Modified By:
 */
public class MeiTuanCrawler {

    private static Logger logger = LoggerFactory.getLogger(MeiTuanCrawler.class);

    // private final static String YK_HOT_VIDEO_URL="http://list.youku.com/category/show/c_91.html";

    private final static String URL = "https://ihotel.meituan.com/hbsearch/HotelSearch?utm_medium=pc&version_name=999.9&cateId=20&attr_28=129&uuid=C34D54E68DA1685F5F78021491CB5EB865A218F517AF307F1E8A56F7E4A4D5D9%401510153902594&cityId=10&offset=0&limit=20&startDay=20171114&endDay=20171115&q=&sort=defaults";
    private final static String COMMENT_URL_BERIN = "http://api.hotel.meituan.com/group/v1/poi/comment/";
    private final static String COMMENT_URL_END = "?sortType=default&noempty=1&withpic=0&filter=all&limit=10&offset=10";

//            "https://ihotel.meituan.com/hbsearch/HotelSearch?utm_medium=pc&version_name=999.9&cateId=20&attr_28=129&uuid=C34D54E68DA1685F5F78021491CB5EB865A218F517AF307F1E8A56F7E4A4D5D9%401510154672566&cityId=59&offset=20&limit=20&startDay=20171108&endDay=20171109&q=&sort=defaults";
//            "http://api.hotel.meituan.com/group/v1/poi/comment/495195?sortType=default&noempty=1&withpic=0&filter=all&limit=10&offset=10"
    private static Map<String, String> headers = new HashMap<String, String>();
    static {

        // 设置请求头参数
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        headers.put("Accept-Language", "en-GB,en;q=0.9,en-US;q=0.8,zh-CN;q=0.7,zh;q=0.6");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Connection", "Keep-Alive");
        headers.put("Content-Type", "application/json;charset=UTF-8");
    }

    public void start() {

        getHotelInfosFromMeituan(JsoupUtils.get(URL, null, "utf-8", headers));
    }

    private List<HotelInfo> getHotelInfosFromMeituan(String content) {


        JsonParser parser=new JsonParser();  //创建JSON解析器
        JsonObject object=(JsonObject) parser.parse(content);  //解析返回的json数据 创建JsonObject对象
        JsonObject data = object.get("data").getAsJsonObject();
        JsonArray searchresults = data.get("searchresult").getAsJsonArray(); //获取主要数据对象 searchresult
        List<HotelInfo> hotelInfos = new ArrayList<>();

        for (JsonElement searchresult : searchresults) {        //循环遍历个酒店信息
            HotelInfo hotelInfo = new HotelInfo();
            JsonObject result = searchresult.getAsJsonObject();
            System.out.println(result.get("poiid").getAsString());
            hotelInfo.setHotelId(result.get("poiid").getAsString());
            hotelInfo.setPosition(result.get("addr").getAsString());
            hotelInfo.setName(result.get("name").getAsString());
            hotelInfos.add(hotelInfo);

            getHotelCommentsFromMeituan(COMMENT_URL_BERIN + result.get("poiid").getAsString() + COMMENT_URL_END);

        }
        return null;
    }

    /**
     * 通过解析返回的json数据,生成对应的评论实体
     * @param commetUrl
     * @return
     */
    private List<HotelComment> getHotelCommentsFromMeituan(String commetUrl){
        System.out.println(commetUrl);
        String content = JsoupUtils.get(commetUrl, null, "utf-8", headers);
        JsonParser parser = new JsonParser();  //创建JSON解析器
        JsonObject object = (JsonObject) parser.parse(content);  //解析返回的json数据 创建JsonObject对象
        JsonObject data = object.get("data").getAsJsonObject();
        JsonArray feedback = data.get("feedback").getAsJsonArray();
        List<HotelComment> hotelComments = new ArrayList<>();
        
        for (JsonElement comment : feedback) {
            HotelComment hotelComment = new HotelComment();
            JsonObject hotel = comment.getAsJsonObject();
            hotelComment.setCommentId(hotel.get("id").getAsString());
            hotelComment.setContent(hotel.get("comment").getAsString());
            hotelComment.setLevel(hotel.get("score").getAsInt());
            System.out.println(hotelComment);
            hotelComments.add(hotelComment);
        }
        return null;
    }


    public static void main(String[] args) {

        MeiTuanCrawler youkuCrawler = new MeiTuanCrawler();

        youkuCrawler.start();

    }

}
