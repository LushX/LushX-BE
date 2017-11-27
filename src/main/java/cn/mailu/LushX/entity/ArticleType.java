package cn.mailu.LushX.entity;

import javax.persistence.*;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:33 2017-11-16
 * @Modified By:
 */
@Entity
@Table(name = "article_type", schema = "LushX", catalog = "")
@IdClass(ArticleTypePK.class)
public class ArticleType extends ArticleTypePK{

    private String typeId;
    private String articleId;
    private Article articleByArticleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ArticleType that = (ArticleType) o;

        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (articleId != null ? !articleId.equals(that.articleId) : that.articleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (articleId != null ? articleId.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "type_id", nullable = false, length = 1)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Id
    @Column(name = "article_id", nullable = false, length = 40)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "article_id", nullable = false)
    public Article getArticleByArticleId() {
        return articleByArticleId;
    }

    public void setArticleByArticleId(Article articleByArticleId) {
        this.articleByArticleId = articleByArticleId;
    }
}
