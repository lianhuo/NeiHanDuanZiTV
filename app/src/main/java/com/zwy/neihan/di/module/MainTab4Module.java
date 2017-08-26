package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.MainTab4Contract;
import com.zwy.neihan.mvp.model.MainTab4Model;


@Module
public class MainTab4Module {
    private MainTab4Contract.View view;

    /**
     * 构建MainTab4Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainTab4Module(MainTab4Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainTab4Contract.View provideMainTab4View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainTab4Contract.Model provideMainTab4Model(MainTab4Model model) {
        return model;
    }
}