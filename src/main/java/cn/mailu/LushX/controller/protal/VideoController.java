package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.entity.Video;
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
 * @Date: Create in 2017/11/16 10:29
 */
@RestController
@RequestMapping("/video")
@Api(value = "VideoController",description = "视频展示接口")
public class VideoController {

    private static Logger logger= LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private RedisService redisService;


    @ApiOperation(value="最热tv", notes="最热tv")
    @GetMapping("/tv/hot")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getHotTv(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> tvs = (List<Video>) redisService.getValueByKey(RedisKey.VIDEO_PREFIX_HOME_TV_KEY+"_"+ RedisKey.TAGS[0]);
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,tvs));
    }

    @ApiOperation(value="最新tv", notes="最新tv")
    @GetMapping("/tv/new")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getNewTv(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> tvs = (List<Video>) redisService.getValueByKey(RedisKey.VIDEO_PREFIX_HOME_TV_KEY+"_"+ RedisKey.TAGS[0]);
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,tvs));
    }

}
