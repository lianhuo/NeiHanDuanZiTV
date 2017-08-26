package com.zwy.neihan.mvp.presenter;

import android.app.Application;
import android.widget.ImageView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.jess.arms.widget.guide.BGABanner;
import com.zwy.neihan.R;
import com.zwy.neihan.mvp.contract.GuideContract;


/**
 * ================================================================
 * 创建时间:2017-8-26 18:59:03
 * 创建人:Alan
 * 文件描述：欢迎页面
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class GuidePresenter extends BasePresenter<GuideContract.Model, GuideContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public GuidePresenter(GuideContract.Model model, GuideContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }


    public void initData(){
        mRootView.setData(mModel.getGuideImages());
        mRootView.setEnterBtId(R.id.bt_enter);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}
