package cn.mailu.LushX.crawler;

import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.Assert.*;

public class YoukuCrawlerTest {


    YoukuCrawler crawler=new YoukuCrawler(new RedisSourceManager(new StringRedisTemplate()));

    @Test
    public void start() throws Exception {

        crawler.start();

    }

}