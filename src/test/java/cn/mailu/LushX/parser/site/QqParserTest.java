package cn.mailu.LushX.parser.site;

import org.junit.Test;

public class QqParserTest {

    QqParser qqParser=new QqParser();

    @Test
    public void parse() throws Exception {

        qqParser.parse("https://v.qq.com/x/page/a05328p5lva.html");

    }

}