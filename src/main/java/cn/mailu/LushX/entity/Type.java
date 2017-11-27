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
@Table(name = "type", catalog = "",schema = "LushX")

public class Type implements java.io.Serializable {

	// Fields

	private String typeId;
	private String icon;
	private String name;
	private Set<Article> articles = new HashSet<Article>(0);

	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(String typeId) {
		this.typeId = typeId;
	}

	/** full constructor */
	public Type(String typeId, String icon, String name, Set<Article> articles) {
		this.typeId = typeId;
		this.icon = icon;
		this.name = name;
		this.articles = articles;
	}

	// Property accessors
	@Id

	@Column(name = "type_id", unique = true, nullable = false, length = 2)

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Column(name = "icon", length = 20)

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "name", length = 20)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "article_type", catalog = "LushX", joinColumns = {
			@JoinColumn(name = "type_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "article_id", nullable = false, updatable = false) })

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

}