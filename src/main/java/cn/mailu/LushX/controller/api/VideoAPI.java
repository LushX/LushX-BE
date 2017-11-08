package cn.mailu.LushX.controller.api;

import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.parser.ParserManager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: NULL
 * @Description:解析video接口
 * @Date: Create in 2017/11/8 18:38
 */
@RestController
@RequestMapping(value = "/api/v")
public class VideoAPI {

    @Autowired
    private ParserManager parserManager;

    @GetMapping
    @ApiOperation(value = "获取真实播放地址")
    @ApiImplicitParam(name = "url",value = "视频源地址",required = true)
    public Video getRealPlayUrl(@RequestParam(value = "url") String url) throws IOException {
        url = url.replaceAll("\\?(spm|from).*", "");
        return (Video) parserManager.parse(url);
    }
}
