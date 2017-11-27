package cn.mailu.LushX.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:35 2017-11-05
 * @Modified By:
 */
@Entity
@Table(name = "article_repertory",catalog = "",schema = "LushX")

public class ArticleRepertory implements java.io.Serializable {

	// Fields

	private String articleRepertoryId;
	private String userId;
	private Set<Article> articles = new HashSet<Article>(0);

	// Constructors

	/** default constructor */
	public ArticleRepertory() {
	}

	/** minimal constructor */
	public ArticleRepertory(String articleRepertoryId) {
		this.articleRepertoryId = articleRepertoryId;
	}

	/** full constructor */
	public ArticleRepertory(String articleRepertoryId, String userId, Set<Article> articles) {
		this.articleRepertoryId = articleRepertoryId;
		this.userId = userId;
		this.articles = articles;
	}

	// Property accessors
	@Id

	@Column(name = "article_repertory_id", unique = true, nullable = false, length = 40)

	public String getArticleRepertoryId() {
		return this.articleRepertoryId;
	}

	public void setArticleRepertoryId(String articleRepertoryId) {
		this.articleRepertoryId = articleRepertoryId;
	}

	@Column(name = "user_id", length = 40)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "repertory_article", catalog = "",schema = "LushX", joinColumns = {
			@JoinColumn(name = "article_repertory_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "article_id", nullable = false, updatable = false) })

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

}