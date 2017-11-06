package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.repository.VideoRepository;
import cn.mailu.LushX.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:00 2017-11-05
 * @Modified By:
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;


    @Override
    public void insert(Video video) {
        videoRepository.save(video);
    }

    @Override
    public void updateById(Video video) {
        videoRepository.delete(video.getVideoId());
        videoRepository.save(video);
    }

    @Override
    public void deleteById(String Id) {
        videoRepository.delete(Id);
    }

    @Override
    public Video selectById(String Id) {
        return videoRepository.findOne(Id);
    }

    @Override
    public List<Video> selectAll() {
        return videoRepository.findAll();
    }
}
