package cn.mailu.LushX.crawler;

import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.JsoupUtils;
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
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:12 2017-11-08
 * @Modified By:
 */
//@Component
@EnableAsync
public class JianShuCrawler {
    private static Logger logger= LoggerFactory.getLogger(JianShuCrawler.class);

    private final static String JIANSHU_URL="http://www.jianshu.com";
    private final static String JIANSHU_TRENDING_URL="http://www.jianshu.com/trending/weekly?page=";
    private final static String JIANSHU_NEW_URL="http://www.jianshu.com/recommendations/notes?category_id=56&max_id=1511078256&page=";


    @Autowired
    private RedisService redisService;

    /**
     * 每隔1天，爬简书7日热门
     */
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    @Async
    public void start(){
        logger.info("==================JianshuCrawler start===============");
        List<Document> documents = new ArrayList<>();
        // 爬取简书7日热门榜
        for (int i = 1; i < 10; i++){
            Document document = JsoupUtils.getDocWithPC(JIANSHU_TRENDING_URL + String.valueOf(i)); // 拼接文章列表url
            documents.add(document);
        }
        saveArticleToRedis(documents,RedisKey.JIANSHU_TRENDING_KEY + "_" + RedisKey.TAGS[2]);

        documents.clear();
        // 爬取简书最新上榜文章
        for (int i = 1; i <= 3; i++){
            Document document = JsoupUtils.getDocWithPC(JIANSHU_NEW_URL + String.valueOf(i)); // 拼接文章列表url
            documents.add(document);
        }
        saveArticleToRedis(documents,RedisKey.JIANSHU_NEW_KEY + "_" + RedisKey.TAGS[2]);
        logger.info("==================JianshuCrawler stop===============");

    }


    /**
     * 爬简书 将对象存入redis
     */
    private void saveArticleToRedis(List<Document> documents,String key) {
        List<Article> articleList = new ArrayList();
        for (Document document : documents) {
            Elements videoElement = document.select(".wrap-img");

            for(Element element:videoElement){
                String realUrl = JIANSHU_URL + element.attr("href");   // 拼接文章详情url
                Article article = getJianhuFromPcDocument(realUrl);
                articleList.add(article);
            }
        }

        redisService.saveByKey(key, articleList);
    }


    /**
     * 解析详情页面 返回 Article对象
     * @param url
     * @return Article
     */
    private Article getJianhuFromPcDocument(String url) {

        Document document= JsoupUtils.getDocWithPC(url);

        Article article = new Article();

        // 设置article属性
        article.setArticleId(UUID.randomUUID().toString());
        article.setTitle(document.select("h1.title").text());
        article.setAuthor(document.select("span.name a").text());
        article.setTime(document.select("span.publish-time").text().replace("*",""));
        article.setContent(document.select("div.show-content").toString());
        article.setImage(document.select("a.avatar img").attr("src").toString());
        article.setCss(document.getElementsByTag("meta").toString() + "\n"
                        + document.getElementsByTag("link"));

        return article;
    }

}
