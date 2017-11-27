package cn.mailu.LushX.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:35 2017-11-05
 * @Modified By:
 */
@Entity
@Table(name = "hotel_comment",catalog = "",schema = "LushX")

public class HotelComment implements java.io.Serializable {

	// Fields

	private String commentId;
	private HotelInfo hotelInfo;
	private String content;
	private Float avgScore;
	private Integer level;

	// Constructors

	/** default constructor */
	public HotelComment() {
	}

	/** minimal constructor */
	public HotelComment(String commentId, HotelInfo hotelInfo) {
		this.commentId = commentId;
		this.hotelInfo = hotelInfo;
	}

	/** full constructor */
	public HotelComment(String commentId, HotelInfo hotelInfo, String content, Float avgScore, Integer level) {
		this.commentId = commentId;
		this.hotelInfo = hotelInfo;
		this.content = content;
		this.avgScore = avgScore;
		this.level = level;
	}

	// Property accessors
	@Id

	@Column(name = "comment_id", unique = true, nullable = false, length = 40)

	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id", nullable = false)

	public HotelInfo getHotelInfo() {
		return this.hotelInfo;
	}

	public void setHotelInfo(HotelInfo hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	@Column(name = "content", length = 65535)

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "avg_score", precision = 12, scale = 0)

	public Float getAvgScore() {
		return this.avgScore;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

	@Column(name = "level")

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}