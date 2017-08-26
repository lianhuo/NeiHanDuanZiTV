package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.SenderContract;
import com.zwy.neihan.mvp.model.SenderModel;


@Module
public class SenderModule {
    private SenderContract.View view;

    /**
     * 构建SenderModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SenderModule(SenderContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SenderContract.View provideSenderView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SenderContract.Model provideSenderModel(SenderModel model) {
        return model;
    }
}