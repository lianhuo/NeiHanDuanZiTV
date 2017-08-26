package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.zwy.neihan.mvp.contract.ZXingContract;
import com.zwy.neihan.mvp.model.ZXingModel;

import dagger.Module;
import dagger.Provides;


@Module
public class ZXingModule {
    private ZXingContract.View view;

    /**
     * 构建ZXingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ZXingModule(ZXingContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ZXingContract.View provideZXingView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ZXingContract.Model provideZXingModel(ZXingModel model) {
        return model;
    }
}