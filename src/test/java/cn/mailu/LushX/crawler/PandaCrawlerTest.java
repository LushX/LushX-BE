package cn.mailu.LushX.crawler;

import cn.mailu.LushX.parser.Parser;
import cn.mailu.LushX.parser.site.PandaParser;
import org.junit.Test;

import static org.junit.Assert.*;

public class PandaCrawlerTest {

    PandaCrawler crawler=new PandaCrawler();

    Parser parser=new PandaParser();

    @Test
    public void start() throws Exception {

        parser.parse("http://www.panda.tv/1404844");


    }

}