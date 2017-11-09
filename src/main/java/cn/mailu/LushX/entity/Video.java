package cn.mailu.LushX.entity;

import javax.persistence.*;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 4:21 2017-11-04
 * @Modified By:
 */
@Entity
@Table(name = "video", schema = "LushX", catalog = "")
public class Video {
    private String videoId;
    private String title;
    private String image;
    private String playUrl;
    private String type;
    private String value;
    private String other;

    @Id
    @Column(name = "video_id", nullable = false, length = 40)
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 50)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "playUrl", nullable = true, length = 100)
    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 50)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "other", nullable = true, length = 255)
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        if (videoId != null ? !videoId.equals(video.videoId) : video.videoId != null) return false;
        if (title != null ? !title.equals(video.title) : video.title != null) return false;
        if (image != null ? !image.equals(video.image) : video.image != null) return false;
        if (playUrl != null ? !playUrl.equals(video.playUrl) : video.playUrl != null) return false;
        if (type != null ? !type.equals(video.type) : video.type != null) return false;
        if (value != null ? !value.equals(video.value) : video.value != null) return false;
        if (other != null ? !other.equals(video.other) : video.other != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = videoId != null ? videoId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (playUrl != null ? playUrl.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        return result;
    }
}
