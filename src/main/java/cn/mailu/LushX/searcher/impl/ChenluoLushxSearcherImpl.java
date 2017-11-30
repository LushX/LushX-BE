package cn.mailu.LushX.searcher.impl;

import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.crawler.ChenluoCrawler;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.searcher.LushxSearcher;
import cn.mailu.LushX.util.JsoupUtils;
import cn.mailu.LushX.util.crawlerHelper.ChenluoCrawlerHelper;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author:Drohe
 * @Description:尘落搜索
 * @Date:Created in 0:09 2017/11/28
 * @Modified By:
 */

public class ChenluoLushxSearcherImpl implements LushxSearcher<Video> {

    private static Logger logger = LoggerFactory.getLogger(ChenluoCrawler.class);

    public final static String baseUrl = "https://www.50s.cc/?c=search&wd=";

    @Override
    public List<Video> parsePage(String keyWord) {

        List<Video> videos=new ArrayList<>();
        Document document = getResultDocument(keyWord);

        if (null == document) {

            return videos;

        }

        videos = ChenluoCrawlerHelper.getCLVideosFromPcDocument(document, VideoTypeEnum.CL_SEARCH.getCode());

        return videos;
    }

    @Override
    public Document getResultDocument(String keyWord) {

        Document document = null;

        try {

            document = JsoupUtils.getDocWithPC(baseUrl + keyWord);

        } catch (LushXException e) {

            logger.error(e.getMessage());

            document = null;
        }
        return document;
    }
}
