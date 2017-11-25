package cn.mailu.LushX.crawler;


import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.JsoupUtils;
import cn.mailu.LushX.util.TimeUtils;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.commons.lang.StringUtils;
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

import java.text.ParseException;
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
    private int pageNum = 1;

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
     * @param document
     * @param type     从列表页面获取电影
     * @Date: Created in 22:52 2017/11/25
     */
    private List<Video> getCLVideosFromPcDocument(Document document, int type) {
        List<Video> videos = new ArrayList<>();
        //获取电影所在的元素区域
        Elements videoElements = document.select("div.col-xs-1-5.col-sm-4.col-xs-6.movie-item");
        for (Element element : videoElements) {
            Video video = new Video();
            String title = element.select("div.movie-item-in > a").attr("title");
            String image = element.select("div.movie-item-in > a>img").attr("src");
            String infoUrl = "https://www.50s.cc" + element.select("div.movie-item-in > a").attr("href");
            //获取详情页
            Document inpage = null;
            //catch 网页请求异常
            try {
                inpage = JsoupUtils.getDocWithPC(infoUrl);
            } catch (LushXException e) {
                logger.error(e.getErrorCode());
                continue;
            }

            Elements infoBlock = inpage.select("div.row");

            String director = infoBlock.select("td:contains(导演)+td >a").text();
            String actor = infoBlock.select("td:contains(主演)+td >a").text().replace("展开全部", "");
            String videoType = infoBlock.select("td:contains(类型)+td >a").text();
            String area = infoBlock.select("td:contains(地区)+td >a").text();
            String time = infoBlock.select("td:contains(年份)+td >a").text();
            String summary = infoBlock.select("div.col-xs-12.movie-introduce p").text().replace("&nbsp", "");
            //每个视频固有的id
            String vid = infoUrl.replace("/show/", "").replace(".html", "");

            video.setValue(vid);
            video.setVideoId(UUID.randomUUID().toString());
            video.setTitle(title);
            video.setImage(image);
            video.setType(videoType);
            video.setOther(summary);
            video.setActor(actor);
            // video.setAlias(alias);
            video.setArea(area);
            video.setDirector(director);
            //video.setScore(score);
            Element epBlock = infoBlock.select("div.row div.row").get(2);

            Elements epElements = epBlock.select("div#tvTabContent div.tab-pane").get(0).select("div[class~=^col-xs-1 play-]");
            Collection<Episode> episodes = new ArrayList<>();

            for (Element epElement : epElements) {

                Episode episode = new Episode();

                String epUrl = "https://www.50s.cc" + epElement.select("a").attr("href");

                String epNumStr = epElement.select("a").text()
                        .replace("第", "")
                        .replace("集", "")
                        .replace("期", "")
                        .trim();

                int epNum = 0;

                if (StringUtils.isNotEmpty(epNumStr) && StringUtils.isNotBlank(epNumStr) && StringUtils.isNumeric(epNumStr)) {

                    epNum = Integer.parseInt(epNumStr);

                }

                episode.setIndex(epNum);
                episode.setEpisodeId(UUID.randomUUID().toString());
                episode.setValue(epUrl);
                episodes.add(episode);
            }

            video.setEpisodesByVideoId(episodes);

            try {

                video.setTime(new java.sql.Date(TimeUtils.stringToDate(time.replace(" ", "")).getTime()));

            } catch (LushXException e) {

                logger.error(e.getMessage());
                //每当时间出现异常格式，直接置为当前时间
                video.setTime(new java.sql.Date((new Date()).getTime()));
                // continue;
            }




            /*String epNumStr=infoBlock.select("td:contains(状态)+td ").text().replace("更新至","").replace("集","").trim();

            int epNum=0;

            try{

                epNum=Integer.parseInt(epNumStr);

            }catch (Exception e){
                logger.error(e.getMessage());
                epNum=0;
            }

            for(int i=0;epNum!=0&&i<epNum;i++){



            }*/


            logger.info("CHENLUO:" + title);
            videos.add(video);
        }
        return videos;
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

            Document document = JsoupUtils.getDocWithPC(urlPage);

            documents.add(document);

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

            videoList.addAll(getCLVideosFromPcDocument(documents.get(i - 1), videoType));
        }
        String videoKey = RedisKey.VIDEOS_KEY + "_" + videoType;
        redisService.saveByKey(videoKey, videoList);
        logger.info("已存入redis！");
    }
}
