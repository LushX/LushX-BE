package cn.mailu.LushX.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:39 2017-11-16
 * @Modified By:
 */
@Entity
public class Video {
    private String videoId;
    private String title;
    private String image;
    private String playUrl;
    private String type;
    private String value;
    private String other;
    private Collection<Episode> episodesByVideoId;
    private String alias;
    private String director;
    private String actor;
    private String area;
    private String score;
    private Date time;

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
    @Column(name = "type", nullable = true, length = 2)
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

    @OneToMany(mappedBy = "videoByVideoId")
    public Collection<Episode> getEpisodesByVideoId() {
        return episodesByVideoId;
    }

    public void setEpisodesByVideoId(Collection<Episode> episodesByVideoId) {
        this.episodesByVideoId = episodesByVideoId;
    }

    @Basic
    @Column(name = "alias", nullable = true, length = 50)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Basic
    @Column(name = "director", nullable = true, length = 15)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "actor", nullable = true, length = 50)
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Basic
    @Column(name = "area", nullable = true, length = 15)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "score", nullable = true, length = 2)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
