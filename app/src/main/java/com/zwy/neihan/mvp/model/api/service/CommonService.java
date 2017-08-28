package com.zwy.neihan.mvp.model.api.service;

import com.zwy.neihan.mvp.model.entity.BaseJson;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;
import com.zwy.neihan.mvp.model.entity.NeiHanContentBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 存放通用的一些API
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonService {
    //https://lf.snssdk.com/neihan/service/personal_tabs/?essence=1&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=joke_essay&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512

    /**
     * 获取首页tabs
     *
     * @return
     */
    @Headers({"Domain-Name: tabs"})
    @GET("/neihan/service/tabs/?essence=1&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=joke_essay&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512")
    Observable<BaseJson<ArrayList<HomeTabBean>>> getHomeTabs(
            @Query("essence") String essence,
            @Query("iid") String iid,
            @Query("device_id") String device_id,
            @Query("ac") String ac,
            @Query("channel") String channel,
            @Query("aid") String aid,
            @Query("app_name") String app_name,
            @Query("version_code") String version_code,
            @Query("ssmix") String ssmix,
            @Query("device_platform") String device_platform,
            @Query("device_type") String device_type,
            @Query("device_brand") String device_brand,
            @Query("os_api") String os_api,
            @Query("os_version") String os_version,
            @Query("uuid") String uuid,
            @Query("openudid") String openudid,
            @Query("manifest_version_code") String manifest_version_code,
            @Query("resolution") String resolution,
            @Query("dpi") String dpi,
            @Query("update_version_code") String update_version_code
    );

    @Headers({"Domain-Name: tabs"})
    @GET("/neihan/service/personal_tabs/?essence=1&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=joke_essay&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512")
    Observable<BaseJson<ArrayList<HomeTabBean>>> getTab2Tabs();


    /**
     * 获取首页各个小Tab下的数据
     *
     * @param content_type 标签编号  由请求tab的接口返回  字段:list_id
     * @param city         当前城市
     * @param longitude    经纬度
     * @param latitude
     * @param am_loc_time  当前时间毫秒
     * @param count        请求的条数
     * @param min_time     上次刷新的时间
     * @return
     */
    @Headers({"Domain-Name: tabsData"})
    @GET("/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&video_cdn_first=1&fetch_activity=1&message_cursor=-1&am_longitude=108.933149&am_latitude=34.170875&screen_width=720&double_col_mode=0&local_request_tag=1503884417492&iid=14204866276&device_id=38616036346&ac=wifi&channel=tengxun&aid=7&app_name=alan&version_code=651&version_name=6.5.1&device_platform=android&ssmix=a&device_type=HUAWEI+C8818&device_brand=Huawei&os_api=19&os_version=4.4.4&uuid=A00000599C2C37&openudid=36459bf17f34022b&manifest_version_code=651&resolution=720*1280&dpi=320&update_version_code=6512")
    Observable<BaseJson<NeiHanContentBean>> getMainTab1ObjectData(@Query("content_type") String content_type,
                                                                  @Query("am_city") String city,
                                                                  @Query("longitude") Long longitude,
                                                                  @Query("latitude") Long latitude,
                                                                  @Query("am_loc_time") Long am_loc_time,
                                                                  @Query("count") int count,
                                                                  @Query("min_time") Long min_time);

}
