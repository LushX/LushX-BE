package cn.mailu.LushX.repository;

import cn.mailu.LushX.entity.VideoRepertory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 12:38
 */
public interface VideoRepertoryRepository extends JpaRepository<VideoRepertory,String > {

    VideoRepertory findByUserId(String userId);
}
