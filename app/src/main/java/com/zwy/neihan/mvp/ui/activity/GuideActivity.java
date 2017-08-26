package com.zwy.neihan.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.dialog.loading.OnShowLoadingListener;
import com.jess.arms.widget.guide.BGABanner;
import com.zwy.neihan.R;
import com.zwy.neihan.di.component.DaggerGuideComponent;
import com.zwy.neihan.di.module.GuideModule;
import com.zwy.neihan.mvp.contract.GuideContract;
import com.zwy.neihan.mvp.presenter.GuidePresenter;

import butterknife.BindView;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间:2017-8-26 18:59:03
 * 创建人:Alan
 * 文件描述：欢迎页面
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class GuideActivity extends BaseActivity<GuidePresenter> implements GuideContract.View, OnShowLoadingListener {


    @BindView(R.id.banner_guide_content)
    BGABanner mBanner;
    @BindView(R.id.bt_enter)
    Button mBtEnter;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerGuideComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .guideModule(new GuideModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_guide; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.initData();
    }


    @Override
    public void showLoading() {
        ArmsUtils.showLoading("加载中...", true, this);
    }

    @Override
    public void hideLoading() {
        ArmsUtils.dissMissLoading();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.showToast(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    /**
     * 当进度条被取消时调用
     */
    @Override
    public void onCancel() {

    }

    @Override
    public void setData(int[] resIds) {
        mBanner.setData(resIds);
    }

    @Override
    public void setEnterBtId(int enterBtId) {
        //我有苦衷才这么做 - -。
        mBanner.setEnterSkipViewIdAndDelegate(enterBtId, 0, new BGABanner.GuideDelegate() {

            @Override
            public void onClickEnterOrSkip() {
                launchActivity(new Intent(GuideActivity.this, MainActivity.class));
                killMyself();
            }
        });
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3)
                    mBtEnter.setVisibility(View.VISIBLE);
                else
                    mBtEnter.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
