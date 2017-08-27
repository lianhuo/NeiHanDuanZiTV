package com.zwy.neihan.mvp.model.api.service;

import com.zwy.neihan.mvp.model.entity.BaseJson;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;

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
}
