package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.CommonUtils;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/16 11:47
 */
@RestController
@RequestMapping("/article")
@Api(value = "ArticleController",description = "文章展示接口")
public class ArticleController {

    private static Logger logger= LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private RedisService redisService;

    @ApiOperation(value="最热文章", notes="最热文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    @GetMapping("/hot")
    public ServerResponse<Page<Article>> getHotArticle(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Article> articles = (List<Article>) redisService.getValueByKey(RedisKey.JIANSHU_TRENDING_KEY+"_"+RedisKey.TAGS[2]);
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,articles));
    }
    @ApiOperation(value="最热文章", notes="最热文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    @GetMapping("/new")
    public ServerResponse<Page<Article>> getNewArticle(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Article> articles = (List<Article>) redisService.getValueByKey(RedisKey.JIANSHU_NEW_KEY + "_" + RedisKey.TAGS[2]);
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,articles));
    }
}
