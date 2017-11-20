package cn.mailu.LushX.crawler;

import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.JsoupUtils;
import cn.mailu.LushX.util.TimeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author:Drohe
 * @Description:优酷视频爬取
 * @Date:Created in 13:15 2017/11/6
 * @Last_Modified By:Drohe in 23:57 2017/11/18
 */

@Component
@EnableAsync
public class YoukuCrawler {

    //全局设定需要爬的页数
    private int pageNum = 3;

    private static Logger logger = LoggerFactory.getLogger(YoukuCrawler.class);

    private final static String YK_TV_URL_HOT = "http://list.youku.com/category/show/c_97_s_1_d_1.html";
    private final static String YK_MOVIE_URL_HOT = "http://list.youku.com/category/show/c_96_s_1_d_1.html";
    private final static String YK_ZY_URL_HOT = "http://list.youku.com/category/show/c_85_s_1_d_1.html";
    private final static String YK_DM_URL_HOT = "http://list.youku.com/category/show/c_100_s_1_d_1.html";
    private final static String YK_TV_URL_NEW = "http://list.youku.com/category/show/c_97_s_6_d_1.html";
    private final static String YK_MOVIE_URL_NEW = "http://list.youku.com/category/show/c_96_s_6_d_1.html";
    private final static String YK_ZY_URL_NEW = "http://list.youku.com/category/show/c_85_s_6_d_1.html";
    private final static String YK_DM_URL_NEW = "http://list.youku.com/category/show/c_100_s_6_d_1.html";


    private static final String TAG = "YOUKU";

    @Autowired
    private RedisService redisService;


    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    @Async
    public void start() {
        logger.info("================youkucrawler start=============");

        //pageTurning(YK_TV_URL_HOT, VideoTypeEnum.YK_TV_HOT.getCode());
        pageTurning(YK_MOVIE_URL_HOT, VideoTypeEnum.YK_MOVIE_HOT.getCode());
        //pageTurning(YK_ZY_URL_HOT, VideoTypeEnum.YK_ZY_HOT.getCode());
        pageTurning(YK_DM_URL_HOT, VideoTypeEnum.YK_DM_HOT.getCode());//部分属性为空
        pageTurning(YK_TV_URL_NEW, VideoTypeEnum.YK_TV_NEW.getCode());
        pageTurning(YK_MOVIE_URL_NEW, VideoTypeEnum.YK_MOVIE_NEW.getCode());
        // pageTurning(YK_ZY_URL_NEW, VideoTypeEnum.YK_ZY_NEW.getCode());
        pageTurning(YK_DM_URL_NEW, VideoTypeEnum.YK_DM_NEW.getCode());
        logger.info("================youkucrawler stop=============");
    }//18296697365

    private List<Video> getYKVideosFromPcDocument(Document document, int type) {
        List<Video> videos = new ArrayList<>();
        Elements videoElements = document.select("li.yk-col4");
        for (Element element : videoElements) {
            Video video = new Video();
            String title = element.select("div.p-thumb a").get(0).attr("title");
            String image = element.select("img.quic").attr("src").replace("http:", "");
            String url = "http:" + element.select("div.p-thumb a").get(0).attr("href");
            Document infoDocument = JsoupUtils.getDocWithPC(url);
            String info = "http:" + infoDocument.select("#module_basic_title > div.base_info > a.desc-link").attr("href");
            video.setVideoId(UUID.randomUUID().toString());
            video.setTitle(title);
            video.setImage(image);
            video.setValue(url);

            Document infoPage;
            //有些视频播放页不存在，首页爬取的页面直接为详情页，不做处理会造成空指针异常
            if (url.indexOf("list.youku.com") == -1) {
                infoPage = JsoupUtils.getDocWithPC(info);
            } else {
                infoPage = infoDocument;
            }

            Elements videoInfoElement = infoPage.select("body > div.s-body > div > div.mod.mod-new > div.mod.fix");
            String actor = videoInfoElement.select("li.p-performer").attr("title");
            String alias = videoInfoElement.select("li.p-alias").attr("title");
            String area = null;
            String director = null;
            //动画片详情页和其他稍有不同，这里用做出判断
            if (type == VideoTypeEnum.YK_DM_HOT.getCode() || type == VideoTypeEnum.YK_DM_NEW.getCode()) {

                director = videoInfoElement.select("li.p-score+li+li").text().replace("导演：", "");
                area = videoInfoElement.select("li.p-score+li+li+li").text().replace("地区：", "");
            } else {
                director = videoInfoElement.select("li.p-performer+li").text().replace("导演：", "");
                area = videoInfoElement.select("li.p-performer+li+li").text().replace("地区：", "");
            }
            String score = videoInfoElement.select("li.p-score span.star-num").text();
            String timeFromSpanPubElement = videoInfoElement.select("span.pub").size() == 0 ? "" : videoInfoElement.select("span.pub").get(0).text()
                    .replace("上映：", "")
                    .replace("优酷开播：", "")
                    .replace("优酷上映：", "")
                    .replace("优酷", "");

            String timeFromSpanSubTitleElement = videoInfoElement.select("span.sub-title").size() == 0 ? "" : videoInfoElement.select("span.sub-title").get(0).text();

            String time = timeFromSpanPubElement.length() > timeFromSpanSubTitleElement.length() ? timeFromSpanPubElement : timeFromSpanSubTitleElement;

            video.setActor(actor);
            video.setAlias(alias);
            video.setArea(area);
            video.setDirector(director);
            video.setScore(score);

            video.setTime(new java.sql.Date(TimeUtils.stringToDate(time.replace(" ", "")).getTime()));
            logger.info("YK:" + title);
            videos.add(video);
        }
        return videos;
    }

    private void pageTurning(String url, int videoType) {

        List <Document> documents=new ArrayList<>();

        for (int i = 1; i <= pageNum; i++) {

            url = url.replace(".html", "") + "_p_" + i + ".html";

            Document document = JsoupUtils.getDocWithPC(url);

            documents.add(document);

            logger.info("第" + i + "页 列表爬取完毕！ URL:  " + url);
        }
        saveVideosToRedis(documents, videoType);
    }

    private void saveVideosToRedis(List<Document> documents, int videoType) {
        List <Video> videoList=new ArrayList<>();
        for(int i=1;i<=pageNum;i++){

            videoList.addAll(getYKVideosFromPcDocument(documents.get(i-1), videoType));
        }
        String videoKey = RedisKey.VIDEOS_KEY + "_" + videoType;
        redisService.saveByKey(videoKey,videoList);
        logger.info("已存入redis！");
    }
}
