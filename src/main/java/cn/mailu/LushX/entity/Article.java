package cn.mailu.LushX.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:35 2017-11-05
 * @Modified By:
 */
@Entity
@Table(name = "article", catalog = "LushX")

public class Article implements java.io.Serializable {

	// Fields

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
	private Set<ArticleRepertory> articleRepertories = new HashSet<ArticleRepertory>(0);
	private Set<Type> types = new HashSet<Type>(0);

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(String articleId) {
		this.articleId = articleId;
	}

	/** full constructor */
	public Article(String articleId, String title, String image, String content, String author, String css, String time,
			String value, String other, String provider, Set<ArticleRepertory> articleRepertories, Set<Type> types) {
		this.articleId = articleId;
		this.title = title;
		this.image = image;
		this.content = content;
		this.author = author;
		this.css = css;
		this.time = time;
		this.value = value;
		this.other = other;
		this.provider = provider;
		this.articleRepertories = articleRepertories;
		this.types = types;
	}

	// Property accessors
	@Id

	@Column(name = "article_id", unique = true, nullable = false, length = 40)

	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Column(name = "title", length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "image", length = 50)

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "content", length = 65535)

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "author", length = 30)

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "css")

	public String getCss() {
		return this.css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	@Column(name = "time", length = 20)

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "value", length = 50)

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "other")

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Column(name = "provider", length = 50)

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "articles")

	public Set<ArticleRepertory> getArticleRepertories() {
		return this.articleRepertories;
	}

	public void setArticleRepertories(Set<ArticleRepertory> articleRepertories) {
		this.articleRepertories = articleRepertories;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "articles")

	public Set<Type> getTypes() {
		return this.types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

}