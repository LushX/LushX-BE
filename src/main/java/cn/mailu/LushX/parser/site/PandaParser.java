package cn.mailu.LushX.parser.site;

import cn.mailu.LushX.constant.ExceptionTypeEnum;
import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.parser.Parser;
import cn.mailu.LushX.util.JsoupUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 熊猫TV
 * Created by Silence on 2017/4/30.
 */
public class PandaParser implements Parser<Video> {
    private static final String PANDA_API = "http://room.api.m.panda.tv/index.php?method=room.shareapi&roomid=";

    @Override
    public Video parse(String url) {
        final Video video = new Video();
        video.setValue(url);
        String roomId = this.getRoomId(url);
        Document document = JsoupUtils.getDocWithPhone(PANDA_API + roomId);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode=null;


        try {
            rootNode = mapper.readValue(document.body().text(),JsonNode.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        JsonNode dataJson = rootNode.findValue("data");

        video.setTitle(dataJson.findValue("roominfo").findValue("name").textValue());
        video.setImage(dataJson.findValue("roominfo").findValue("pictures").findValue("img").textValue().replace("http:",""));
        video.setPlayUrl(dataJson.findValue("videoinfo").findValue("address").textValue());
        return video;
    }

    @Override
    public List<Episode> parseEpisodes(String url) {
        return null;
    }

    /**
     * 从 URL 中获取房间号
     */
    private String getRoomId(String videoUrl){
        Matcher matcher = Pattern.compile("([0-9]{3,})").matcher(videoUrl);
        if (matcher.find()){
            return matcher.group(1);
        }
        throw new LushXException(ExceptionTypeEnum.VID_CANNOT_MATCH);
    }

}
