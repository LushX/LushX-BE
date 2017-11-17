package cn.mailu.LushX.constant;

public enum VideoTypeEnum {

    YK_TV(1), // 优酷-电视剧
    YK_MOVIE(2), // 优酷-电影
    YK_ZY(3), // 优酷-综艺
    YK_DM(4),//优酷-动漫

    YK_TV_INFO(5), // 优酷-电视剧详情
    YK_MOVIE_INFO(6), // 优酷-电影详情
    YK_ZY_INFO(7), // 优酷-综艺详情
    YK_DM_INFO(8),//优酷-动漫详情

    IQY_TV(11), // 爱奇艺-电视剧
    IQY_MOVIE(12), // 爱奇艺-电影
    IQY_ZY(13), // 爱奇艺-综艺
    IQY_DM(14); // 爱奇艺-动漫

    private int code;

    VideoTypeEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
