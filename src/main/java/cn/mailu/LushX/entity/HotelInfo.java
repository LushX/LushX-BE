package cn.mailu.LushX.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:35 2017-11-05
 * @Modified By:
 */
@Entity
@Table(name = "hotel_info",catalog = "",schema = "LushX")

public class HotelInfo implements java.io.Serializable {

	// Fields

	private String hotelId;
	private String name;
	private String position;
	private String phone;
	private String info;
	private String introduction;
	private String sourceCity;
	private String img;
	private String sourceWeb;
	private String facility;
	private Integer comments;
	private Set<HotelComment> hotelComments = new HashSet<HotelComment>(0);

	// Constructors

	/** default constructor */
	public HotelInfo() {
	}

	/** minimal constructor */
	public HotelInfo(String hotelId) {
		this.hotelId = hotelId;
	}

	/** full constructor */
	public HotelInfo(String hotelId, String name, String position, String phone, String info, String introduction,
			String sourceCity, String img, String sourceWeb, String facility, Integer comments,
			Set<HotelComment> hotelComments) {
		this.hotelId = hotelId;
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.info = info;
		this.introduction = introduction;
		this.sourceCity = sourceCity;
		this.img = img;
		this.sourceWeb = sourceWeb;
		this.facility = facility;
		this.comments = comments;
		this.hotelComments = hotelComments;
	}

	// Property accessors
	@Id

	@Column(name = "hotel_id", unique = true, nullable = false, length = 40)

	public String getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	@Column(name = "name", length = 50)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "position", length = 50)

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "phone", length = 50)

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "info", length = 1024)

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "introduction", length = 65535)

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "source_city", length = 10)

	public String getSourceCity() {
		return this.sourceCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	@Column(name = "img", length = 1024)

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "source_web", length = 20)

	public String getSourceWeb() {
		return this.sourceWeb;
	}

	public void setSourceWeb(String sourceWeb) {
		this.sourceWeb = sourceWeb;
	}

	@Column(name = "facility", length = 1024)

	public String getFacility() {
		return this.facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	@Column(name = "comments")

	public Integer getComments() {
		return this.comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotelInfo")

	public Set<HotelComment> getHotelComments() {
		return this.hotelComments;
	}

	public void setHotelComments(Set<HotelComment> hotelComments) {
		this.hotelComments = hotelComments;
	}

}