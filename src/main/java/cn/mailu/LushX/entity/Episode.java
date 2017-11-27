package cn.mailu.LushX.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 2:32 2017-11-27
 * @Modified By:
 */
@Entity
public class Episode {
    private String episodeId;
    private String videoId;
    private Integer index;
    private String value;

    @Id
    @Column(name = "episode_id", nullable = false, length = 40)
    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    @Basic
    @Column(name = "video_id", nullable = false, length = 20)
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "index", nullable = true)
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 50)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Episode episode = (Episode) o;

        if (episodeId != null ? !episodeId.equals(episode.episodeId) : episode.episodeId != null) return false;
        if (videoId != null ? !videoId.equals(episode.videoId) : episode.videoId != null) return false;
        if (index != null ? !index.equals(episode.index) : episode.index != null) return false;
        if (value != null ? !value.equals(episode.value) : episode.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = episodeId != null ? episodeId.hashCode() : 0;
        result = 31 * result + (videoId != null ? videoId.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
