package cn.mailu.LushX.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PinYin4jUtilsTest {




    @Test
    public void stringToPinyin() throws Exception {

        System.out.println(PinYin4jUtils.charToPinyin('传',true,","));


    }

}