package com.zwy.neihan.mvp.ui.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.zwy.neihan.R;

/**
 * ================================================================
 * 创建时间：2017/8/28 下午12:35
 * 创建人：Alan
 * 文件描述：我能怎么办，我也很想怼她啊。
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class TipsView extends Toast {
    private Context mContext;
    private CharSequence text;
    private boolean isSound;
    private LayoutInflater inflate;
    private DisplayMetrics mDisplayMetrics;
    private View mToastView;
    private TextView mTextView;
    private MediaPlayer mPlayer;
    private static TipsView mTipsView;

    private TipsView(Context context, CharSequence text, boolean isSound) {
        super(context);
        this.mContext = context;
        this.text = text;
        this.isSound = isSound;
    }

    public static TipsView init(Context context, CharSequence text, boolean isSound) {
        return mTipsView == null ? new TipsView(context, text, isSound) : mTipsView;
    }

    public TipsView showNewDataToast() {

        inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mDisplayMetrics = DeviceUtils.getDisplayMetrics(mContext);

        mToastView = inflate.inflate(R.layout.tipsview, null);
        mToastView.setMinimumWidth(mDisplayMetrics.widthPixels);//设置控件最小宽度为手机屏幕宽度

        mTextView = (TextView) mToastView.findViewById(R.id.new_data_toast_message);
        mTextView.setText(text);
        mTextView.setTextColor(ArmsUtils.getColor(mContext, R.color.white));
        setView(mToastView);
        setDuration(Toast.LENGTH_SHORT);
        setGravity(Gravity.TOP, 0, (int) (mDisplayMetrics.density * 55));

        if (isSound) {
            mPlayer = MediaPlayer.create(mContext, R.raw.newdatatoast);
            mPlayer.setOnCompletionListener(mediaPlayer -> mediaPlayer.release());
        }
        show();
        return this;
    }

    public void destroy() {
        this.mContext = null;
        this.text = null;
        this.inflate = null;
        this.mDisplayMetrics = null;
        this.mToastView = null;
        this.mPlayer = null;
        this.mTipsView = null;
    }
}
