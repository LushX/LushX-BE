package cn.mailu.LushX.vo;

import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.ArticleRepertory;
import cn.mailu.LushX.entity.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 18:38
 */
public class ArticleVO {
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

    public static ArticleVO toArticleVO(Article a){
        ArticleVO articleVO=new ArticleVO();
        articleVO.setArticleId(a.getArticleId());
        articleVO.setAuthor(a.getAuthor());
        articleVO.setContent(a.getContent());
        articleVO.setCss(a.getCss());
        articleVO.setImage(a.getImage());
        articleVO.setOther(a.getOther());
        articleVO.setProvider(a.getProvider());
        articleVO.setTime(a.getTime());
        articleVO.setTitle(a.getTitle());
        articleVO.setValue(a.getValue());
        return articleVO;
    }
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
