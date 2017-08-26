package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.MainTab3Module;

import com.zwy.neihan.mvp.ui.fragment.MainTab3Fragment;

@ActivityScope
@Component(modules = MainTab3Module.class, dependencies = AppComponent.class)
public interface MainTab3Component {
    void inject(MainTab3Fragment fragment);
}