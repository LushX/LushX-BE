package cn.mailu.LushX.parser;

import cn.mailu.LushX.parser.site.QqParser;
import cn.mailu.LushX.parser.site.YoukuParser;
import org.junit.Test;

/**
 * @Author:Drohe
 * @Description:测试优酷视频解析器
 * @Date:Created in 19:30 2017/11/7
 * @Modified By:
 */
public class YoukuParserTest {

    YoukuParser youkuParser=new YoukuParser();


    @Test
    public void parse() throws Exception {

        youkuParser.parse("http://v.youku.com/v_show/id_XMzExODU5ODU5Mg==.html");

    }

}