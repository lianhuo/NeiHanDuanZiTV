package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.EssenceModule;

import com.zwy.neihan.mvp.ui.fragment.EssenceFragment;

@ActivityScope
@Component(modules = EssenceModule.class, dependencies = AppComponent.class)
public interface EssenceComponent {
    void inject(EssenceFragment fragment);
}