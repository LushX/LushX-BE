package cn.mailu.LushX.vo;

import cn.mailu.LushX.entity.Episode;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/29 11:21
 */
public class EpisodeVO {
    private String episodeId;
    private Integer indexs;
    private String value;

    public Integer getIndexs() {
        return indexs;
    }

    public void setIndexs(Integer indexs) {
        this.indexs = indexs;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }
    public static EpisodeVO toEpisodeVO(Episode e){
        EpisodeVO episodeVO=new EpisodeVO();
        episodeVO.setEpisodeId(e.getEpisodeId());
        episodeVO.setIndexs(e.getIndexs());
        episodeVO.setValue(e.getValue());
        return episodeVO;
    }
}
