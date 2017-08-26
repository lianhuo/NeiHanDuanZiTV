package com.zwy.neihan.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.dialog.loading.OnShowLoadingListener;
import com.zwy.neihan.R;
import com.zwy.neihan.app.utils.DBUtils;
import com.zwy.neihan.dbtabs.User;
import com.zwy.neihan.di.component.DaggerMainComponent;
import com.zwy.neihan.di.module.MainModule;
import com.zwy.neihan.mvp.contract.MainContract;
import com.zwy.neihan.mvp.presenter.MainPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间:2017-8-26 17:02:43
 * 创建人:Alan
 * 文件描述：xxxxx页面
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, OnShowLoadingListener {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        DBUtils.getInstance(getApplication()).insertUser(new User("我是一个测试的用户"));
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

}
