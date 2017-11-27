package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.entity.VideoRepertory;
import cn.mailu.LushX.repository.VideoRepertoryRepository;
import cn.mailu.LushX.service.VideoRepertoryService;
import cn.mailu.LushX.util.CommonUtils;
import cn.mailu.LushX.vo.ArticleVO;
import cn.mailu.LushX.vo.VideoVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 12:59
 */
@Service
public class VideoRepertoryServiceImpl implements VideoRepertoryService{

    @Autowired
    private VideoRepertoryRepository videoRepertoryRepository;

    @Override
    public VideoRepertory findByUserId(String userId) {
        return videoRepertoryRepository.findByUserId(userId);
    }

    @Override
    public VideoRepertory save(VideoRepertory videoRepertory) {
        return videoRepertoryRepository.save(videoRepertory);
    }

    @Override
    public Page<VideoVO> getLikeVideoListByUserId(String userId, Pageable pageable) {
        List<Video> videos= Lists.newArrayList(videoRepertoryRepository.findByUserId(userId).getVideos().iterator());
        List<VideoVO> videoVOs=Lists.newArrayList();
        for(Video v: videos){
            videoVOs.add(VideoVO.toVideoVO(v));
        }
        return CommonUtils.getPage(pageable,videos);
    }
}
