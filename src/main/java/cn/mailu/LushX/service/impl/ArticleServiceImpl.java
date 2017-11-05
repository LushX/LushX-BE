package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.repository.ArticleRepository;
import cn.mailu.LushX.service.ArticleService;
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
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public void insert(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void updateById(Article article) {
        articleRepository.delete(article.getArticleId());
        articleRepository.save(article);
    }

    @Override
    public void deleteById(String Id) {
        articleRepository.delete(Id);
    }

    @Override
    public Article selectById(String Id) {
        return articleRepository.findOne(Id);
    }

    @Override
    public List<Article> selectAll() {
        return articleRepository.findAll();
    }
}
