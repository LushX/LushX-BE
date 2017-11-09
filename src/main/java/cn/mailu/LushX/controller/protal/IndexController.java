package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.crawler.RedisSourceManager;
import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.service.UserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: NULL
 * @Description:Index页
 * @Date: Create in 2017/11/7 9:38
 */

@RestController
@Api(value = "IndexController",description = "首页接口")
public class IndexController {

    private static Logger logger= LoggerFactory.getLogger(IndexController.class);

    @Resource
    private  RedisSourceManager redisSourceManager ;

    @ApiOperation(value="video首页", notes="video首页")
    @GetMapping("/video")
    public ServerResponse<Map<String,List<Video>>> videoIndex(){
        List<Video> carouselPics = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_CAROUSEL_KEY, redisSourceManager.TAGS[0]);
        List<Video> lives = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_RECOMMEND_KEY, redisSourceManager.TAGS[0]);
        List<Video> tvs = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_TV_KEY, redisSourceManager.TAGS[0]);
        List<Video> animations = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_CARTOON_KEY, redisSourceManager.TAGS[0]);
        List<Video> movies = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_MOVIE_KEY, redisSourceManager.TAGS[0]);
        List<Video> cams = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIx_HOME_LIVE_KEY, redisSourceManager.TAGS[1]);
        Map<String,List<Video>> videoList= Maps.newHashMap();
        videoList.put("carousel",carouselPics);
        videoList.put("tv",tvs);
        videoList.put("lives",lives);
        videoList.put("animation",animations);
        videoList.put("movies",movies);
        videoList.put("cams",cams);
        return ServerResponse.createBySuccess(videoList);
    }

    @ApiOperation(value="article首页", notes="article首页")
    @GetMapping("/article")
    public ServerResponse<List<Article>> articleIndex(){
        List<Article> articleList = redisSourceManager.getArticlesByKeyAndTag(redisSourceManager.JIANSHU_TRENDING_KEY,redisSourceManager.TAGS[2]);
        return ServerResponse.createBySuccess(articleList);
    }


}
