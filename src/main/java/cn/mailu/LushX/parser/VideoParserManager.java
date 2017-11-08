package cn.mailu.LushX.parser;

import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.parser.site.QqParser;
import cn.mailu.LushX.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class VideoParserManager implements ParserManager {

    private static Logger logger= LoggerFactory.getLogger(VideoParserManager.class);

    private Map<String, Parser> parserMap;

    /**
     * 初始化所有解析器
     */
    public VideoParserManager() {
        parserMap = new HashMap<>();
        //parserMap.put("le.com", new Letv());
        //parserMap.put("panda.tv", new Panda());
        // parserMap.put("weixin.qq.com", new Weixin());
        //parserMap.put("jianshu.com", new Jianshu());
        //parserMap.put("youku.com", new Youku());
        //parserMap.put("iqiyi.com", new Iqiyi());
        parserMap.put("v.qq.com", new QqParser());
        //parserMap.put("v.youku.com", new Youku());
    }

    /**
     * 解析资源
     * todo 未进行解析异常处理
     */
    public Object parse(String url)throws IOException {
        String key = UrlUtils.getTopDomain(url);
        logger.info("Parser key: " + key);
        Parser parser = this.getParser(key);
        if (parser == null) {
            throw new RuntimeException();
        }
        return parser.parse(url);
    }

    /**
     * 获取解析器
     *
     * @param key 一般为 url 中的顶级域名
     */
    public Parser getParser(String key) {
        return parserMap.get(key);
    }

}


