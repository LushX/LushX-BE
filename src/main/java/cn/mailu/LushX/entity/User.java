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
@Table(name = "user",catalog = "",schema = "LushX")

public class User implements java.io.Serializable {

	// Fields

	private String userId;
	private String role;
	private String gender;
	private String headImg;
	private String loginType;
	private String md5;
	private String meta;
	private String password;
	private String username;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userId, String role, String md5, String password, String username) {
		this.userId = userId;
		this.role = role;
		this.md5 = md5;
		this.password = password;
		this.username = username;
	}

	/** full constructor */
	public User(String userId, String role, String gender, String headImg, String loginType, String md5, String meta,
			String password, String username) {
		this.userId = userId;
		this.role = role;
		this.gender = gender;
		this.headImg = headImg;
		this.loginType = loginType;
		this.md5 = md5;
		this.meta = meta;
		this.password = password;
		this.username = username;
	}

	// Property accessors
	@Id

	@Column(name = "user_id", unique = true, nullable = false, length = 40)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "role", nullable = false, length = 30)

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "gender", length = 1)

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "head_img", length = 50)

	public String getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	@Column(name = "login_type", length = 1)

	public String getLoginType() {
		return this.loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Column(name = "md5", nullable = false, length = 100)

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Column(name = "meta")

	public String getMeta() {
		return this.meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	@Column(name = "password", nullable = false, length = 60)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username", nullable = false, length = 20)

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}