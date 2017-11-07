package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.Video;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: NULL
 * @Description:Index页
 * @Date: Create in 2017/11/7 9:38
 */

@RestController
public class IndexController {

    @ApiOperation(value="video首页", notes="video首页")
    @GetMapping("/video")
    public ServerResponse<Map<String,List<Video>>> videoIndex(){
        Map<String,List<Video>> videoList= Maps.newHashMap();
        Video video=new Video();
        video.setVideoId("231543123141231");
        video.setImage("http://r1.ykimg.com/0516000059803B95ADBC0972D405B705");
        video.setTitle("小黄片");
        video.setPlayUrl("http://r1.ykimg.com/0516000059803B95ADBC0972D405B705");
        video.setType("这个是视频类型：1");
        video.setOther("这个是desc");
        video.setValue("这个是视频源地址");
        List<Video> tv=Lists.newArrayList();
        for(int i=0;i<3;i++){
            tv.add(video);
        }
        videoList.put("tv",tv);
        List<Video> lives=Lists.newArrayList();
        for(int i=0;i<3;i++){
            lives.add(video);
        }
        videoList.put("lives",lives);
        List<Video> animal=Lists.newArrayList();
        for(int i=0;i<3;i++){
            animal.add(video);
        }
        videoList.put("animal",animal);
        List<Video> movies=Lists.newArrayList();
        for(int i=0;i<3;i++){
            movies.add(video);
        }
        videoList.put("movies",movies);
        return ServerResponse.createBySuccess(videoList);
    }
}
