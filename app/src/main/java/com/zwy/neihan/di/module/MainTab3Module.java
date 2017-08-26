package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.MainTab3Contract;
import com.zwy.neihan.mvp.model.MainTab3Model;


@Module
public class MainTab3Module {
    private MainTab3Contract.View view;

    /**
     * 构建MainTab3Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainTab3Module(MainTab3Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainTab3Contract.View provideMainTab3View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainTab3Contract.Model provideMainTab3Model(MainTab3Model model) {
        return model;
    }
}