package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.ArticleRepertory;
import cn.mailu.LushX.repository.ArticleRepertoryRepository;
import cn.mailu.LushX.service.ArticleRepertoryService;
import cn.mailu.LushX.util.CommonUtils;
import cn.mailu.LushX.vo.ArticleVO;
import com.google.common.collect.Lists;
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
    public Page<ArticleVO> getLikeArticleListByUserId(String userId, Pageable pageable) {
        List<Article> articles= Lists.newArrayList(articleRepertoryRepository.findByUserId(userId).getArticles().iterator());
        List<ArticleVO> articleVOs=Lists.newArrayList();
        for(Article a: articles){
            articleVOs.add(ArticleVO.toArticleVO(a));
        }
        return CommonUtils.getPage(pageable,articleVOs);
    }

}
