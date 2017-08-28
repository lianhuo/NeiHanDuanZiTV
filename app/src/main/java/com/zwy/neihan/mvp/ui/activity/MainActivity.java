package com.zwy.neihan.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.boxing.impl.view.HackyViewPager;
import com.jess.arms.widget.dialog.loading.OnCancelListener;
import com.jess.arms.widget.tablayout.listener.CustomTabEntity;
import com.zwy.neihan.R;
import com.zwy.neihan.app.utils.DBUtils;
import com.zwy.neihan.dbtabs.User;
import com.zwy.neihan.di.component.DaggerMainComponent;
import com.zwy.neihan.di.module.MainModule;
import com.zwy.neihan.mvp.contract.MainContract;
import com.zwy.neihan.mvp.presenter.MainPresenter;
import com.zwy.neihan.mvp.ui.adapter.MyPagerAdapter;
import com.zwy.neihan.mvp.ui.widget.MyTabHost;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间:2017-8-26 17:02:43
 * 创建人:Alan
 * 文件描述：xxxxx页面
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, OnCancelListener {


    @BindView(R.id.vp)
    HackyViewPager mVp;
    @BindView(R.id.tablayout)
    MyTabHost mTablayout;

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
        mPresenter.initData(mTablayout, mVp);
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

    /**
     * 设置适配器
     *
     * @param adapter
     */
    @Override
    public void setAdapter(MyPagerAdapter adapter) {
        mVp.setAdapter(adapter);
    }

    /**
     * 设置tab页数据源
     *
     * @param mTabEntities
     */
    @Override
    public void setTabData(ArrayList<CustomTabEntity> mTabEntities) {
        mTablayout.setTabData(mTabEntities);
    }

    /**
     * 获取适配器管理器
     *
     * @return
     */
    @Override
    public FragmentManager getManager() {
        return getSupportFragmentManager();
    }


    private long exitTime = 0;


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    /**
     * 退出程序
     */
    private void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showMessage("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            ArmsUtils.exitApp();
        }
    }

    public void setViewPagerLocked(boolean b) {
        mVp.setLocked(b);
    }
}
