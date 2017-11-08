package cn.mailu.LushX.parser.site;


import cn.mailu.LushX.crawler.YoukuCrawler;
import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.exception.LushXException;
import cn.mailu.LushX.parser.Parser;
import cn.mailu.LushX.util.JsoupUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.math.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QqParser implements Parser<Video> {

    private static Logger log= LoggerFactory.getLogger(YoukuCrawler.class);
    private final static String VIDEO_API = "http://vv.video.qq.com/getinfo";
    private final static String KEY_API = "http://vv.video.qq.com/getkey";
    private final static String COOKIE = "gv_pvi=8764138496; RK=vQGLRHUKYP; tvfe_boss_uuid=ad92b8f6187d6ab5; tvfe_search_uid=630d832e-c681-42d1-b40a-723eba41e708; luin=o0545544032; lskey=000100003bf74539b22218b1fcb0dbccb7b495b5aa099d6647b0e378199609a9edc8ebf8ca0c42d9bb1809fd; login_remember=qq; eas_sid=A1z5v0A3O2g0B0J2D5Z3L6q438; LW_uid=01V5m0Q3u4y5u3p9J231a948i8; ue_uid=ab7040ed4b966b7d3a5373441f04df91; ue_ts=1503500816; ue_uk=62b40f67948a56967557d9b652e8fb9f; ue_skey=fcad5c2f589f34abb84fb72ed5cd863f; LW_TS=1503500817; LW_PsKey=cb7f1de4c8bd86cdda5bbc44d16c5b3c; LW_pid=ae8a016e9a78dfd394a80346465acb94; mobileUV=1_15e0fc5b713_e36c3; LW_sid=01D560v3X9i2E7H1q8N4u2p0p1; ts_refer=m.v.qq.com/tv.html; pgv_si=s2744110080; ptui_loginuin=228701992; ptisp=cnc; ptcz=dc0405a72e903fd13ee3dedc75b9dd35988e50a3d71c91b062bfd12689ee2ea2; pt2gguin=o0545544032; uin=o0545544032; skey=@snAZhVtqM; uid=100414728; ad_play_index=68; ptag=|new_vs_focus:img_2; main_login=qq; qv_als=VsfJ2nlWWoKpT5cXA11504239914OmtYHA==; encuin=f4e7225172efe340bcfe344f11a106c5|545544032; lw_nick=Anoy|545544032|//thirdqq.qlogo.cn/g?b=sdk&k=cCwJCaXUjR9ySsCVyu8Rfw&s=40&t=1488113923|1; pgv_info=ssid=s7643493700; ts_last=v.qq.com/x/cover/09t5p6rdhrnxiig/k0024bgath5.html; pgv_pvid=1685846680; o_cookie=545544032; ts_uid=5590511376";
    private final static String GUID = "fb74ffcc7b14377db9cb5308e598d6e5";

    @Override
    public Video parse(String url) {
        Video video = new Video();
        video.setValue(url);
        String vid = getVid(url);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode= null;
        try {
            rootNode = mapper.readValue(videoInfo(vid),JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initVideo(video, rootNode);
        int a=1;
        return video;
    }

    @Override
    public List<Episode> parseEpisodes(String url) {
        List<Episode> episodes = new ArrayList<>();
        Document document = JsoupUtils.getDocWithPhone(url);
        Elements elements = document.select("div[data-tpl='episode'] span a");
        for (Element element : elements) {
            Episode episode = new Episode();
            String value = "http://v.qq.com" + element.attr("href");
            String index = element.text();
            episode.setValue(value);
            episode.setIndex(Integer.parseInt(index));
            episodes.add(episode);
        }
        if (episodes.size() < 1) {
            elements = document.select("a.U_color_b");
            for (Element element : elements) {
                Episode episode = new Episode();
                String value = "http://m.v.qq.com" + element.attr("href");
                String index = element.text().replace("会员", "-V");
                episode.setValue(value);
                episode.setIndex(Integer.parseInt(index));
                if (!index.equals("登录")) {
                    episodes.add(episode);
                }
            }
        }
        return episodes;
    }

    /**
     * 解析腾讯视频片段
     */
    public Episode parsePart(String fileName, Integer index) {
        Episode episode = new Episode();
        String[] params = fileName.split("\\.");
        String file = fileName.replace("1.mp4", index + ".mp4");
        String vid = params[0];
        String format = params[1].replace("p", "10");
        String key = videoKey(vid, file, format);
        episode.setIndex(index);
        episode.setValue(playUrl("/", file, key));
        return episode;
    }

    /**
     * 获取 vid
     */
    private String getVid(String url) {
        Document document = JsoupUtils.getDocWithPhone(url);
        Matcher matcher = Pattern.compile("\"vid\":\"(.*?)\"").matcher(document.html());
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new LushXException("Cannot match VID");
    }

    /**
     * 调用腾讯接口，获取视频信息
     */
    private String videoInfo(String vid) {
        try {
            Document document = Jsoup.connect(VIDEO_API).header("Cookie", COOKIE)
                    .data("vids", vid).data("platform", "10901")
                    .data("otype", "json").data("defn", "fhd")
                    .data("defaultfmt", "auto").data("guid", GUID).ignoreContentType(true).get();
            String result = document.text().replace("QZOutputJson=", "");
            return result.substring(0, result.length() - 1);
        } catch (IOException e) {
            log.info("request tencent api error, vid : " + vid);
            throw new LushXException("request tencent api error, vid : " + vid);
        }
    }

    /**
     * 初始化视频信息
     */
    private void initVideo(Video video, JsonNode json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode videoJson = json.findValue("vl").findValues("vi").get(0);
        int random = RandomUtils.nextInt(3);
        String url = videoJson.findValue("ul").findValues("ui").get(random).findValue("url").toString();
        String vkey = videoJson.findValue("fvkey").toString();
        String fn = videoJson.findValue("fn").toString();
        String file = fn.replace("mp4", "1.mp4");
        String title = videoJson.findValue("ti").toString();
        String firstPlayUrl = playUrl(url, file, vkey);
        String size = videoJson.findValue("cl").findValue("fc").toString();
        video.setPlayUrl(firstPlayUrl);
        video.setImage("");
        video.setTitle(title);
        video.setType("qq");
        video.setOther(size);
    }

    /**
     * 片段播放地址
     */
    private String playUrl(String url, String part, String vkey) {
        return url + part + "?sdtfrom=v1010&platform=2&guid=" + GUID + "&vkey=" + vkey;
    }

    /**
     * 获取片段播放的 key
     */
    private String videoKey(String vid, String filename, String format) {
        try {
            Document document = Jsoup.connect(KEY_API).header("Cookie", COOKIE)
                    .data("vid", vid).data("platform", "10901")
                    .data("otype", "json").data("vt", "203")
                    .data("filename", filename).data("sdtfrom", "v1010")
                    .data("format", format).data("guid", GUID).ignoreContentType(true).get();
            String result = document.text().replace("QZOutputJson=", "");
            result = result.substring(0, result.length() - 1);

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result,JsonNode.class).findValue("key").toString();

        } catch (IOException e) {
            log.info("request tencent video part api error, vid : " + vid);
            throw new LushXException("request tencent api error, vid : " + vid);
        }
    }

    /**
     * @Author:Drohe
     * @Description:优酷视频解析器
     * @Date:Created in 18:43 2017/11/7
     * @Modified By:
     */
    public static class YoukuParser implements Parser<Video> {

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
            String result = getResponse(api);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode=mapper.readValue(result,JsonNode.class);
            JsonNode videoNode=rootNode.path("data").path("video");
    //        JSONObject json = JSONObject.parseObject(result);
    //        JSONObject videoInfo = json.getJSONObject("data").getJSONObject("video");
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
            return bestStream.findValue("m3u8_url").textValue();
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
            return "http://ups.youku.com/ups/get.json?vid=" + vid + "&ccode=0509&client_ip=0.0.0.0&utid=ajEdEgkDCSQCAXBBq2KFutND&r=TJXNtWdcb6ky/owezfVSubVck3Aq6AsioO5j8WcrPPc%3D&client_ts=" + client_ts;
        }

        /**
         * 获取 HTTP 请求返回的结果
         */
        private String getResponse(String api) {
            try {
                URL url = new URL(api);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.addRequestProperty("user-agent", JsoupUtils.getUaPad());
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 得到响应信息
                    InputStream is = connection.getInputStream();
                    byte[] bs = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = is.read(bs)) != -1) {
                        String str = new String(bs, 0, len);
                        sb.append(str);
                    }
                    return sb.toString();
                }
                throw new LushXException("HTTP 请求错误");
            } catch (IOException exception) {
                throw new LushXException("youku api request error: " + api);
            }
        }
    }
}
