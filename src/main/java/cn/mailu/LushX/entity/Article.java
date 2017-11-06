package cn.mailu.LushX.entity;

import javax.persistence.*;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 4:20 2017-11-04
 * @Modified By:
 */
@Entity
@Table(name = "article", schema = "LushX", catalog = "")
public class Article {
    private String articleId;
    private String title;
    private String image;
    private String content;
    private String author;
    private String css;
    private String time;
    private String value;
    private String other;
    private String provider;

    @Id
    @Column(name = "article_id", nullable = false, length = 20)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 50)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 30)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "css", nullable = true, length = 255)
    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    @Basic
    @Column(name = "time", nullable = true, length = 20)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 50)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "other", nullable = true, length = 255)
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Basic
    @Column(name = "provider", nullable = true, length = 50)
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (articleId != null ? !articleId.equals(article.articleId) : article.articleId != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (image != null ? !image.equals(article.image) : article.image != null) return false;
        if (content != null ? !content.equals(article.content) : article.content != null) return false;
        if (author != null ? !author.equals(article.author) : article.author != null) return false;
        if (css != null ? !css.equals(article.css) : article.css != null) return false;
        if (time != null ? !time.equals(article.time) : article.time != null) return false;
        if (value != null ? !value.equals(article.value) : article.value != null) return false;
        if (other != null ? !other.equals(article.other) : article.other != null) return false;
        if (provider != null ? !provider.equals(article.provider) : article.provider != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleId != null ? articleId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (css != null ? css.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        return result;
    }
}
