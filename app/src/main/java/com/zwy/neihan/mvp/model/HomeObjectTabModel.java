package com.zwy.neihan.mvp.model;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.zwy.neihan.mvp.contract.HomeObjectTabContract;
import com.zwy.neihan.mvp.model.api.cache.CommonCache;
import com.zwy.neihan.mvp.model.api.service.CommonService;
import com.zwy.neihan.mvp.model.entity.BaseJson;
import com.zwy.neihan.mvp.model.entity.NeiHanContentBean;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


@ActivityScope
public class HomeObjectTabModel extends BaseModel implements HomeObjectTabContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public HomeObjectTabModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    /**
     * 获取MainTab1下的子tab数据
     *
     * @param content_type 根据type区分获取哪一个tab的
     * @param min_time     上次更新的时间  可为空
     * @return
     */
    @Override
    public Observable<BaseJson<NeiHanContentBean>> getMainTab1ObjectData(@NonNull String content_type, Long min_time,int count,boolean isUpdata) {

        RetrofitUrlManager.getInstance().putDomain("tabsData", "http://iu.snssdk.com");
       return Observable.just(mRepositoryManager.obtainRetrofitService(CommonService.class).getMainTab1ObjectData(content_type,
                "西安",(long)108.9158414235,(long)34.165824685598,new Date().getTime(),count,min_time==0?new Date().getTime():min_time))
                .flatMap(new Function<Observable<BaseJson<NeiHanContentBean>>, ObservableSource<BaseJson<NeiHanContentBean>>>() {
                    @Override
                    public ObservableSource<BaseJson<NeiHanContentBean>> apply(@io.reactivex.annotations.NonNull Observable<BaseJson<NeiHanContentBean>> baseJsonObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class).getMainTab1ObjectDataCache(baseJsonObservable,new DynamicKey(content_type),new EvictDynamicKey(isUpdata));
                    }
                });
    }
}