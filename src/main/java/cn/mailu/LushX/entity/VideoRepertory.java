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
@Table(name = "video_repertory", catalog = "LushX")

public class VideoRepertory implements java.io.Serializable {

	// Fields

	private String videoRepertoryId;
	private String userId;
	private Set<Video> videos = new HashSet<Video>(0);

	// Constructors

	/** default constructor */
	public VideoRepertory() {
	}

	/** minimal constructor */
	public VideoRepertory(String videoRepertoryId) {
		this.videoRepertoryId = videoRepertoryId;
	}

	/** full constructor */
	public VideoRepertory(String videoRepertoryId, String userId, Set<Video> videos) {
		this.videoRepertoryId = videoRepertoryId;
		this.userId = userId;
		this.videos = videos;
	}

	// Property accessors
	@Id

	@Column(name = "video_repertory_id", unique = true, nullable = false, length = 40)

	public String getVideoRepertoryId() {
		return this.videoRepertoryId;
	}

	public void setVideoRepertoryId(String videoRepertoryId) {
		this.videoRepertoryId = videoRepertoryId;
	}

	@Column(name = "user_id", length = 40)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "repertory_video", catalog = "LushX", joinColumns = {
			@JoinColumn(name = "video_repertory_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "video_id", nullable = false, updatable = false) })

	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

}