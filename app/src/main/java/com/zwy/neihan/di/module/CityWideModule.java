package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.CityWideContract;
import com.zwy.neihan.mvp.model.CityWideModel;


@Module
public class CityWideModule {
    private CityWideContract.View view;

    /**
     * 构建CityWideModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CityWideModule(CityWideContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CityWideContract.View provideCityWideView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CityWideContract.Model provideCityWideModel(CityWideModel model) {
        return model;
    }
}