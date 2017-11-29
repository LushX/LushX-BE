package cn.mailu.LushX.util.crawlerHelper;

import cn.mailu.LushX.crawler.ChenluoCrawler;
import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.util.JsoupUtils;
import cn.mailu.LushX.util.PinYin4jUtils;
import cn.mailu.LushX.util.MD5Utils;
import cn.mailu.LushX.util.TimeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ChenluoCrawlerHelper {

    private static Logger logger = LoggerFactory.getLogger(ChenluoCrawlerHelper.class);


    /**
     * @param document
     * @param type
     *
     * 从列表页面获取电影
     *@Date: Created in 12:52 2017/11/28
     *
     */
    public static List<Video> getCLVideosFromPcDocument(Document document, int type) {
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
            String channel=infoBlock.select("a#zu1-tab").text();
            video.setValue(infoUrl+"?channel="+PinYin4jUtils.stringToPinyinString(channel).toString());
            video.setVideoId(MD5Utils.MD5EncodeUtf8(title));
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

            Elements epElements = epBlock.select("div#tvTabContent div.tab-pane").get(0).select("div[class~=^col-xs-1 play-[234]]");
            List<Episode> episodes = Lists.newArrayList();

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

                episode.setIndexs(epNum);
                episode.setEpisodeId(MD5Utils.MD5EncodeUtf8(title+epNumStr));
                episode.setValue(epUrl+"?channel="+PinYin4jUtils.stringToPinyinString(channel).toString());
                episodes.add(episode);
            }

            video.setEpisodes(episodes);

            try {

                video.setTime(new java.sql.Date(TimeUtils.stringToDate(time.replace(" ", "")).getTime()));

            } catch (LushXException e) {

                logger.error(e.getMessage());
                //每当时间出现异常格式，直接置为当前时间
                video.setTime(new java.sql.Date((new Date()).getTime()));
                // continue;
            }

            logger.info("CHENLUO:" + title);
            videos.add(video);
        }
        return videos;
    }
}
