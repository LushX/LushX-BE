package cn.mailu.LushX.repository;

import cn.mailu.LushX.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 8:39 2017-11-05
 * @Modified By:
 */
public interface ArticleRepository extends JpaRepository<Article, String> {
}
