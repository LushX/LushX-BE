package cn.mailu.LushX.crawler;

import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.JsoupUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Drohe
 * @Description:优酷视频爬取
 * @Date:Created in 13:15 2017/11/6
 * @Modified By:
 */
public class YoukuCrawler {

    private final static String YK_TV_URL = "http://list.youku.com/category/show/c_97.html";
    private final static String YK_MOVIE_URL = "http://list.youku.com/category/show/c_96.html";
    private final static String YK_ZY_URL = "http://list.youku.com/category/show/c_85.html";
    private final static String YK_DM_URL = "http://list.youku.com/category/show/c_100.html";
    private static final String TAG = "YOUKU";

    @Autowired
    private RedisService redisService;


    private static Logger logger= LoggerFactory.getLogger(YoukuCrawler.class);




    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void start(){

        Document ykTv = JsoupUtils.getDocWithPC(YK_TV_URL);
        Document ykMovie = JsoupUtils.getDocWithPC(YK_MOVIE_URL);
        Document ykZy = JsoupUtils.getDocWithPC(YK_ZY_URL);
        Document ykDm = JsoupUtils.getDocWithPC(YK_DM_URL);
        saveVideosToRedis(ykTv, VideoTypeEnum.YK_TV.getCode());
        saveVideosToRedis(ykMovie, VideoTypeEnum.YK_MOVIE.getCode());
        saveVideosToRedis(ykZy, VideoTypeEnum.YK_ZY.getCode());
        saveVideosToRedis(ykDm, VideoTypeEnum.YK_DM.getCode());

    }

    private List<Video> getYKVideosFromPcDocument(Document document) {
        List<Video> videos = new ArrayList<>();
        Elements videoElements = document.select("li.yk-col4");
        for (Element element : videoElements) {
            Video video = new Video();
            String title = element.select("div.p-thumb a").get(0).attr("title");
            String image = element.select("img.quic").attr("src").replace("http:", "");
            String url = "http:" + element.select("div.p-thumb a").get(0).attr("href");
            video.setTitle(title);
            video.setImage(image);
            video.setValue(url);
            logger.info("YK:" + title);
            videos.add(video);
        }
        return videos;
    }

    private void saveVideosToRedis(Document document, int videoType) {
        String key = RedisKey.VIDEOS_KEY + "_" + videoType;
        logger.info("资源类型 ：" + videoType);
        redisService.saveByKey(key, getYKVideosFromPcDocument(document));
    }
}
