package cn.mailu.LushX.crawler;

import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.HttpClientUtils;
import cn.mailu.LushX.util.JsoupUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author:Drohe
 * @Description:网易云音乐热门评论爬取
 * @Date:Created in 15:23 2017/11/12
 * @Modified By:
 */

public class Music163Crawler {


    private static Logger logger= LoggerFactory.getLogger(Music163Crawler.class);

    private final static String MUSIC163_HOT_ORDER="http://music.163.com/#/discover/playlist/";
    private static final String TAG = "Music163";


    @Autowired
    private RedisService redisService;

    /**
     *
     *任务定时开启（每五个小时爬一次）
     *
     *@Date: Created in 15:24 2017/11/12
     *
     */

  //  @Scheduled(fixedRate = 5 * 60 * 60 * 1000)
    public void start() throws IOException {
        int offset=0;
        logger.info("==================Music163Crawler start===============");
        List<Document> documents = new ArrayList<>();
        for (int i = 1; i < 10; i++){
            Document document = Jsoup.parse(HttpClientUtils.fetch(MUSIC163_HOT_ORDER + "&cat=全部&limit=35&offset=" + offset));
            offset=offset+35;
            documents.add(document);
        }
        List<String>  SongMenu=new ArrayList<>();
        for(Document document:documents){

            SongMenu=getSongMenu(document);

            int a=2;

        }

        logger.info("==================Music163Crawler stop===============");
    }


    public  List<String>  getSongMenu(Document document){

         List<String>  SongMenu=new ArrayList<>();

         Element musicElement=document.getElementById("m-pl-container");

        Elements elements=musicElement.select(" li > div > a.msk");

        for(Element element:elements){

            SongMenu.add(element.select("a").attr("src"));

        }

        return SongMenu;

    }

}
