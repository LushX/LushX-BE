package cn.mailu.LushX.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 4:21 2017-11-04
 * @Modified By:
 */
@Entity
@Table(name = "type", schema = "LushX", catalog = "")
public class Type implements Serializable {
    private String typeId;
    private String icon;
    private String name;

    @Id
    @Column(name = "type_id", nullable = false, length = 1)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "icon", nullable = true, length = 20)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (typeId != null ? !typeId.equals(type.typeId) : type.typeId != null) return false;
        if (icon != null ? !icon.equals(type.icon) : type.icon != null) return false;
        if (name != null ? !name.equals(type.name) : type.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId != null ? typeId.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
