package cn.mailu.LushX.crawler;


import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.util.JsoupUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 熊猫TV 爬虫
 */
@Component
public class PandaCrawler {

    private static Logger logger= LoggerFactory.getLogger(PandaCrawler.class);
    private static final String PANDA = "http://www.panda.tv/";
    private static final String PANDA_ALL = "http://www.panda.tv/all";
    private static final String TAG = "PANDA";

    private final RedisSourceManager redisSourceManager;

    public PandaCrawler(RedisSourceManager redisSourceManager) {
        this.redisSourceManager = redisSourceManager;
    }

    /**
     * 每隔20分钟，爬一次熊猫TV
     */
    @Scheduled(fixedRate = 20 * 60 * 1000)
    public void start() {
        Document document = JsoupUtils.getDocWithPC(PANDA_ALL);
        savePandaLivesToRedis(document);
    }

    private void savePandaLivesToRedis(Document document) {
        List<Video> lives = new ArrayList<>();
        Elements elements = document.select("li.video-list-item.video-no-tag");
        for (Element element : elements) {
            Video videoDTO = new Video();
            String title = element.select("div.video-info span.video-nickname").text();
            String image = element.select("img.video-img").attr("data-original");
            image = image.replace("http:", "");
            String url = PANDA + element.attr("data-id");
            videoDTO.setTitle(title);
            videoDTO.setImage(image);
            videoDTO.setValue(url);
            lives.add(videoDTO);
            if (lives.size() > 48) {
                break;
            }
        }
        String key = redisSourceManager.VIDEO_PREFIx_HOME_LIVE_KEY + "_" + TAG;
        redisSourceManager.saveVideos(key, lives);
    }
}
