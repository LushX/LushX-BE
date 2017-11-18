package cn.mailu.LushX.crawler;

import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.entity.VideoInfo;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author:Drohe
 * @Description:优酷视频爬取
 * @Date:Created in 13:15 2017/11/6
 * @Modified By:
 */


public class YoukuCrawler {

    private static Logger logger = LoggerFactory.getLogger(YoukuCrawler.class);

    private final static String YK_TV_URL = "http://list.youku.com/category/show/c_97.html";
    private final static String YK_MOVIE_URL = "http://list.youku.com/category/show/c_96.html";
    private final static String YK_ZY_URL = "http://list.youku.com/category/show/c_85.html";
    private final static String YK_DM_URL = "http://list.youku.com/category/show/c_100.html";
    private static final String TAG = "YOUKU";

    @Autowired
    private RedisService redisService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void start() {
        Document ykTv = JsoupUtils.getDocWithPC(YK_TV_URL);
        Document ykMovie = JsoupUtils.getDocWithPC(YK_MOVIE_URL);
        Document ykZy = JsoupUtils.getDocWithPC(YK_ZY_URL);
        Document ykDm = JsoupUtils.getDocWithPC(YK_DM_URL);
        saveVideosToRedis(ykTv, VideoTypeEnum.YK_TV.getCode(),VideoTypeEnum.YK_TV_INFO.getCode());
        saveVideosToRedis(ykMovie, VideoTypeEnum.YK_MOVIE.getCode(),VideoTypeEnum.YK_MOVIE_INFO.getCode());
        saveVideosToRedis(ykZy, VideoTypeEnum.YK_ZY.getCode(),VideoTypeEnum.YK_ZY_INFO.getCode());
        saveVideosToRedis(ykDm, VideoTypeEnum.YK_DM.getCode(),VideoTypeEnum.YK_DM_INFO.getCode());

    }

    private List<Video> getYKVideosFromPcDocument(Document document) {
        List<Video> videos = new ArrayList<>();

        Elements videoElements = document.select("li.yk-col4");
        for (Element element : videoElements) {
            VideoInfo videoInfo = new VideoInfo();
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
            video.setOther(info);
            logger.info("YK:" + title);
            videos.add(video);
        }
        return videos;
    }

    private List<VideoInfo> getYKVideosInfo(List<Video> videos) {
        List<VideoInfo> videoInfos = new ArrayList<>();

        for (Video video : videos) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setVideoInfoId(video.getVideoId());
            videoInfo.setName(video.getTitle());

            Document document = JsoupUtils.getDocWithPC(video.getOther());
            Elements videoInfoElement = document.select("body > div.s-body > div > div.mod.mod-new > div.mod.fix");
            String actor = videoInfoElement.select("li.p-performer").attr("title");
            String alias = videoInfoElement.select("li.p-alias").attr("title");
            String area = videoInfoElement.select("li.p-performer+li+li").text().replace("地区：","");
            String director = videoInfoElement.select("li.p-performer+li").text().replace("导演：","");
            String score = videoInfoElement.select("li.p-score span.star-num").text();
            String time = videoInfoElement.select("span.pub").get(0).text().replace("上映：", "").replace("优酷开播：","").replace("优酷上映：","");


            videoInfo.setActor(actor);
            videoInfo.setAlias(alias);
            videoInfo.setArea(area);
            videoInfo.setDirector(director);
            videoInfo.setScore(score);
            videoInfo.setTime(new java.sql.Date(TimeUtils.stringToDate(time.replace(" ","")).getTime()));
            videoInfos.add(videoInfo);

        }

        return videoInfos;
    }

    private void saveVideosToRedis(Document document, int videoType,int videoInfoType) {
        String videoKey = RedisKey.VIDEOS_KEY + "_" + videoType;
        String videoInfoKey=RedisKey.VIDEOS_KEY + "_" + videoInfoType;
        logger.info("资源类型 ：" + videoType);
        redisService.saveByKey(videoInfoKey, getYKVideosInfo(getYKVideosFromPcDocument(document)));
        redisService.saveByKey(videoKey, getYKVideosFromPcDocument(document));
    }
}
