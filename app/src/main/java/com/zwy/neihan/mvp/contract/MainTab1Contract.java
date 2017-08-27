package com.zwy.neihan.mvp.contract;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;
import com.zwy.neihan.mvp.ui.adapter.PageAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public interface MainTab1Contract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        /**
         * 设置tabs数据
         * @param pageAdapter
         */
       void setAdapter(PageAdapter pageAdapter);

        /**
         * 获取视图管理器
         * @return
         */
        FragmentManager getFMManager();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * 获取首页tabs
         * @return
         */
        Observable<ArrayList<HomeTabBean>> getHomeTabs();
    }
}
