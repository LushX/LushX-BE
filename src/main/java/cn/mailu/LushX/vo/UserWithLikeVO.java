package cn.mailu.LushX.vo;

import java.util.Map;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/26 12:07
 */
public class UserWithLikeVO extends UserVO {
    private Map collection;

    public Map getCollection() {
        return collection;
    }

    public UserWithLikeVO(UserVO userVO,Map collection) {
        super(userVO.getUserId(),userVO.getUsername(),userVO.getHeadImg(),userVO.getGender());
        this.collection = collection;
    }

    public void setCollection(Map collection) {
        this.collection = collection;
    }
}
