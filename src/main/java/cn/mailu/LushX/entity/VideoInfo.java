package cn.mailu.LushX.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:32 2017-11-16
 * @Modified By:
 */
@Entity
@Table(name = "video_info", schema = "LushX", catalog = "")
public class VideoInfo {
    private String videoInfoId;
    private String name;
    private String alias;
    private String director;
    private String actor;
    private String area;
    private Date time;
    private String score;
    private Collection<VideoInfoType> videoInfoTypesByVideoInfoId;

    @Id
    @Column(name = "video_info_id", nullable = false, length = 40)
    public String getVideoInfoId() {
        return videoInfoId;
    }

    public void setVideoInfoId(String videoInfoId) {
        this.videoInfoId = videoInfoId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "alias", nullable = true, length = 30)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Basic
    @Column(name = "director", nullable = true, length = 20)
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
    @Column(name = "time", nullable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "score", nullable = true, length = 4)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoInfo videoInfo = (VideoInfo) o;

        if (videoInfoId != null ? !videoInfoId.equals(videoInfo.videoInfoId) : videoInfo.videoInfoId != null)
            return false;
        if (name != null ? !name.equals(videoInfo.name) : videoInfo.name != null) return false;
        if (alias != null ? !alias.equals(videoInfo.alias) : videoInfo.alias != null) return false;
        if (director != null ? !director.equals(videoInfo.director) : videoInfo.director != null) return false;
        if (actor != null ? !actor.equals(videoInfo.actor) : videoInfo.actor != null) return false;
        if (area != null ? !area.equals(videoInfo.area) : videoInfo.area != null) return false;
        if (time != null ? !time.equals(videoInfo.time) : videoInfo.time != null) return false;
        if (score != null ? !score.equals(videoInfo.score) : videoInfo.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = videoInfoId != null ? videoInfoId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (actor != null ? actor.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "videoInfoByVideoInfoId")
    public Collection<VideoInfoType> getVideoInfoTypesByVideoInfoId() {
        return videoInfoTypesByVideoInfoId;
    }

    public void setVideoInfoTypesByVideoInfoId(Collection<VideoInfoType> videoInfoTypesByVideoInfoId) {
        this.videoInfoTypesByVideoInfoId = videoInfoTypesByVideoInfoId;
    }
}
