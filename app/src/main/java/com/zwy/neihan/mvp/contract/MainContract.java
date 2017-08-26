package com.zwy.neihan.mvp.contract;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.jess.arms.widget.tablayout.listener.CustomTabEntity;
import com.zwy.neihan.mvp.ui.adapter.MyPagerAdapter;

import java.util.ArrayList;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        /**
         * 设置适配器
         * @param adapter
         */
        void setAdapter(MyPagerAdapter adapter);

        /**
         * 设置tab页数据源
         * @param mTabEntities
         */
        void setTabData(ArrayList<CustomTabEntity> mTabEntities);

        /**
         * 获取适配器管理器
         * @return
         */
        FragmentManager getManager();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * 获取tab页填充的数据
         * @return
         */
        ArrayList<CustomTabEntity> getmTabEntities();

        /**
         * 获取标题
         * @return
         */
        String[] getTitles();

        /**
         * 获取单页对应的f
         * @return
         */
        ArrayList<Fragment> getFragments();
    }
}
