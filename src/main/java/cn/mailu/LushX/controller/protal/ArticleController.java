package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.ArticleRepertory;
import cn.mailu.LushX.security.JWTUserDetails;
import cn.mailu.LushX.service.ArticleRepertoryService;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.CommonUtils;
import cn.mailu.LushX.vo.ArticleVO;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/16 11:47
 */
@RestController
@RequestMapping("/article")
@Api(value = "ArticleController", description = "文章展示接口")
public class ArticleController {

    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private ArticleRepertoryService articleRepertoryService;


    @ApiOperation(value = "最热文章", notes = "最热文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", defaultValue = "0", required = false, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页大小", defaultValue = "20", required = false, paramType = "query")
    })
    @GetMapping("/hot")
    public ServerResponse<Page<Article>> getHotArticle(@PageableDefault(value = 20, size = 20) Pageable pageable) {
        List<Article> articles = (List<Article>) redisService.getValueByKey(RedisKey.JIANSHU_TRENDING_KEY + "_" + RedisKey.TAGS[2]);
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable, articles));
    }

    @ApiOperation(value = "最热文章", notes = "最热文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", defaultValue = "0", required = false, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页大小", defaultValue = "20", required = false, paramType = "query")
    })
    @GetMapping("/new")
    public ServerResponse<Page<Article>> getNewArticle(@PageableDefault(value = 20, size = 20) Pageable pageable) {
        List<Article> articles = (List<Article>) redisService.getValueByKey(RedisKey.JIANSHU_NEW_KEY + "_" + RedisKey.TAGS[2]);
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable, articles));
    }

    //文章收藏api

    @ApiOperation(value = "收藏文章", notes = "收藏文章")
    @ApiImplicitParam(name = "article", value = "收藏文章实体类", required = true,dataType = "Article")
    @PostMapping("/like")
    public ServerResponse likeArticle(@AuthenticationPrincipal JWTUserDetails jwtuser, @RequestBody Article article) {
        if (jwtuser != null) {
            ArticleRepertory articleRepertory = articleRepertoryService.findByUserId(jwtuser.getUserId());
            if(articleRepertory.getArticles().contains(article)){
                return ServerResponse.createBySuccessMessage("已收藏");
            }
            articleRepertory.getArticles().add(article);
            if (articleRepertoryService.save(articleRepertory) != null) {
                return ServerResponse.createBySuccessMessage("收藏成功");
            }
            return ServerResponse.createByErrorMessage("收藏失败");
        }
        return ServerResponse.createByErrorMessage("未登录");
    }

    @ApiOperation(value = "取消收藏文章", notes = "取消收藏文章")
    @ApiImplicitParam(name = "articleId", value = "取消收藏文章id", required = true,dataType ="path" )
    @PostMapping("/dislike/{articleId}")
    public ServerResponse dislikeArticle(@AuthenticationPrincipal JWTUserDetails jwtuser, @PathVariable String articleId) {
        if (jwtuser != null) {
            ArticleRepertory articleRepertory = articleRepertoryService.findByUserId(jwtuser.getUserId());
            Collection<Article> articles = articleRepertory.getArticles();
            Iterator<Article> it=articles.iterator();
            while(it.hasNext()){
                Article a=it.next();
                if(a.getArticleId().equals(articleId)){
                    it.remove();
                }
            }
            articleRepertory.setArticles(articles);
            if (articleRepertoryService.save(articleRepertory) != null) {
                return ServerResponse.createBySuccessMessage("取消收藏成功");
            }
            return ServerResponse.createByErrorMessage("取消收藏失败");
        }
        return ServerResponse.createByErrorMessage("未登录");
    }

    @ApiOperation(value = "是否已收藏文章", notes = "是否已收藏文章")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, paramType = "query")
    @GetMapping("/islike")
    public ServerResponse isLike(@AuthenticationPrincipal JWTUserDetails jwtuser, @RequestParam String articleId) {
        Map res = Maps.newHashMap();
        res.put("isLike", false);
        if (jwtuser != null) {
            ArticleRepertory articleRepertory = articleRepertoryService.findByUserId(jwtuser.getUserId());
            for (Article a : articleRepertory.getArticles()) {
                if (a.getArticleId().equals(articleId) ) {
                    res.put("isLike", true);
                    break;
                }
            }
            return ServerResponse.createBySuccess(res);
        }
        return ServerResponse.createByErrorMessage("未登录");
    }

    @ApiOperation(value = "收藏文章列表", notes = "收藏文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", defaultValue = "0", required = false, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页大小", defaultValue = "20", required = false, paramType = "query")
    })
    @GetMapping("/like")
    public ServerResponse<Page<ArticleVO>> getLikeArticle(@AuthenticationPrincipal JWTUserDetails jwtuser, @PageableDefault(value = 20, size = 20) Pageable pageable) {
        if (jwtuser != null) {
            return ServerResponse.createBySuccess(articleRepertoryService.getLikeArticleListByUserId(jwtuser.getUserId(),pageable));
        }
        return ServerResponse.createByErrorMessage("未登录");
    }
}
