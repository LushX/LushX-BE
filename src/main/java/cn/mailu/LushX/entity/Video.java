package cn.mailu.LushX.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:35 2017-11-05
 * @Modified By:
 */
@Entity
@Table(name = "video", catalog = "LushX")

public class Video implements java.io.Serializable {

	// Fields

	private String videoId;
	private String title;
	private String alias;
	private String image;
	private String playUrl;
	private String type;
	private String value;
	private String director;
	private String actor;
	private String area;
	private String score;
	private Date time;
	private String other;
	private Set<Episode> episodes = new HashSet<Episode>(0);
	private Set<VideoRepertory> videoRepertories = new HashSet<VideoRepertory>(0);

	// Constructors

	/** default constructor */
	public Video() {
	}

	/** minimal constructor */
	public Video(String videoId) {
		this.videoId = videoId;
	}

	/** full constructor */
	public Video(String videoId, String title, String alias, String image, String playUrl, String type, String value,
			String director, String actor, String area, String score, Date time, String other, Set<Episode> episodes,
			Set<VideoRepertory> videoRepertories) {
		this.videoId = videoId;
		this.title = title;
		this.alias = alias;
		this.image = image;
		this.playUrl = playUrl;
		this.type = type;
		this.value = value;
		this.director = director;
		this.actor = actor;
		this.area = area;
		this.score = score;
		this.time = time;
		this.other = other;
		this.episodes = episodes;
		this.videoRepertories = videoRepertories;
	}

	// Property accessors
	@Id

	@Column(name = "video_id", unique = true, nullable = false, length = 40)

	public String getVideoId() {
		return this.videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	@Column(name = "title", length = 50)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "alias", length = 50)

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "image", length = 50)

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "playUrl", length = 100)

	public String getPlayUrl() {
		return this.playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

	@Column(name = "type", length = 2)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "value", length = 50)

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "director", length = 15)

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Column(name = "actor", length = 50)

	public String getActor() {
		return this.actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	@Column(name = "area", length = 15)

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "score", length = 2)

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "time", length = 10)

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "other")

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "video")

	public Set<Episode> getEpisodes() {
		return this.episodes;
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "videos")

	public Set<VideoRepertory> getVideoRepertories() {
		return this.videoRepertories;
	}

	public void setVideoRepertories(Set<VideoRepertory> videoRepertories) {
		this.videoRepertories = videoRepertories;
	}

}