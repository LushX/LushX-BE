package cn.mailu.LushX.vo;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: NULL
 * @Description:UserVO
 * @Date: Create in 2017/11/9 10:31
 */
public class UserVO {

    private String userId;  //用户Id
    private String username;  //用户名
    private String headImg;  //用户头像
    private String gender;  // 性别

    public UserVO(String userId, String username, String headImg, String gender) {
        this.userId = userId;
        this.username = username;
        this.headImg = headImg;
        this.gender = gender;
    }

    public UserVO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
