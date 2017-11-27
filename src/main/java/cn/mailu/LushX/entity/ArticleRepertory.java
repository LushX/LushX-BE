package cn.mailu.LushX.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:02 2017-11-26
 * @Modified By:
 */
@Entity
@Table(name = "article_repertory", schema = "LushX", catalog = "")
public class ArticleRepertory {
    private String articleRepertoryId;
    private String userId;
    private Collection<Article> articlesByArticleRepertoryId;

    @Id
    @Column(name = "article_repertory_id", nullable = false, length = 40)
    public String getArticleRepertoryId() {
        return articleRepertoryId;
    }

    public void setArticleRepertoryId(String articleRepertoryId) {
        this.articleRepertoryId = articleRepertoryId;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 40)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleRepertory that = (ArticleRepertory) o;

        if (articleRepertoryId != null ? !articleRepertoryId.equals(that.articleRepertoryId) : that.articleRepertoryId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleRepertoryId != null ? articleRepertoryId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "articleRepertoryByArticleRepertoryId")
    public Collection<Article> getArticlesByArticleRepertoryId() {
        return articlesByArticleRepertoryId;
    }

    public void setArticlesByArticleRepertoryId(Collection<Article> articlesByArticleRepertoryId) {
        this.articlesByArticleRepertoryId = articlesByArticleRepertoryId;
    }
}
