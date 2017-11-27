package cn.mailu.LushX.repository;

import cn.mailu.LushX.entity.ArticleRepertory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 12:36
 */
public interface ArticleRepertoryRepository extends JpaRepository<ArticleRepertory,String>{

    ArticleRepertory findByUserId(String userId);
}
