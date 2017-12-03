package cn.mailu.LushX.util.crawlerHelper;

import cn.mailu.LushX.util.JsoupUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChenluoCrawlerHelperTest {

    //ChenluoCrawlerHelper chenluoCrawlerHelper=new ChenluoCrawlerHelper()；
    @Test
    public void getCLVideosFromPcDocument() {

        ChenluoCrawlerHelper.getCLVideosFromPcDocument(JsoupUtils.getDocWithPad("https://www.50s.cc/whole/1.html"),22);
    }
}