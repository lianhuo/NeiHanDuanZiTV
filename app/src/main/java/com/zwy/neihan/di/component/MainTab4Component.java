package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.MainTab4Module;

import com.zwy.neihan.mvp.ui.fragment.MainTab4Fragment;

@ActivityScope
@Component(modules = MainTab4Module.class, dependencies = AppComponent.class)
public interface MainTab4Component {
    void inject(MainTab4Fragment fragment);
}