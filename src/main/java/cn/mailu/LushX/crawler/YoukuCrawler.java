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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author:Drohe
 * @Description:优酷视频爬取
 * @Date:Created in 13:15 2017/11/6
 * @Modified By:
 */

@Component
public class YoukuCrawler {

    private static Logger logger = LoggerFactory.getLogger(YoukuCrawler.class);

    private final static String YK_TV_URL_HOT = "http://list.youku.com/category/show/c_97.html";
    private final static String YK_MOVIE_URL_HOT= "http://list.youku.com/category/show/c_96.html";
    private final static String YK_ZY_URL_HOT = "http://list.youku.com/category/show/c_85.html";
    private final static String YK_DM_URL_HOT = "http://list.youku.com/category/show/c_100.html";
    private final static String YK_TV_URL_NEW = "http://list.youku.com/category/show/c_97_s_6_d_1.html";
    private final static String YK_MOVIE_URL_NEW= "http://list.youku.com/category/show/c_96_s_6_d_1.html";
    private final static String YK_ZY_URL_NEW= "http://list.youku.com/category/show/c_85_s_6_d_1.html";
    private final static String YK_DM_URL_NEW= "http://list.youku.com/category/show/c_100_s_6_d_1.html";



    private static final String TAG = "YOUKU";

    @Autowired
    private RedisService redisService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void start() {
        logger.info("================youkucrawler start=============");
        Document ykTvHot = JsoupUtils.getDocWithPC(YK_TV_URL_HOT);
        Document ykMovieHot = JsoupUtils.getDocWithPC(YK_MOVIE_URL_HOT);
        Document ykZyHot = JsoupUtils.getDocWithPC(YK_ZY_URL_HOT);
        Document ykDmHot = JsoupUtils.getDocWithPC(YK_DM_URL_HOT);
        Document ykTvNew = JsoupUtils.getDocWithPC(YK_TV_URL_NEW);
        Document ykMovieNew = JsoupUtils.getDocWithPC(YK_MOVIE_URL_NEW);
        Document ykZyNew = JsoupUtils.getDocWithPC(YK_ZY_URL_NEW);
        Document ykDmNew = JsoupUtils.getDocWithPC(YK_DM_URL_NEW);
        saveVideosToRedis(ykTvHot, VideoTypeEnum.YK_TV_HOT.getCode());
        saveVideosToRedis(ykMovieHot, VideoTypeEnum.YK_MOVIE_HOT.getCode());
        saveVideosToRedis(ykZyHot, VideoTypeEnum.YK_ZY_HOT.getCode());
        saveVideosToRedis(ykDmHot, VideoTypeEnum.YK_DM_HOT.getCode());
        saveVideosToRedis(ykTvNew, VideoTypeEnum.YK_TV_NEW.getCode());
        saveVideosToRedis(ykMovieNew, VideoTypeEnum.YK_MOVIE_NEW.getCode());
        saveVideosToRedis(ykZyNew, VideoTypeEnum.YK_ZY_NEW.getCode());
        saveVideosToRedis(ykDmNew, VideoTypeEnum.YK_DM_NEW.getCode());
        logger.info("================youkucrawler stop=============");
    }

    private List<Video> getYKVideosFromPcDocument(Document document) {
        List<Video> videos = new ArrayList<>();
        Elements videoElements = document.select("li.yk-col4");
        for (Element element : videoElements) {
            Video video = new Video();
            String title = element.select("div.p-thumb a").get(0).attr("title");
            String image = element.select("img.quic").attr("src").replace("http:", "");
            String url = "http:" + element.select("div.p-thumb a").get(0).attr("href");
            Document infoDocument=JsoupUtils.getDocWithPC(url);
            String info = "http:" + infoDocument.select("#module_basic_title > div.base_info > a.desc-link").attr("href");
            video.setVideoId(UUID.randomUUID().toString());
            video.setTitle(title);
            video.setImage(image);
            video.setValue(url);
            Document documentinfo = JsoupUtils.getDocWithPC(info);
            Elements videoInfoElement = documentinfo.select("body > div.s-body > div > div.mod.mod-new > div.mod.fix");
            String actor = videoInfoElement.select("li.p-performer").attr("title");
            String alias = videoInfoElement.select("li.p-alias").attr("title");
            String area = videoInfoElement.select("li.p-performer+li+li").text().replace("地区：","");
            String director = videoInfoElement.select("li.p-performer+li").text().replace("导演：","");
            String score = videoInfoElement.select("li.p-score span.star-num").text();
            String time = videoInfoElement.select("span.pub").get(0).text().replace("上映：", "").replace("优酷开播：","").replace("优酷上映：","").replace("优酷","");
            video.setActor(actor);
            video.setAlias(alias);
            video.setArea(area);
            video.setDirector(director);
            video.setScore(score);
            video.setTime(new java.sql.Date(TimeUtils.stringToDate(time.replace(" ","")).getTime()));
            logger.info("YK:" + title);
            videos.add(video);
        }
        return videos;
    }

    private void saveVideosToRedis(Document document, int videoType) {
        String videoKey = RedisKey.VIDEOS_KEY + "_" + videoType;
        redisService.saveByKey(videoKey, getYKVideosFromPcDocument(document));
    }
}
