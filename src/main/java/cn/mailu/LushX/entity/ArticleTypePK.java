package cn.mailu.LushX.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 4:21 2017-11-04
 * @Modified By:
 */
public class ArticleTypePK implements Serializable {
    private String articleId;
    private String typeId;

    @Column(name = "article_id", nullable = false, length = 40)
    @Id
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Column(name = "type_id", nullable = false, length = 1)
    @Id
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleTypePK that = (ArticleTypePK) o;

        if (articleId != null ? !articleId.equals(that.articleId) : that.articleId != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleId != null ? articleId.hashCode() : 0;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        return result;
    }
}
