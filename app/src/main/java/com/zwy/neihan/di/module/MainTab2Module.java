package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.MainTab2Contract;
import com.zwy.neihan.mvp.model.MainTab2Model;


@Module
public class MainTab2Module {
    private MainTab2Contract.View view;

    /**
     * 构建MainTab2Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainTab2Module(MainTab2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainTab2Contract.View provideMainTab2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainTab2Contract.Model provideMainTab2Model(MainTab2Model model) {
        return model;
    }
}