package cn.mailu.LushX.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 2:32 2017-11-27
 * @Modified By:
 */
@Entity
public class Video {
    private String videoId;
    private String videoRepertoryId;
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

    @Id
    @Column(name = "video_id", nullable = false, length = 40)
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "video_repertory_id", nullable = true, length = 40)
    public String getVideoRepertoryId() {
        return videoRepertoryId;
    }

    public void setVideoRepertoryId(String videoRepertoryId) {
        this.videoRepertoryId = videoRepertoryId;
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
    @Column(name = "alias", nullable = true, length = 50)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
        if (videoRepertoryId != null ? !videoRepertoryId.equals(video.videoRepertoryId) : video.videoRepertoryId != null)
            return false;
        if (title != null ? !title.equals(video.title) : video.title != null) return false;
        if (alias != null ? !alias.equals(video.alias) : video.alias != null) return false;
        if (image != null ? !image.equals(video.image) : video.image != null) return false;
        if (playUrl != null ? !playUrl.equals(video.playUrl) : video.playUrl != null) return false;
        if (type != null ? !type.equals(video.type) : video.type != null) return false;
        if (value != null ? !value.equals(video.value) : video.value != null) return false;
        if (director != null ? !director.equals(video.director) : video.director != null) return false;
        if (actor != null ? !actor.equals(video.actor) : video.actor != null) return false;
        if (area != null ? !area.equals(video.area) : video.area != null) return false;
        if (score != null ? !score.equals(video.score) : video.score != null) return false;
        if (time != null ? !time.equals(video.time) : video.time != null) return false;
        if (other != null ? !other.equals(video.other) : video.other != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = videoId != null ? videoId.hashCode() : 0;
        result = 31 * result + (videoRepertoryId != null ? videoRepertoryId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (playUrl != null ? playUrl.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (actor != null ? actor.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (other != null ? other.hashCode() : 0);
        return result;
    }
}
