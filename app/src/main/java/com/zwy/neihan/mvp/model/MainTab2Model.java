package com.zwy.neihan.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zwy.neihan.mvp.contract.MainTab2Contract;
import com.zwy.neihan.mvp.model.api.service.CommonService;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class MainTab2Model extends BaseModel implements MainTab2Contract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MainTab2Model(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
     * 获取段友秀tabs
     *
     * @return
     */
    @Override
    public Observable<ArrayList<HomeTabBean>> getTab2Tabs() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getTab2Tabs().map(arrayListBaseJson -> {
                    return arrayListBaseJson.getData();
                });
    }
}