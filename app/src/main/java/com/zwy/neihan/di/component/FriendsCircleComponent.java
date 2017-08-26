package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.FriendsCircleModule;

import com.zwy.neihan.mvp.ui.fragment.FriendsCircleFragment;

@ActivityScope
@Component(modules = FriendsCircleModule.class, dependencies = AppComponent.class)
public interface FriendsCircleComponent {
    void inject(FriendsCircleFragment fragment);
}