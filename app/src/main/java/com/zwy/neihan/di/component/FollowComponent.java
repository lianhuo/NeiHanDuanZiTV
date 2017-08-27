package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.FollowModule;

import com.zwy.neihan.mvp.ui.fragment.FollowFragment;

@ActivityScope
@Component(modules = FollowModule.class, dependencies = AppComponent.class)
public interface FollowComponent {
    void inject(FollowFragment fragment);
}