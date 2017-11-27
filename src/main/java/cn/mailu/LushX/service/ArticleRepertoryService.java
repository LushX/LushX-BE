package cn.mailu.LushX.service;

import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.ArticleRepertory;
import cn.mailu.LushX.vo.ArticleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 12:56
 */
public interface ArticleRepertoryService {

    ArticleRepertory findByUserId(String userId);

    ArticleRepertory save(ArticleRepertory articleRepertory);

    Page<ArticleVO> getLikeArticleListByUserId(String userId, Pageable pageable);
}
