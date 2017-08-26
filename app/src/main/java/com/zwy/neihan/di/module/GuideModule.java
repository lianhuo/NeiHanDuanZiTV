package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.GuideContract;
import com.zwy.neihan.mvp.model.GuideModel;


@Module
public class GuideModule {
    private GuideContract.View view;

    /**
     * 构建GuideModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GuideModule(GuideContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GuideContract.View provideGuideView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GuideContract.Model provideGuideModel(GuideModel model) {
        return model;
    }
}