package cn.mailu.LushX.repository;

import cn.mailu.LushX.entity.ArticleType;
import cn.mailu.LushX.entity.ArticleTypePK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 8:43 2017-11-05
 * @Modified By:
 */
public interface ArticleTypeRepository extends JpaRepository<ArticleType,ArticleTypePK> {
}
