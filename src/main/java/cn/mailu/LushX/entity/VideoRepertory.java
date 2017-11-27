package cn.mailu.LushX.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 5:03 2017-11-26
 * @Modified By:
 */
@Entity
@Table(name = "video_repertory", schema = "LushX", catalog = "")
public class VideoRepertory {
    private String videoRepertoryId;
    private String userId;
    private Collection<Video> videosByVideoRepertoryId;

    @Id
    @Column(name = "video_repertory_id", nullable = false, length = 40)
    public String getVideoRepertoryId() {
        return videoRepertoryId;
    }

    public void setVideoRepertoryId(String videoRepertoryId) {
        this.videoRepertoryId = videoRepertoryId;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 40)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoRepertory that = (VideoRepertory) o;

        if (videoRepertoryId != null ? !videoRepertoryId.equals(that.videoRepertoryId) : that.videoRepertoryId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = videoRepertoryId != null ? videoRepertoryId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "videoRepertoryByVideoRepertoryId")
    public Collection<Video> getVideosByVideoRepertoryId() {
        return videosByVideoRepertoryId;
    }

    public void setVideosByVideoRepertoryId(Collection<Video> videosByVideoRepertoryId) {
        this.videosByVideoRepertoryId = videosByVideoRepertoryId;
    }
}
