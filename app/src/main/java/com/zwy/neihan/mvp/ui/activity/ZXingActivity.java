package com.zwy.neihan.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.zxingqrcode.core.QRCodeView;
import com.jess.arms.widget.zxingqrcode.zxing.ZXingView;
import com.zwy.neihan.R;
import com.zwy.neihan.di.component.DaggerZXingComponent;
import com.zwy.neihan.di.module.ZXingModule;
import com.zwy.neihan.mvp.contract.ZXingContract;
import com.zwy.neihan.mvp.presenter.ZXingPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ZXingActivity extends BaseActivity<ZXingPresenter> implements ZXingContract.View , QRCodeView.Delegate {


    @BindView(R.id.zxingview)
    ZXingView mZxingview;
    @BindView(R.id.bt_open)
    Button mBtOpen;
    @BindView(R.id.bt_close)
    Button mBtClose;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerZXingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .zXingModule(new ZXingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_zxing; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mZxingview.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZxingview.startCamera();//开启相机
        mZxingview.showScanRect();//显示扫描框
        mZxingview.startSpot();//开始扫描
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @OnClick({R.id.bt_open, R.id.bt_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_open:
                mZxingview.openFlashlight();
                break;
            case R.id.bt_close:
                mZxingview.closeFlashlight();
                break;
        }
    }

    /**
     * 处理扫描结果
     *
     * @param result
     */
    @Override
    public void onScanQRCodeSuccess(String result) {
        mPresenter.sendResult(result);
    }

    /**
     * 处理打开相机出错
     */
    @Override
    public void onScanQRCodeOpenCameraError() {
        ArmsUtils.snackbarText("打开相机出错，请重新尝试");
    }
}
