package cn.mailu.LushX.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/10 21:40
 */
public interface RedisService {

    Object getValueByKey(String key);

    void saveByKey(String key, Object object);

    List getListByKey(String key, long start, long end);

    List getListByKey(String key);

    void saveAllForListByKey(String key, List list);

    void saveObjectForListByKey(String key, Object object);

    void setExpireByKey(String key,long timeout,TimeUnit timeUnit);

}
