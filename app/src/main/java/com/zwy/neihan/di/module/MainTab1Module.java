package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.MainTab1Contract;
import com.zwy.neihan.mvp.model.MainTab1Model;


@Module
public class MainTab1Module {
    private MainTab1Contract.View view;

    /**
     * 构建MainTab1Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainTab1Module(MainTab1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainTab1Contract.View provideMainTab1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainTab1Contract.Model provideMainTab1Model(MainTab1Model model) {
        return model;
    }
}