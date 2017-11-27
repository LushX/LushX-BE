package cn.mailu.LushX.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 2:32 2017-11-27
 * @Modified By:
 */
public class ArticleTypePK implements Serializable {
    private String typeId;
    private String articleId;

    @Column(name = "type_id", nullable = false, length = 1)
    @Id
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Column(name = "article_id", nullable = false, length = 40)
    @Id
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleTypePK that = (ArticleTypePK) o;

        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (articleId != null ? !articleId.equals(that.articleId) : that.articleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId != null ? typeId.hashCode() : 0;
        result = 31 * result + (articleId != null ? articleId.hashCode() : 0);
        return result;
    }
}
