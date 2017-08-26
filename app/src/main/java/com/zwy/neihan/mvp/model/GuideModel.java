package com.zwy.neihan.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zwy.neihan.R;
import com.zwy.neihan.mvp.contract.GuideContract;

import javax.inject.Inject;


@ActivityScope
public class GuideModel extends BaseModel implements GuideContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public GuideModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

    @Override
    public int[] getGuideImages() {
        return new int[]{R.mipmap.bg_intros1, R.mipmap.bg_intros2, R.mipmap.bg_intros3, R.mipmap.bg_intros4};
    }
}