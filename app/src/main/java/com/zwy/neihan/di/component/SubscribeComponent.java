package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.SubscribeModule;

import com.zwy.neihan.mvp.ui.fragment.SubscribeFragment;

@ActivityScope
@Component(modules = SubscribeModule.class, dependencies = AppComponent.class)
public interface SubscribeComponent {
    void inject(SubscribeFragment fragment);
}