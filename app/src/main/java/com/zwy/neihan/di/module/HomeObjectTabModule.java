package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.HomeObjectTabContract;
import com.zwy.neihan.mvp.model.HomeObjectTabModel;


@Module
public class HomeObjectTabModule {
    private HomeObjectTabContract.View view;

    /**
     * 构建HomeObjectTabModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HomeObjectTabModule(HomeObjectTabContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HomeObjectTabContract.View provideHomeObjectTabView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HomeObjectTabContract.Model provideHomeObjectTabModel(HomeObjectTabModel model) {
        return model;
    }
}