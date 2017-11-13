package cn.mailu.LushX.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 3:40 2017-11-13
 * @Modified By:
 */
@Entity
public class Resource {
    private String hubId;     // 仓库ID
    private String name;      // 名称
    private String image;     // 图片
    private String url;       // 资源地址
    private String md5;       // 仓库Md5
    private Integer recommend;      // 是否推荐
    private String description;     // 描述

    @Id
    @Column(name = "hubId", nullable = false, length = 40)
    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
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
    @Column(name = "image", nullable = true, length = 60)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 80)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "md5", nullable = true, length = 40)
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Basic
    @Column(name = "recommend", nullable = true)
    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 512)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (hubId != null ? !hubId.equals(resource.hubId) : resource.hubId != null) return false;
        if (name != null ? !name.equals(resource.name) : resource.name != null) return false;
        if (image != null ? !image.equals(resource.image) : resource.image != null) return false;
        if (url != null ? !url.equals(resource.url) : resource.url != null) return false;
        if (md5 != null ? !md5.equals(resource.md5) : resource.md5 != null) return false;
        if (recommend != null ? !recommend.equals(resource.recommend) : resource.recommend != null) return false;
        if (description != null ? !description.equals(resource.description) : resource.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hubId != null ? hubId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (md5 != null ? md5.hashCode() : 0);
        result = 31 * result + (recommend != null ? recommend.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
