package cn.mailu.LushX.searcher.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChenluoLushxSearcherImplTest {
    ChenluoLushxSearcherImpl chenluoLushxSearcher=new ChenluoLushxSearcherImpl();

    @Test
    public void parsePage() throws Exception {
        chenluoLushxSearcher.parsePage("");
    }

}