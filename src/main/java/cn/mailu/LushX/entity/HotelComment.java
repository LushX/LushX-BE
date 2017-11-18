package cn.mailu.LushX.entity;

import javax.persistence.*;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 9:33 2017-11-16
 * @Modified By:
 */
@Entity
@Table(name = "hotel_comment", schema = "LushX", catalog = "")
public class HotelComment {
    private String commentId;
    private String content;
    private Double avgScore;
    private Integer level;
    private HotelInfo hotelInfoByHotelId;
    private String hotelId;

    @Id
    @Column(name = "comment_id", nullable = false, length = 40)
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "avg_score", nullable = true, precision = 0)
    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelComment that = (HotelComment) o;

        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (avgScore != null ? !avgScore.equals(that.avgScore) : that.avgScore != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (avgScore != null ? avgScore.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id", nullable = false)
    public HotelInfo getHotelInfoByHotelId() {
        return hotelInfoByHotelId;
    }

    public void setHotelInfoByHotelId(HotelInfo hotelInfoByHotelId) {
        this.hotelInfoByHotelId = hotelInfoByHotelId;
    }

    @Basic
    @Column(name = "hotel_id", nullable = false, length = 20)
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
