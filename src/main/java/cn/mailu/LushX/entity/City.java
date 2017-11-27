package cn.mailu.LushX.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:35 2017-11-05
 * @Modified By:
 */
@Entity
@Table(name = "city", catalog = "",schema = "LushX")

public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String pinyin;
	private Integer isOpen;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public City(Integer id, String name, String pinyin, Integer isOpen) {
		this.id = id;
		this.name = name;
		this.pinyin = pinyin;
		this.isOpen = isOpen;
	}

	// Property accessors
	@Id

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 10)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pinyin", length = 25)

	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Column(name = "isOpen")

	public Integer getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

}