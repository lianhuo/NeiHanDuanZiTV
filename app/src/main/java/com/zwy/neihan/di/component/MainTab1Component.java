package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.MainTab1Module;

import com.zwy.neihan.mvp.ui.fragment.MainTab1Fragment;

@ActivityScope
@Component(modules = MainTab1Module.class, dependencies = AppComponent.class)
public interface MainTab1Component {
    void inject(MainTab1Fragment fragment);
}