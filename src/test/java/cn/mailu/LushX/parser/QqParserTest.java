package cn.mailu.LushX.parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class QqParserTest {

    QqParser qqParser=new QqParser();

    @Test
    public void parse() throws Exception {

        qqParser.parse("https://v.qq.com/x/cover/mx0u39zyyqg9hwy.html");

    }

}