package cn.mailu.LushX.crawler;

import cn.mailu.LushX.entity.Article;
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
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:12 2017-11-08
 * @Modified By:
 */
@Component
public class JianShuCrawler {
    private static Logger logger= LoggerFactory.getLogger(JianShuCrawler.class);

    private final static String JIANSHU_URL="http://www.jianshu.com";
    private final static String JIANSHU_TRENDING_URL="http://www.jianshu.com/trending/weekly?page=";
    private static final String TAG = "Jianshu";

    private final RedisSourceManager redisSourceManager;

    public JianShuCrawler(RedisSourceManager redisSourceManager) {
        this.redisSourceManager = redisSourceManager;
    }


    /**
     * 每隔1天，爬简书7日热门
     */
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void start(){
        logger.info("==================JianshuCrawler start===============");
        for (int i = 1; i < 10; i++){
            Document document = JsoupUtils.getDocWithPC(JIANSHU_TRENDING_URL + String.valueOf(i)); // 拼接文章列表url
            saveArticleToRedis(document);
        }
        logger.info("==================JianshuCrawler stop===============");
    }


    /**
     * 爬简书 将对象存入redis
     */
    private void saveArticleToRedis(Document document) {
        Elements videoElement = document.select(".wrap-img");
        List<Article> articleList = new ArrayList();

        for(Element element:videoElement){
            String realUrl = JIANSHU_URL + element.attr("href");   // 拼接文章详情url
            Article article = getJianhuFromPcDocument(realUrl);
            articleList.add(article);
        }
        String key = redisSourceManager.JIANSHU_TRENDING_KEY + "_" + TAG;
        redisSourceManager.saveArticle(key, articleList);
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
        article.setTitle(document.select("h1.title").text());
        article.setAuthor(document.select("span.name a").text());
        article.setTime(document.select("span.publish-time").text());
        article.setContent(document.select("div.show-content").toString());
        article.setImage(document.select("a.avatar img").attr("src").toString());

        return article;
    }


}
