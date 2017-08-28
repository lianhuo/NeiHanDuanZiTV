package com.zwy.neihan.mvp.model.api.cache;

import com.zwy.neihan.mvp.model.entity.BaseJson;
import com.zwy.neihan.mvp.model.entity.NeiHanContentBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;

/**
 * Created by jess on 8/30/16 13:53
 * Contact with jess.yan.effort@gmail.com
 */
public interface CommonCache {

    /**
     * 获取首页各个小Tab下的缓存数据
     *
     * @param baseJsonObservable 接口
     * @param dynamicKey            缓存key
     * @param isUpdata              是否是更新  为true时不使用缓存   会删除缓存
     * @return
     */
    @LifeCache(duration = 10, timeUnit = TimeUnit.SECONDS)
    Observable<BaseJson<NeiHanContentBean>> getMainTab1ObjectDataCache(Observable<BaseJson<NeiHanContentBean>> baseJsonObservable, DynamicKey dynamicKey, EvictDynamicKey isUpdata);
}
