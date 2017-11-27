package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.ArticleRepertory;
import cn.mailu.LushX.repository.ArticleRepertoryRepository;
import cn.mailu.LushX.service.ArticleRepertoryService;
import cn.mailu.LushX.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 12:58
 */
@Service
public class ArticleRepertotyServiceImpl implements ArticleRepertoryService {

    @Autowired
    private ArticleRepertoryRepository articleRepertoryRepository;

    @Override
    public ArticleRepertory findByUserId(String userId) {
        return articleRepertoryRepository.findByUserId(userId);
    }

    @Override
    public ArticleRepertory save(ArticleRepertory articleRepertory) {
        return articleRepertoryRepository.save(articleRepertory);
    }

    @Override
    public Page<Article> getLikeArticleListByUserId(String userId, Pageable pageable) {
    List articles= (List) articleRepertoryRepository.findByUserId(userId).getArticles();
        return CommonUtils.getPage(pageable,articles);
    }
}
