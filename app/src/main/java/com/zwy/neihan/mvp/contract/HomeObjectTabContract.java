package com.zwy.neihan.mvp.contract;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zwy.neihan.mvp.model.entity.BaseJson;
import com.zwy.neihan.mvp.model.entity.NeiHanContentBean;
import com.zwy.neihan.mvp.ui.adapter.MainTab1Adapter;

import io.reactivex.Observable;


public interface HomeObjectTabContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setAdapter(MainTab1Adapter mainTab1Adapter);

        /**
         * 刷新后的红条通知
         *
         * @param msg
         * @param isPlaySound 是否播放声音
         */
        void showNewDataToast(String msg, boolean isPlaySound);


        @LayoutRes int  getEmptyView();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * 获取MainTab1下的子tab数据
         *
         * @param content_type 根据type区分获取哪一个tab的
         * @param min_time     上次更新的时间  可为空
         * @param count        请求的条数
         * @param isUpdata     是否更新
         * @return
         */
        Observable<BaseJson<NeiHanContentBean>> getMainTab1ObjectData(@NonNull String content_type, Long min_time, int count, boolean isUpdata);
    }
}
