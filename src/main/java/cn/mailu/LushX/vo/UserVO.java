package cn.mailu.LushX.vo;

import cn.mailu.LushX.entity.User;
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
    private Map collection; //收藏

    public Map getCollection() {
        return collection;
    }

    public void setCollection(Map collection) {
        this.collection = collection;
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

    public static UserVO toUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setHeadImg(user.getHeadImg());
        userVO.setUsername(user.getUsername());
        return userVO;
    }
}
