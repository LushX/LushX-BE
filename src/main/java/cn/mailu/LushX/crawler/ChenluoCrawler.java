package cn.mailu.LushX.crawler;


import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.JsoupUtils;
import cn.mailu.LushX.util.crawlerHelper.ChenluoCrawlerHelper;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * @Author:Drohe
 * @Description:尘落电影爬虫
 * @Date:Created in 22:26 2017/11/25
 * @Modified By:
 */

@Component
@EnableAsync
public class ChenluoCrawler {

    //全局设定需要爬的页数
    private int pageNum = 3;

    private static Logger logger = LoggerFactory.getLogger(ChenluoCrawler.class);

    private final static String CL_TV_URL_HOT = "https://www.50s.cc/whole/2~~~~~~~0~hits~.html";
    private final static String CL_MOVIE_URL_HOT = "https://www.50s.cc/whole/1~~~~~~~0~hits~.html";
    private final static String CL_ZY_URL_HOT = "https://www.50s.cc/whole/4~~~~~~~0~hits~.html";
    private final static String CL_DM_URL_HOT = "https://www.50s.cc/whole/3~~~~~~~0~hits~.html";
    private final static String CL_TV_URL_NEW = "https://www.50s.cc/whole/2~~~~~~~0~id~.html";
    private final static String CL_MOVIE_URL_NEW = "https://www.50s.cc/whole/1~~~~~~~0~id~.html";
    private final static String CL_ZY_URL_NEW = "https://www.50s.cc/whole/4~~~~~~~0~id~.html";
    private final static String CL_DM_URL_NEW = "https://www.50s.cc/whole/3~~~~~~~0~id~.html";

    private static final String TAG = "CHENLUO";

    @Autowired
    private RedisService redisService;


    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    @Async
    public void start() {
        logger.info("================Chenluocrawler start=============");

        pageTurning(CL_TV_URL_HOT, VideoTypeEnum.CL_TV_HOT.getCode());
        pageTurning(CL_MOVIE_URL_HOT, VideoTypeEnum.CL_MOVIES_HOT.getCode());
        pageTurning(CL_ZY_URL_HOT, VideoTypeEnum.CL_ZY_HOT.getCode());
        pageTurning(CL_DM_URL_HOT, VideoTypeEnum.CL_DM_HOT.getCode());//部分属性为空
        pageTurning(CL_TV_URL_NEW, VideoTypeEnum.CL_TV_NEW.getCode());
        pageTurning(CL_MOVIE_URL_NEW, VideoTypeEnum.CL_MOVIES_NEW.getCode());
        pageTurning(CL_ZY_URL_NEW, VideoTypeEnum.CL_ZY_NEW.getCode());
       pageTurning(CL_DM_URL_NEW, VideoTypeEnum.CL_DM_NEW.getCode());
        logger.info("================Chenluocrawler stop=============");
    }

    /**
     * @param url
     * @param videoType 翻页
     * @Date: Created in 23:44 2017/11/25
     */
    private void pageTurning(String url, int videoType) {

        List<Document> documents = new ArrayList<>();

        for (int i = 1; i <= pageNum; i++) {

            String urlPage = url.replace(".html", "") + i + ".html";

            Document document;
            try {
                document = JsoupUtils.getDocWithPC(urlPage);
                documents.add(document);
            }catch (LushXException e){
                logger.error(e.getMessage());
                continue;
            }


            logger.info("第" + i + "页 列表爬取完毕！ URL:  " + urlPage);
        }
        saveVideosToRedis(documents, videoType);
    }


    /**
     * @param documents
     * @param videoType 存入redis数据库
     * @Date: Created in 23:44 2017/11/25
     */
    private void saveVideosToRedis(List<Document> documents, int videoType) {
        List<Video> videoList = new ArrayList<>();
        for (int i = 1; i <= pageNum; i++) {

            videoList.addAll(ChenluoCrawlerHelper.getCLVideosFromPcDocument(documents.get(i - 1), videoType));
        }
        String videoKey = RedisKey.VIDEOS_KEY + "_" + videoType;
        redisService.saveByKey(videoKey, videoList);
        logger.info("已存入redis！");
    }
}
