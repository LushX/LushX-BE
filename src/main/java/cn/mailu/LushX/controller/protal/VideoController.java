package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.service.RedisService;
import cn.mailu.LushX.util.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.CL_TV_HOT.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @ApiOperation(value="最新tv", notes="最新tv")
    @GetMapping("/tv/new")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getNewTv(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.CL_TV_NEW.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @ApiOperation(value="最热综艺", notes="最热综艺")
    @GetMapping("/zy/hot")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getHotZy(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.YK_ZY_HOT.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @ApiOperation(value="最新综艺", notes="最新综艺")
    @GetMapping("/zy/new")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getNewZy(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.CL_ZY_NEW.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @ApiOperation(value="最热movie", notes="最热movie")
    @GetMapping("/movie/hot")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getHotMovie(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.CL_MOVIES_HOT.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @ApiOperation(value="最新movie", notes="最新movie")
    @GetMapping("/movie/new")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getNewMovie(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.CL_MOVIES_NEW.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @ApiOperation(value="最热动漫", notes="最热动漫")
    @GetMapping("/cartoon/hot")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getHotDM(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.CL_DM_HOT.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @ApiOperation(value="最新动漫", notes="最新动漫")
    @GetMapping("/cartoon/new")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getNewDM(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> videos = (List<Video>) redisService.getValueByKey(RedisKey.VIDEOS_KEY+"_"+ VideoTypeEnum.CL_DM_NEW.getCode());
        return ServerResponse.createBySuccess(CommonUtils.getPage(pageable,videos));
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索视频")
    @ApiImplicitParam(name = "keyword",value = "关键词",required = true,paramType = "query")
    public ServerResponse searchVideo(@RequestParam(value = "keyword") String keyword) throws IOException {
        if(StringUtils.isNotEmpty(keyword.trim())){
            //todo 搜索
        }
        return ServerResponse.createByErrorMessage("关键词不为空");
    }

}
