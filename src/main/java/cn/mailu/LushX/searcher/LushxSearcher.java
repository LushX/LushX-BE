package cn.mailu.LushX.searcher;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @Author:Drohe
 * @Description:搜索器接口
 * @Date:Created in 0:08 2017/11/28
 * @Modified By:
 */

public interface LushxSearcher<T> {


    Document document = null;

    /**
     * 解析搜索页
     *
     * @Date: Created in 0:10 2017/11/28
     */
    List<T> parsePage(String keyWord);

    /**
     * @param keyWord 获取搜索结果页
     * @Date: Created in 0:10 2017/11/28
     */
    Document getResultDocument(String keyWord);


}
