package cn.mailu.LushX.vo;

import cn.mailu.LushX.entity.Episode;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.entity.VideoRepertory;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/27 18:38
 */
public class VideoVO {
    private String videoId;
    private String title;
    private String alias;
    private String image;
    private String playUrl;
    private String type;
    private String value;
    private String director;
    private String actor;
    private String area;
    private String score;
    private Date time;
    private String other;
    private Set<Episode> episodes = new HashSet<Episode>(0);

    public static VideoVO toVideoVO(Video v) {
        VideoVO videoVO=new VideoVO();
        videoVO.setActor(v.getActor());
        videoVO.setAlias(v.getAlias());
        videoVO.setArea(v.getArea());
        videoVO.setDirector(v.getDirector());
        videoVO.setEpisodes(v.getEpisodes());
        videoVO.setImage(v.getImage());
        videoVO.setOther(v.getOther());
        videoVO.setPlayUrl(v.getPlayUrl());
        videoVO.setScore(v.getScore());
        videoVO.setTime(v.getTime());
        videoVO.setTitle(v.getTitle());
        videoVO.setType(v.getType());
        videoVO.setValue(v.getValue());
        videoVO.setVideoId(v.getVideoId());
        return videoVO;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }


}
