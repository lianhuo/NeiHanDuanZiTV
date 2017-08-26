package com.zwy.neihan.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zwy.neihan.mvp.contract.ZXingContract;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;

import static com.zwy.neihan.app.EventBusTags.SCAN_QR_CODE_RESULT;


@ActivityScope
public class ZXingPresenter extends BasePresenter<ZXingContract.Model, ZXingContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ZXingPresenter(ZXingContract.Model model, ZXingContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }
    public void sendResult(String result) {
        EventBus.getDefault().post(result, SCAN_QR_CODE_RESULT);
        Timber.d("二维码扫描成功,result: " + result);
        mRootView.killMyself();
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
