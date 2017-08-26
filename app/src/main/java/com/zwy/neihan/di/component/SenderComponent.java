package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.SenderModule;

import com.zwy.neihan.mvp.ui.activity.SenderActivity;

@ActivityScope
@Component(modules = SenderModule.class, dependencies = AppComponent.class)
public interface SenderComponent {
    void inject(SenderActivity activity);
}