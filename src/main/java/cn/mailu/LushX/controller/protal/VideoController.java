package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.service.RedisService;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        int fromIndex=pageable.getPageNumber()*pageable.getPageSize();
        int endIndex=(pageable.getPageNumber()+1)*pageable.getPageSize();
        Page<Video> page=new PageImpl<Video>(tvs.subList(fromIndex,endIndex),pageable,tvs.size());
        return ServerResponse.createBySuccess(page);
    }

    @ApiOperation(value="最新tv", notes="最新tv")
    @GetMapping("/tv/new")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "size", value = "页大小",defaultValue = "20",required = false,paramType ="query")
    })
    public ServerResponse<Page<Video>> getNewTv(@PageableDefault(value = 20,size = 20)Pageable pageable){
        List<Video> tvs = (List<Video>) redisService.getValueByKey(RedisKey.VIDEO_PREFIX_HOME_TV_KEY+"_"+ RedisKey.TAGS[0]);
        int fromIndex=pageable.getPageNumber()*pageable.getPageSize();
        int endIndex=(pageable.getPageNumber()+1)*pageable.getPageSize();
        Page<Video> page=new PageImpl<Video>(tvs.subList(fromIndex,endIndex),pageable,tvs.size());
        return ServerResponse.createBySuccess(page);
    }
}
