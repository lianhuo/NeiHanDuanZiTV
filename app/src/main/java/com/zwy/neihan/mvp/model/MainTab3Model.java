package com.zwy.neihan.mvp.model;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.zwy.neihan.mvp.contract.MainTab3Contract;
import com.zwy.neihan.mvp.ui.fragment.FollowFragment;
import com.zwy.neihan.mvp.ui.fragment.TopicFragment;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class MainTab3Model extends BaseModel implements MainTab3Contract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MainTab3Model(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public String[] getTitle() {
        return new String[]{"话题","关注"};
    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> list  = new ArrayList<>();
        list.add(TopicFragment.newInstance());
        list.add(FollowFragment.newInstance());
        return list;
    }
}