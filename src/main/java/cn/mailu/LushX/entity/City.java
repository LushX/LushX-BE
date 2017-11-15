package cn.mailu.LushX.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 上午 11:24 2017-11-15
 * @Modified By:
 */
@Entity
public class City {
    private int id;
    private String name;
    private String pinyin;
    private Integer isOpen;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pinyin", nullable = true, length = 25)
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Basic
    @Column(name = "isOpen", nullable = true)
    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (pinyin != null ? !pinyin.equals(city.pinyin) : city.pinyin != null) return false;
        if (isOpen != null ? !isOpen.equals(city.isOpen) : city.isOpen != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pinyin != null ? pinyin.hashCode() : 0);
        result = 31 * result + (isOpen != null ? isOpen.hashCode() : 0);
        return result;
    }
}
