package cn.mailu.LushX.parser.site;

import cn.mailu.LushX.crawler.YoukuCrawler;
import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.parser.Parser;
import cn.mailu.LushX.util.UrlUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:Drohe
 * @Description:优酷视频解析器
 * @Date:Created in 18:43 2017/11/7
 * @Modified By:
 */
public class YoukuParser implements Parser<Video> {

    private static Logger logger= LoggerFactory.getLogger(YoukuCrawler.class);


    
    /**
     *@Author:Drohe
     * 
     *@params: url
     * 
     *@return: video
     *
     * @Date:Created in 18:44 2017/11/7
     * 
     */
    @Override
    public Video parse(String url) throws IOException {
        final Video video = new Video();
        video.setValue(url);
        String vid = matchVid(url);
        String api = createPlayRequestApi(vid);
        String result = UrlUtils.getResponse(api);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode=mapper.readValue(result,JsonNode.class);
        JsonNode videoNode=rootNode.path("data").path("video");
        String title = videoNode.findValues("title").toString();
        video.setTitle(title);
        String image = videoNode.findValues("logo").toString();
        video.setImage(image);
        String playUrl = getPlayUrl(rootNode);
        video.setPlayUrl(playUrl);

        return video;
    }

    @Override
    public List<Episode> parseEpisodes(String url) {
        return null;
    }
    /**
     * 从 URL 中匹配 VID
     */
    private String matchVid(String videoUrl) {
        Matcher matcher = Pattern.compile("id_(.*?)\\.html").matcher(videoUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new LushXException("找不到VID");
    }

    /**
     * 获取最清晰的视频线路
     */
    private String getPlayUrl(JsonNode rootNode) {
        List<JsonNode> playList= rootNode.findValue("data").findValues("stream");
        JsonNode bestStream = playList.get(playList.size() - 1);
        return bestStream.findValue("cdn_url").textValue();
    }


    /**
     * 构建视频集数信息的 API
     */
    private String createEpisodeRequestApi(String vid, String showId, String cateId) {
        return "http://api.m.youku.com/api/showlist/getshowlist?vid=" + vid + "&showid=" + showId + "&cateid=" + cateId + "&pagesize=98&page=0";
    }

    /**
     * 构建视频播放信息的 API
     */
    private String createPlayRequestApi(String vid) {
        Date now = new Date();
        String client_ts = String.valueOf(now.getTime() / 1000);
        return "http://ups.youku.com/ups/get.json?vid=" + vid + "&ccode=0590&client_ip=0.0.0.0&utid=ajEdEgkDCSQCAXBBq2KFutND&r=TJXNtWdcb6ky/owezfVSubVck3Aq6AsioO5j8WcrPPc%3D&client_ts=" + client_ts;
    }




}
