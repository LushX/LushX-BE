package cn.mailu.LushX.service;

import java.util.List;

/**
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 8:45 2017-11-05
 * @Modified By:
 */
public interface BaseService<T> {

    /**
     * 新增记录
     * @return
     */
    void insert(T t);

    /**
     * 根据Id删除
     * @return
     */
    void updateById(T t);

    /**
     * 根据Id删除
     * @return
     */
    void deleteById(String Id);

    /**
     * 根据Id查询
     * @return
     */
    T selectById(String Id);

    /**
     * 查询所有记录
     * @return
     */
    List<T> selectAll();

}
