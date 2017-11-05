package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.repository.EpisodeRepository;
import cn.mailu.LushX.service.EpisodeService;
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
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;


    @Override
    public void insert(Episode episode) {
        episodeRepository.save(episode);
    }

    @Override
    public void updateById(Episode episode) {
        episodeRepository.delete(episode.getEpisodeId());
        episodeRepository.save(episode);
    }

    @Override
    public void deleteById(String Id) {
        episodeRepository.delete(Id);
    }

    @Override
    public Episode selectById(String Id) {
        return episodeRepository.findOne(Id);
    }

    @Override
    public List<Episode> selectAll() {
        return episodeRepository.findAll();
    }
}
