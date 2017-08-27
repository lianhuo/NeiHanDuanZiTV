package com.zwy.neihan.mvp.presenter;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.zwy.neihan.NeiHanConfig;
import com.zwy.neihan.app.utils.RxUtils;
import com.zwy.neihan.mvp.contract.MainTab1Contract;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;
import com.zwy.neihan.mvp.ui.adapter.PageAdapter;
import com.zwy.neihan.mvp.ui.fragment.EssenceFragment;
import com.zwy.neihan.mvp.ui.fragment.FriendsCircleFragment;
import com.zwy.neihan.mvp.ui.fragment.HomeObjectTabFragment;
import com.zwy.neihan.mvp.ui.fragment.SubscribeFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


/**
 * ================================================================
 * 创建时间:2017-8-26 23:40:51
 * 创建人:Alan
 * 文件描述：xxxxxx控制器
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class MainTab1Presenter extends BasePresenter<MainTab1Contract.Model, MainTab1Contract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public MainTab1Presenter(MainTab1Contract.Model model, MainTab1Contract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }


    public void getTabs() {
        mModel.getHomeTabs()
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
                .subscribe(new ErrorHandleSubscriber<ArrayList<HomeTabBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ArrayList<HomeTabBean> homeTabBeen) {
                        setData(homeTabBeen);
                    }
                });
    }

    private void setData(ArrayList<HomeTabBean> homeTabBeen) {
        if (homeTabBeen == null || homeTabBeen.size() == 0) return;

        ArrayList<Fragment> fragments = new ArrayList<>();
        String[] strs = new String[homeTabBeen.size()];

        for (int i = 0; i < homeTabBeen.size(); i++) {
            String tabName = homeTabBeen.get(i).getName();
            if (tabName.equals("直播")) {
                strs[i] = tabName;
                fragments.add(SubscribeFragment.newInstance());
                continue;
            }
            if (tabName.equals("精华")) {
                strs[i] = tabName;
                fragments.add(EssenceFragment.newInstance());//精华
                continue;
            }

            if (tabName.equals("游戏")) {
                strs[i] = tabName;
                fragments.add(FriendsCircleFragment.newInstance());//段友圈
                continue;
            }
            strs[i] = tabName;
            fragments.add(HomeObjectTabFragment.newInstance(homeTabBeen.get(i)));

        }
        mRootView.setAdapter(new PageAdapter(mRootView.getFMManager(), fragments, strs));
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
