package cn.mailu.LushX.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 一些公用的方法
 * @Ahtuor: xuzhenya
 * @Description:
 * @Date: Created in 下午 4:17 2017-11-18
 * @Modified By:
 */
public class CommonUtils {

    /**
     *
     * @param pageable  pageable对象
     * @param list  从redis中取出的list
     * @param <T>
     * @return
     */
    public static <T> Page<T> getPage(Pageable pageable, List list){
        int fromIndex=pageable.getPageNumber()*pageable.getPageSize();
        int length=list.size();
        int end=(pageable.getPageNumber()+1)*pageable.getPageSize();
        int endIndex=end>=length?length:end;
        return new PageImpl<T>(list.subList(fromIndex,endIndex),pageable,length);
    }
}
