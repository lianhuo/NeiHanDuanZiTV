package com.zwy.neihan.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.dialog.loading.OnCancelListener;
import com.zwy.neihan.R;
import com.zwy.neihan.di.component.DaggerCityWideComponent;
import com.zwy.neihan.di.module.CityWideModule;
import com.zwy.neihan.mvp.contract.CityWideContract;
import com.zwy.neihan.mvp.model.entity.HomeTabBean;
import com.zwy.neihan.mvp.presenter.CityWidePresenter;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间:2017-8-27 3:52:22
 * 创建人:Alan
 * 文件描述：段友秀两个tab公用的 fragment            公用fmt   1 *  2
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@SuppressLint("ValidFragment")
public class CityWideFragment extends BaseFragment<CityWidePresenter> implements CityWideContract.View, OnCancelListener {

    @BindView(R.id.tv)
    TextView mTv;
    private HomeTabBean mTabBean;

    public static CityWideFragment newInstance(HomeTabBean homeTabBean) {
        CityWideFragment fragment = new CityWideFragment(homeTabBean);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    private CityWideFragment(HomeTabBean tabBean) {
        mTabBean = tabBean;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerCityWideComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .cityWideModule(new CityWideModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_wide, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTv.setText(mTabBean.getName());
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }

    /**
     * 当进度条被取消时调用
     */
    @Override
    public void onCancel() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabBean=null;
    }
}
