package cn.mailu.LushX.parser;


import cn.mailu.LushX.entity.Episode;

import java.io.IOException;
import java.util.List;

/**
 * @Author:Drohe
 * @Description:解析器接口
 * @Date:Created in 18:32 2017/11/7
 * @Modified By:
 */
public interface Parser<T> {

    /**
     * @param url
     *          播放地址
     *
     *@return 对象
     *
     *@Date: Created in 18:36 2017/11/7
     *
     */
    T parse(String url);

    /**
     *
     * @param  url
     *
     *@return 集数对象列表
     *
     *@Date: Created in 18:40 2017/11/7
     *
     */
    List<Episode> parseEpisodes(String url);


}
