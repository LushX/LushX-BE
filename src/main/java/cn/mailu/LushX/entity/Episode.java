package cn.mailu.LushX.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "episode", catalog = "",schema = "LushX")

public class Episode implements java.io.Serializable {

	// Fields

	private String episodeId;
	private Video video;
	private Integer indexs;
	private String value;

	// Constructors

	/** default constructor */
	public Episode() {
	}

	/** minimal constructor */
	public Episode(String episodeId, Video video) {
		this.episodeId = episodeId;
		this.video = video;
	}

	/** full constructor */
	public Episode(String episodeId, Video video, Integer indexs, String value) {
		this.episodeId = episodeId;
		this.video = video;
		this.indexs = indexs;
		this.value = value;
	}

	// Property accessors
	@Id

	@Column(name = "episode_id", unique = true, nullable = false, length = 40)

	public String getEpisodeId() {
		return this.episodeId;
	}

	public void setEpisodeId(String episodeId) {
		this.episodeId = episodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "video_id", nullable = false)

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@Column(name = "indexs")

	public Integer getIndexs() {
		return this.indexs;
	}

	public void setIndexs(Integer indexs) {
		this.indexs = indexs;
	}

	@Column(name = "value", length = 50)

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}