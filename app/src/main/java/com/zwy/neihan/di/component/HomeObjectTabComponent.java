package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.HomeObjectTabModule;

import com.zwy.neihan.mvp.ui.fragment.HomeObjectTabFragment;

@ActivityScope
@Component(modules = HomeObjectTabModule.class, dependencies = AppComponent.class)
public interface HomeObjectTabComponent {
    void inject(HomeObjectTabFragment fragment);
}