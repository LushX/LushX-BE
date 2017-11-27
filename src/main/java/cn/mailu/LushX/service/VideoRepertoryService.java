package cn.mailu.LushX.service;

import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.entity.VideoRepertory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 12:58
 */
public interface VideoRepertoryService {

    VideoRepertory findByUserId(String userId);

    VideoRepertory save(VideoRepertory videoRepertory);

    Page<Video> getLikeVideoListByUserId(String userId, Pageable pageable);
}
