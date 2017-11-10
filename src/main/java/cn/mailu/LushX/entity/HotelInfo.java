package cn.mailu.LushX.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 上午 11:35 2017-11-07
 * @Modified By:
 */
@Entity
@Table(name = "hotel_info", schema = "LushX", catalog = "")
public class HotelInfo implements Serializable {
    private String hotelId;
    private String name;
    private String position;
    private String phone;
    private String info;
    private String introduction;
    private String sourceCity;
    private String img;
    private String sourceWeb;
    private String facility;
    private Integer comments;

    @Id
    @Column(name = "hotel_id", nullable = false, length = 40)
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 50)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 1024)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "introduction", nullable = true, length = -1)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "source_city", nullable = true, length = 10)
    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 1024)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "source_web", nullable = true, length = 20)
    public String getSourceWeb() {
        return sourceWeb;
    }

    public void setSourceWeb(String sourceWeb) {
        this.sourceWeb = sourceWeb;
    }

    @Basic
    @Column(name = "facility", nullable = true, length = 1024)
    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    @Basic
    @Column(name = "comments", nullable = true)
    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelInfo hotelInfo = (HotelInfo) o;

        if (hotelId != null ? !hotelId.equals(hotelInfo.hotelId) : hotelInfo.hotelId != null) return false;
        if (name != null ? !name.equals(hotelInfo.name) : hotelInfo.name != null) return false;
        if (position != null ? !position.equals(hotelInfo.position) : hotelInfo.position != null) return false;
        if (phone != null ? !phone.equals(hotelInfo.phone) : hotelInfo.phone != null) return false;
        if (info != null ? !info.equals(hotelInfo.info) : hotelInfo.info != null) return false;
        if (introduction != null ? !introduction.equals(hotelInfo.introduction) : hotelInfo.introduction != null)
            return false;
        if (sourceCity != null ? !sourceCity.equals(hotelInfo.sourceCity) : hotelInfo.sourceCity != null) return false;
        if (img != null ? !img.equals(hotelInfo.img) : hotelInfo.img != null) return false;
        if (sourceWeb != null ? !sourceWeb.equals(hotelInfo.sourceWeb) : hotelInfo.sourceWeb != null) return false;
        if (facility != null ? !facility.equals(hotelInfo.facility) : hotelInfo.facility != null) return false;
        if (comments != null ? !comments.equals(hotelInfo.comments) : hotelInfo.comments != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelId != null ? hotelId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (sourceCity != null ? sourceCity.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (sourceWeb != null ? sourceWeb.hashCode() : 0);
        result = 31 * result + (facility != null ? facility.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
