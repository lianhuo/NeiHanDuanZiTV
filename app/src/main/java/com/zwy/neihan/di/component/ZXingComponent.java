package com.zwy.neihan.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.zwy.neihan.di.module.ZXingModule;
import com.zwy.neihan.mvp.ui.activity.ZXingActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ZXingModule.class, dependencies = AppComponent.class)
public interface ZXingComponent {
    void inject(ZXingActivity activity);
}