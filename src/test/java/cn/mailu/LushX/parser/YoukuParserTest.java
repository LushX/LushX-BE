package cn.mailu.LushX.parser;

import org.junit.Test;

import static org.junit.Assert.*;
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

        youkuParser.parse("v.youku.com/v_show/id_XMzEzOTk4NDcyOA==.html");

    }

}