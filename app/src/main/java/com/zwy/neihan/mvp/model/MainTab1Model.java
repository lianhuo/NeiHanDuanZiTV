package com.zwy.neihan.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zwy.neihan.mvp.contract.MainTab1Contract;
import com.zwy.neihan.mvp.model.api.service.CommonService;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


@ActivityScope
public class MainTab1Model extends BaseModel implements MainTab1Contract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MainTab1Model(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
     * 获取首页tabs
     *
     * @return
     */
    @Override
    public Observable<ArrayList<HomeTabBean>> getTabs() {
        RetrofitUrlManager.getInstance().putDomain("tabs", "http://lf.snssdk.com");
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getHometabs()
                .map(arrayListBaseJson -> {
                    return arrayListBaseJson.getData();
                });
    }
}