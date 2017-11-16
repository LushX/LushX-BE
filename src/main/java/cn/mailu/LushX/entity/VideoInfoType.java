package cn.mailu.LushX.entity;

import javax.persistence.*;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:32 2017-11-16
 * @Modified By:
 */
@Entity
@Table(name = "video_info_type", schema = "LushX", catalog = "")
@IdClass(VideoInfoTypePK.class)
public class VideoInfoType {
    private String videoInfoId;
    private String typeId;
    private VideoInfo videoInfoByVideoInfoId;
    private Type typeByTypeId;

    @Id
    @Column(name = "video_info_id", nullable = false, length = 40)
    public String getVideoInfoId() {
        return videoInfoId;
    }

    public void setVideoInfoId(String videoInfoId) {
        this.videoInfoId = videoInfoId;
    }

    @Id
    @Column(name = "type_id", nullable = false, length = 2)
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

        VideoInfoType that = (VideoInfoType) o;

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

    @ManyToOne
    @JoinColumn(name = "video_info_id", referencedColumnName = "video_info_id", nullable = false)
    public VideoInfo getVideoInfoByVideoInfoId() {
        return videoInfoByVideoInfoId;
    }

    public void setVideoInfoByVideoInfoId(VideoInfo videoInfoByVideoInfoId) {
        this.videoInfoByVideoInfoId = videoInfoByVideoInfoId;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false)
    public Type getTypeByTypeId() {
        return typeByTypeId;
    }

    public void setTypeByTypeId(Type typeByTypeId) {
        this.typeByTypeId = typeByTypeId;
    }
}
