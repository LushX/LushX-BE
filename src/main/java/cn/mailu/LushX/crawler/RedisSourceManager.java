package cn.mailu.LushX.crawler;


import cn.mailu.LushX.entity.Video;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 视频资源管理器
 */
@Component
public class RedisSourceManager {

  public final String VIDEO_PREFIX_HOME_CAROUSEL_KEY = "HOME_VIDEO_CAROUSEL";
  public final String VIDEO_PREFIX_HOME_RECOMMEND_KEY = "HOME_VIDEO_RECOMMEND";
  public final String VIDEO_PREFIX_HOME_TV_KEY = "HOME_VIDEO_TV";
  public final String VIDEO_PREFIX_HOME_MOVIE_KEY = "HOME_VIDEO_MOVIE";
  public final String VIDEO_PREFIX_HOME_CARTOON_KEY = "HOME_VIDEO_CARTOON";
  public final String VIDEO_PREFIx_HOME_LIVE_KEY = "HOME_LIVE";
  public final String VIDEOS_KEY = "VIDEOS";

  private final StringRedisTemplate stringRedisTemplate;

  public RedisSourceManager(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate;
  }

  /**
   *  保存视频信息到 Redis
   */
  void saveVideos(String key, List<Video> videos){
    ObjectMapper mapper = new ObjectMapper();
    try {
      String value=mapper.writeValueAsString(videos);
      stringRedisTemplate.opsForValue().set(key, value);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  /**
   *  得到视频信息
   */
  public List<Video> getVideosByKeyAndTag(String key, String tag){
    ObjectMapper mapper = new ObjectMapper();
    key = key + "_" + tag;
    String cacheValue = stringRedisTemplate.opsForValue().get(key);
    try {
      return mapper.readValue(cacheValue,mapper.getTypeFactory().constructParametricType(List.class,Video.class));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
