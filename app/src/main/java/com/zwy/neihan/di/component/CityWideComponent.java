package com.zwy.neihan.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zwy.neihan.di.module.CityWideModule;

import com.zwy.neihan.mvp.ui.fragment.CityWideFragment;

@ActivityScope
@Component(modules = CityWideModule.class, dependencies = AppComponent.class)
public interface CityWideComponent {
    void inject(CityWideFragment fragment);
}