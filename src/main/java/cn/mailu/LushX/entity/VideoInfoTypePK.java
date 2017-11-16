package cn.mailu.LushX.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:32 2017-11-16
 * @Modified By:
 */
public class VideoInfoTypePK implements Serializable {
    private String videoInfoId;
    private String typeId;

    @Column(name = "video_info_id", nullable = false, length = 40)
    @Id
    public String getVideoInfoId() {
        return videoInfoId;
    }

    public void setVideoInfoId(String videoInfoId) {
        this.videoInfoId = videoInfoId;
    }

    @Column(name = "type_id", nullable = false, length = 2)
    @Id
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoInfoTypePK that = (VideoInfoTypePK) o;

        if (videoInfoId != null ? !videoInfoId.equals(that.videoInfoId) : that.videoInfoId != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = videoInfoId != null ? videoInfoId.hashCode() : 0;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        return result;
    }
}
