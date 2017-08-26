package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.EssenceContract;
import com.zwy.neihan.mvp.model.EssenceModel;


@Module
public class EssenceModule {
    private EssenceContract.View view;

    /**
     * 构建EssenceModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public EssenceModule(EssenceContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    EssenceContract.View provideEssenceView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    EssenceContract.Model provideEssenceModel(EssenceModel model) {
        return model;
    }
}