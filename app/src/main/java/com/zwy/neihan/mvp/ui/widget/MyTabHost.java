package com.zwy.neihan.mvp.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.widget.tablayout.CommonTabLayout;

/**
 * ================================================================
 * 创建时间：2017/8/26 下午11:27
 * 创建人：Alan
 * 文件描述：我能怎么办，我也很想怼她啊。
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MyTabHost extends CommonTabLayout {
    public MyTabHost(Context context) {
        super(context);
    }

    public MyTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void addTab(int position, View tabView) {
        TextView tv_tab_title = (TextView) tabView.findViewById(com.jess.arms.R.id.tv_tab_title);
        if (TextUtils.isEmpty(mTabEntitys.get(position).getTabTitle())){
            tv_tab_title.setVisibility(GONE);
        }else {
            tv_tab_title.setText(mTabEntitys.get(position).getTabTitle());
        }

        ImageView iv_tab_icon = (ImageView) tabView.findViewById(com.jess.arms.R.id.iv_tab_icon);
        iv_tab_icon.setImageResource(mTabEntitys.get(position).getTabUnselectedIcon());

        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (position == 2) {
                    if (onCenterClick != null) onCenterClick.onClick();
                    return;
                }
                if (mCurrentTab != position) {
                    setCurrentTab(position);
                    if (mListener != null) {
                        mListener.onTabSelect(position);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onTabReselect(position);
                    }
                }
            }
        });

        /** 每一个Tab的布局参数 */
        LinearLayout.LayoutParams lp_tab = mTabSpaceEqual ?
                new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f) :
                new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        if (mTabWidth > 0) {
            lp_tab = new LinearLayout.LayoutParams((int) mTabWidth, LayoutParams.MATCH_PARENT);
        }
        mTabsContainer.addView(tabView, position, lp_tab);
    }

    private OnCenterButtonClickListener onCenterClick;

    public void setOnCenterClick(OnCenterButtonClickListener onCenterClick) {
        this.onCenterClick = onCenterClick;
    }

    public static interface OnCenterButtonClickListener {
        void onClick();
    }
}
