package com.zwy.neihan.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.tablayout.CommonTabLayout;
import com.jess.arms.widget.tablayout.listener.OnTabSelectListener;
import com.jess.arms.widget.tablayout.widget.MsgView;
import com.zwy.neihan.mvp.contract.MainContract;
import com.zwy.neihan.mvp.ui.activity.SenderActivity;
import com.zwy.neihan.mvp.ui.adapter.MyPagerAdapter;
import com.zwy.neihan.mvp.ui.fragment.MainTab1Fragment;
import com.zwy.neihan.mvp.ui.fragment.MainTab2Fragment;
import com.zwy.neihan.mvp.ui.fragment.MainTab3Fragment;
import com.zwy.neihan.mvp.ui.fragment.MainTab4Fragment;
import com.zwy.neihan.mvp.ui.widget.MyTabHost;

import java.util.ArrayList;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * ================================================================
 * 创建时间:2017-8-26 17:02:43
 * 创建人:Alan
 * 文件描述：xxxxxx控制器
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private ArrayList<Fragment> mFragments;
    private int p = 0;
    private long time_click = 0;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    /**
     * 初始化数据
     */
    public void initData(MyTabHost mTablayout, ViewPager mVp) {
        mFragments = mModel.getFragments();
        mRootView.setAdapter(new MyPagerAdapter(mRootView.getManager(), mFragments, mModel.getTitles()));
        mRootView.setTabData(mModel.getmTabEntities());
        initListener(mTablayout, mVp);
    }

    /**
     * 设置监听
     *
     * @param mTablayout
     * @param mVp
     */
    public void initListener(MyTabHost mTablayout, ViewPager mVp) {
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                // TODO: 2017/8/24 预留功能点 当按钮选中时再次点击会刷新页面 类似淘宝
                if (mFragments == null || position == 2) return;
                if ((System.currentTimeMillis() - time_click) > 1000) {
                    time_click = System.currentTimeMillis();
                    return;
                }
                switch (position) {
                    case 0:
                        if (mFragments.get(0) != null) {
                            ((MainTab1Fragment) mFragments.get(0)).showMessage("刷新首页");
                        }
                        break;

                    case 1:
                        if (mFragments.get(1) != null) {
                            ((MainTab2Fragment) mFragments.get(1)).showMessage("刷新段友秀页面");
                        }
                        break;

                    case 3:
                        if (mFragments.get(3) != null) {
                            ((MainTab3Fragment) mFragments.get(3)).showMessage("刷新发现页面");
                        }
                        break;

                    case 4:
                        if (mFragments.get(4) != null) {
                            ((MainTab4Fragment) mFragments.get(4)).showMessage("刷新我的页面");
                        }
                        break;
                }
                time_click = 0;
            }
        });
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (p == 1 && position == 2) {
                    p = 3;
                } else if (p == 3 && position == 2) {
                    p = 1;
                } else {
                    p = position;
                }
                mTablayout.setCurrentTab(p);
                mVp.setCurrentItem(p);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVp.setCurrentItem(0);
        addTestData(mTablayout);
        mTablayout.setOnCenterClick(new MyTabHost.OnCenterButtonClickListener() {
            @Override
            public void onClick() {
                mRootView.launchActivity(new Intent(mApplication, SenderActivity.class));
            }
        });
    }

    private void addTestData(CommonTabLayout mTablayout) {
//        mTablayout.showMsg(0, 55);
////        mTablayout.setMsgMargin(0,0,0);
//        mTablayout.setMsgMargin(0, -1, 5);

        mTablayout.showDot(1);
        mTablayout.setMsgMargin(1, -1, 5);

//        mTablayout.showMsg(2, 1);
//        mTablayout.setMsgMargin(2, -4, 5);

        //设置未读消息背景
        mTablayout.showMsg(3, 5);
        mTablayout.setMsgMargin(3, -1, 5);

        MsgView rtv_2_3 = mTablayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
        this.mFragments = null;
    }


}
