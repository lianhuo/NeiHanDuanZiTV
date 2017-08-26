package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.FriendsCircleContract;
import com.zwy.neihan.mvp.model.FriendsCircleModel;


@Module
public class FriendsCircleModule {
    private FriendsCircleContract.View view;

    /**
     * 构建FriendsCircleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FriendsCircleModule(FriendsCircleContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    FriendsCircleContract.View provideFriendsCircleView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    FriendsCircleContract.Model provideFriendsCircleModel(FriendsCircleModel model) {
        return model;
    }
}