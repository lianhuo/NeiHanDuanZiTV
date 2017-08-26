package com.zwy.neihan.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zwy.neihan.app.utils.DBUtils;
import com.zwy.neihan.mvp.contract.SplashContract;
import com.zwy.neihan.mvp.ui.activity.GuideActivity;
import com.zwy.neihan.mvp.ui.activity.MainActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;


/**
 * ================================================================
 * 创建时间:2017-8-26 18:26:57
 * 创建人:Alan
 * 文件描述：xxxxxx控制器
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void initDataAndToNextPage() {
        // TODO: 2017/8/26 休眠1000ms后判断是否进入首页 或者展示引导页面
        Schedulers.newThread().createWorker().schedule(() -> {
            Timber.d("一秒后跳转页面");
            final Class[] mClass = {MainActivity.class};
            AndroidSchedulers.mainThread().createWorker().schedule(() -> {
                Timber.d("开始执行");
                if (DBUtils.getInstance(mApplication).isFirstEnterApp())
                    mClass[0] = GuideActivity.class;
                Intent intent = new Intent(mApplication, mClass[0]);
                mRootView.launchActivity(intent);
                mRootView.killMyself();
            });
        }, 1, TimeUnit.SECONDS);
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
