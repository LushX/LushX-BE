package cn.mailu.LushX.controller.api;

import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.parser.Parser;
import cn.mailu.LushX.parser.ParserManager;
import cn.mailu.LushX.parser.site.QqParser;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.UrlUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author: NULL
 * @Description:解析video接口
 * @Date: Create in 2017/11/8 18:38
 */
@RestController
@Api(value = "解析video接口")
@RequestMapping(value = "/api/video")
public class VideoAPI {

    @Autowired
    private ParserManager parserManager;

    @Autowired
    private RedisService redisService;

    @GetMapping
    @ApiOperation(value = "获取真实播放地址")
    @ApiImplicitParam(name = "url",value = "视频源地址",required = true)
    public Video getRealPlayUrl(@RequestParam(value = "url") String url) throws IOException {
        url = url.replaceAll("\\?(spm|from).*", "");
        return (Video) parserManager.parse(url);
    }

    @GetMapping("/episode")
    @ApiOperation(value = "获取视频相关集数")
    @ApiImplicitParam(name = "url",value = "视频源地址",required = true)
    public List<Episode> getEpisodes(@RequestParam(value = "url") String url) throws IOException {
        url = url.replaceAll("\\?(spm|from).*", "");
        String key = UrlUtils.getTopDomain(url);
        Parser videoParse = parserManager.getParser(key);
        return videoParse.parseEpisodes(url);
    }

    /**
     * 解析腾讯视频片段
     */
    @GetMapping("/qq/{file}/{index}")
    @ApiOperation(value = "获取腾讯视频片段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名",required = true),
            @ApiImplicitParam(name = "fileIndex", value = "文件索引",required = true)
    })
    public Episode qqVideo(@PathVariable("fileName") String fileName, @PathVariable("fileIndex") Integer fileIndex) {
        return ((QqParser) parserManager.getParser("v.qq.com")).parsePart(fileName, fileIndex);
    }


    @GetMapping("/{type}")
    @ApiOperation(value = "获取分类排行榜视频")
    @ApiImplicitParam(name = "type",value = "视频分类",required = true)
    public List<Video> videos(@PathVariable("type") String type){
        return (List<Video>) redisService.getListByKey(RedisKey.VIDEOS_KEY+"_"+type,Video.class);
    }
}
