package com.zwy.neihan.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zwy.neihan.NeiHanConfig;
import com.zwy.neihan.app.GlobalConfiguration;
import com.zwy.neihan.app.utils.RxUtils;
import com.zwy.neihan.mvp.contract.HomeObjectTabContract;
import com.zwy.neihan.mvp.model.entity.BaseJson;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;
import com.zwy.neihan.mvp.model.entity.NeiHanContentBean;
import com.zwy.neihan.mvp.ui.adapter.MainTab1Adapter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import timber.log.Timber;


/**
 * ================================================================
 * 创建时间:2017-8-27 3:41:19
 * 创建人:Alan
 * 文件描述：首页上部1-5个tab的内容容器
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class HomeObjectTabPresenter extends BasePresenter<HomeObjectTabContract.Model, HomeObjectTabContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private MainTab1Adapter mainTab1Adapter;

    @Inject
    public HomeObjectTabPresenter(HomeObjectTabContract.Model model, HomeObjectTabContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }


    public void getData(HomeTabBean homeTabBean, Long lastTime, boolean isUpData, int count) {
        mainTab1Adapter = new MainTab1Adapter(null);
        mainTab1Adapter.openLoadAnimation(GlobalConfiguration.ListAnim);
        mRootView.setAdapter(mainTab1Adapter);
//        mainTab1Adapter.setEmptyView(mRootView.getEmptyView());
        mModel.getMainTab1ObjectData(String.valueOf(homeTabBean.getList_id()), lastTime, count, isUpData)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(NeiHanConfig.NETWORK_RETRY_TIMES, NeiHanConfig.NETWORK_RETRY_DELAYSECOND))
                .doOnSubscribe(disposable ->
                        mRootView.showLoading())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() ->
                        mRootView.hideLoading()
                )
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<NeiHanContentBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseJson<NeiHanContentBean> data) {
                        try {
                            if (data.getData() == null) {
                                Timber.e(data.toString());
                                return;
                            }
                            mRootView.showNewDataToast(data.getData().getTip(), true);
                        } catch (Exception e) {
                            Timber.e("data == null ? " + (data == null));
                            Timber.e("data.getData() == null ? " + (data.getData() == null));
                            Timber.e("data.getData().getTip() == null ? " + (data.getData().getTip() == null));
                            Timber.e("可能是加载缓存出错了," + e.toString());
                            mRootView.showMessage("可能是加载缓存出错了," + e.toString());
                        }
                            /*刷新数据时 重新设置适配器数据*/
                        if (isUpData) {
                            mainTab1Adapter.setNewData(data.getData().getData());
                        } else {
                            for (int i = 0; i < data.getData().getData().size(); i++) {
                                mainTab1Adapter.addData(data.getData().getData().get(i));
                            }
                        }
                        mainTab1Adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
        this.mainTab1Adapter = null;
    }


}
