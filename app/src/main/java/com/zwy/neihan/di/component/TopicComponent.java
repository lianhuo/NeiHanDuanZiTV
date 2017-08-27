package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.TopicModule;

import com.zwy.neihan.mvp.ui.fragment.TopicFragment;

@ActivityScope
@Component(modules = TopicModule.class, dependencies = AppComponent.class)
public interface TopicComponent {
    void inject(TopicFragment fragment);
}