package cn.mailu.LushX.service;

import java.util.List;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/10 21:40
 */
public interface RedisService {

    public Object getValueByKey(String key);

    public void saveByKey(String key,Object object);

    public List getListByKey(String key, long start , long end);

}
