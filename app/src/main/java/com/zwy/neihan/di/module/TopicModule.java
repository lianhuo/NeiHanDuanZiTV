package com.zwy.neihan.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zwy.neihan.mvp.contract.TopicContract;
import com.zwy.neihan.mvp.model.TopicModel;


@Module
public class TopicModule {
    private TopicContract.View view;

    /**
     * 构建TopicModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TopicModule(TopicContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TopicContract.View provideTopicView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TopicContract.Model provideTopicModel(TopicModel model) {
        return model;
    }
}