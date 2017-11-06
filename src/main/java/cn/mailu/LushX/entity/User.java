package cn.mailu.LushX.entity;

import javax.persistence.*;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 4:21 2017-11-04
 * @Modified By:
 */
@Entity
@Table(name = "user", schema = "LushX", catalog = "")
public class User {
    private String userId;  //用户Id
    private String username;  //用户名
    private String password;  //密码
    private String loginType;  // 登录类型
    private String headImg;  //用户头像
    private String gender;  // 性别
    private String meta;  //其他信息
    private String md5;  //md5加密
    private String role; //用户角色

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "login_type", nullable = true, length = 1)
    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Basic
    @Column(name = "head_img", nullable = true, length = 30)
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Basic
    @Column(name = "gender", nullable = false, length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "meta", nullable = true, length = 255)
    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    @Basic
    @Column(name = "md5", nullable = false, length = 100)
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (loginType != null ? !loginType.equals(user.loginType) : user.loginType != null) return false;
        if (headImg != null ? !headImg.equals(user.headImg) : user.headImg != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (meta != null ? !meta.equals(user.meta) : user.meta != null) return false;
        if (md5 != null ? !md5.equals(user.md5) : user.md5 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (loginType != null ? loginType.hashCode() : 0);
        result = 31 * result + (headImg != null ? headImg.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        result = 31 * result + (md5 != null ? md5.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 12)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
