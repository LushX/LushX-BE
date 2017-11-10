package cn.mailu.LushX.service;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/10 21:40
 */
public interface RedisService {

    public Object getListByKey(String key,Class<?> clazz);

    public void saveByKey(String key,Object object);

}
