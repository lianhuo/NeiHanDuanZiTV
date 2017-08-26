package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.MainTab2Module;

import com.zwy.neihan.mvp.ui.fragment.MainTab2Fragment;

@ActivityScope
@Component(modules = MainTab2Module.class, dependencies = AppComponent.class)
public interface MainTab2Component {
    void inject(MainTab2Fragment fragment);
}