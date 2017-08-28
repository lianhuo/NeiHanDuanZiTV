package com.zwy.neihan.mvp.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.CircleImageView;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.ImagePreviewActivity;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zwy.neihan.R;
import com.zwy.neihan.mvp.model.entity.NeiHanContentBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import timber.log.Timber;

/**
 * ================================================================
 * 创建时间：2017/8/28 下午1:51
 * 创建人：Alan
 * 文件描述：1-5 tab 数据填充的适配器
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MainTab1Adapter extends BaseQuickAdapter<NeiHanContentBean.DataBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MainTab1Adapter(@Nullable List<NeiHanContentBean.DataBean> data) {
        super(R.layout.item_maintabscontent_1_5, data);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, NeiHanContentBean.DataBean item) {

        /*返回数据类型为图片 gif 时需要显示的控件 Media_type == ?*/
        ImageView mContentImage = (ImageView) helper.getView(R.id.iv_content);

        /*返回数据类型为文本时需要显示的控件  Media_type == 0*/
        TextView mContentText = (TextView) helper.getView(R.id.tv_title);

        /*返回数据类型为视频时需要显示的控件  Media_type == 3*/
        JCVideoPlayerStandard mContentVideo = (JCVideoPlayerStandard) helper.getView(R.id.videoplay_content);

        /*返回数据类型为图片时需要显示的控件  Media_type == 4*/
        NineGridView mGridView = (NineGridView) helper.getView(R.id.bg_images);



        /*举报当前Item*/
        helper.getView(R.id.iv_delete).setOnClickListener(view -> {
            ArmsUtils.showToast("弹出举报窗");
        });

        /*热门图标*/
        ImageView mHotImageView = (ImageView) helper.getView(R.id.iv_hot);

        /*用户头像*/
        CircleImageView mUserLogoImageView = (CircleImageView) helper.getView(R.id.cv_userlogo);

        TextView mUserName = (TextView) helper.getView(R.id.tv_username);
        //标题不能为空
        mContentText.setText(item.getGroup().getContent());

        mContentText.setOnClickListener(view -> {
            ArmsUtils.showToast("展示详情页面");
        });
        /*神评布局，无神评时需要隐藏*/
        AutoRelativeLayout mHotLayout = (AutoRelativeLayout) helper.getView(R.id.arl_hotconnlayout);

        /*沈萍用户头像*/
        CircleImageView mHotCommUserLogo = (CircleImageView) helper.getView(R.id.cv_comm_userlogo);

        /*沈萍用户名*/
        TextView mHotCommUserName = (TextView) helper.getView(R.id.tv_comm_username);

        /*沈萍点赞按钮*/
        ImageView mHotLike = (ImageView) helper.getView(R.id.iv_like_comm);

        /*沈萍点赞数量*/
        TextView mHotCommLikeCount = (TextView) helper.getView(R.id.iv_like_comm_count);

        /*沈萍分享按钮*/
        ImageView mHotShared = (ImageView) helper.getView(R.id.iv_shared_comm);

        /*沈萍内容*/
        TextView mHotCommText = (TextView) helper.getView(R.id.tv_comm_content);


        /*当前item内容的点赞按钮*/
        ImageView mLikeIcon = (ImageView) helper.getView(R.id.iv_like);

        /*当前item内容的点赞数量*/
        TextView mLikeCount = (TextView) helper.getView(R.id.tv_like_number);

        /*当前item内容的点low按钮*/
        ImageView mBadIcon = (ImageView) helper.getView(R.id.iv_bad);

        /*当前item内容的点low数量*/
        TextView mBadCount = (TextView) helper.getView(R.id.tv_bad_number);

        /*当前item内容的评论按钮*/
        ImageView mCommIcon = (ImageView) helper.getView(R.id.iv_content_comm);

        /*当前item内容的评论数量*/
        TextView mCommCount = (TextView) helper.getView(R.id.tv_content_comm);

        /*当前item内容的分享按钮*/
        ImageView mSharedIcon = (ImageView) helper.getView(R.id.iv_content_shared);

        /*当前item内容的分享数量*/
        TextView mSharedCount = (TextView) helper.getView(R.id.tv_content_shared);


        splitContent2resetView(item, mContentImage, mContentVideo,
                mHotImageView, mUserLogoImageView, mUserName, mHotLayout,
                mHotCommUserLogo, mHotCommUserName, mHotLike, mHotCommLikeCount, mHotShared,
                mHotCommText, mLikeIcon, mLikeCount, mBadIcon, mBadCount,
                mCommIcon, mCommCount, mSharedIcon, mSharedCount, mGridView);

    }


    private void splitContent2resetView(NeiHanContentBean.DataBean dataBean,
                                        ImageView mContentImage, JCVideoPlayerStandard mContentVideo,
                                        ImageView mHotImageView, CircleImageView mUserLogoImageView,
                                        TextView mUserName, AutoRelativeLayout mHotLayout,
                                        CircleImageView mHotCommUserLogo, TextView mHotCommUserName,
                                        ImageView mHotLike, TextView mHotCommLikeCount, ImageView mHotShared, TextView mHotCommText,
                                        ImageView mLikeIcon, TextView mLikeCount, ImageView mBadIcon,
                                        TextView mBadCount, ImageView mCommIcon, TextView mCommCount,
                                        ImageView mSharedIcon, TextView mSharedCount, NineGridView mGridView) {
        NeiHanContentBean.DataBean.GroupBean item = dataBean.getGroup();

        switch (item.getMedia_type()) {
            case 0:
                //text
                mContentImage.setVisibility(View.GONE);
                mContentVideo.setVisibility(View.GONE);
                mGridView.setVisibility(View.GONE);
                break;
            case 1:
                //长图
                mContentImage.setVisibility(View.VISIBLE);
                mContentVideo.setVisibility(View.GONE);
                mGridView.setVisibility(View.GONE);
                NeiHanContentBean.DataBean.GroupBean.LargeCoverBean large_image = item.getLarge_image();
                GlideArms.with(mContext).load(large_image.getUrl_list().get(0).getUrl())
                        .placeholder(R.mipmap.s_).override(large_image.getWidth(), large_image.getHeight())
                        .into(mContentImage);
                /*手动添加点击大图预览*/
                mContentImage.setOnClickListener(view -> {
                    showBigImage(large_image);
                });
                break;
            case 2:
                //gif
                mContentImage.setVisibility(View.VISIBLE);
                mContentVideo.setVisibility(View.GONE);
                mGridView.setVisibility(View.GONE);
                GlideArms.with(mContext).asGif().load(item.getLarge_image().getUrl_list().get(0).getUrl())
                        .placeholder(R.mipmap.tc).override(item.getLarge_image().getWidth(), item.getLarge_image().getHeight())
                        .into(mContentImage);
                mContentImage.setOnClickListener(view -> {
                    showBigImage(item.getLarge_image());
                });
                break;
            case 3:
                //video
                mContentImage.setVisibility(View.GONE);
                mGridView.setVisibility(View.GONE);
                mContentVideo.setVisibility(View.VISIBLE);
                String u = item.getDownload_url().replaceAll("\"", "").trim();
                mContentVideo.setUp(u, JCVideoPlayer.SCREEN_LAYOUT_LIST, item.getPlay_count() + "人观看");
                GlideArms.with(mContext).load(item.getLarge_cover().getUrl_list().get(0).getUrl()).into(mContentVideo.thumbImageView);
                break;
            case 4:
                //图片
                mContentImage.setVisibility(View.GONE);
                mContentVideo.setVisibility(View.GONE);
                mGridView.setVisibility(View.VISIBLE);
                List<ImageInfo> list = new ArrayList<>();
                List<NeiHanContentBean.DataBean.GroupBean.ImagesBean> thumb_image_list = item.getThumb_image_list();
                List<NeiHanContentBean.DataBean.GroupBean.ImagesBean> large_image_list = item.getLarge_image_list();
                if (thumb_image_list != null && thumb_image_list.size() > 0) {
                    for (int i = 0; i < thumb_image_list.size(); i++) {
                        ImageInfo images = new ImageInfo();
                        images.setBigImageUrl(large_image_list.get(i).getUrl());
                        images.setThumbnailUrl(thumb_image_list.get(i).getUrl());
                        images.setImageViewHeight(thumb_image_list.get(i).getHeight());
                        images.setImageViewWidth(thumb_image_list.get(i).getWidth());
                        list.add(images);
                    }
                    mGridView.setAdapter(new NineGridViewClickAdapter(mContext, list));
                }
                break;
            default:
                Timber.d("default type=" + item.getMedia_type() + ":" + item.getText());
        }
        /*默认显示出来再去判断是否显示或者隐藏*/
        mHotImageView.setVisibility(View.VISIBLE);
        switch (item.getStatus()) {
            case 110:
                //热门
                mHotImageView.setImageResource(R.mipmap.sp);
                break;
            default:
                mHotImageView.setVisibility(View.INVISIBLE);
        }

        /*加载用户头像*/
        GlideArms.with(mContext).load(item.getUser().getAvatar_url()).placeholder(R.drawable.defaultimage).into(mUserLogoImageView);
        /*设置用户昵称*/
        mUserName.setText(item.getUser().getName());

        /*显示或者隐藏沈萍布局*/
        List<NeiHanContentBean.DataBean.CommentBean> commList = dataBean.getComments();
        if (commList == null || commList.size() == 0) {
            mHotLayout.setVisibility(View.GONE);
        } else {
            mHotLayout.setVisibility(View.VISIBLE);

            NeiHanContentBean.DataBean.CommentBean commentBean = commList.get(0);

            /*沈萍用户头像*/
            GlideArms.with(mContext).load(commentBean.getAvatar_url()).placeholder(R.drawable.defaultimage).into(mHotCommUserLogo);

            /*沈萍用户名*/
            mHotCommUserName.setText(commentBean.getUser_name());

            /*设置用户对当前沈萍的点赞状态*/
            mHotLike.setImageResource(commentBean.getIs_digg() == 1 ? R.mipmap.r7 : R.mipmap.r6);

            /*沈萍点赞数*/
            mHotCommLikeCount.setText(String.valueOf(commentBean.getDigg_count()));

            mHotLike.setOnClickListener(view -> {
                if (commentBean.getIs_digg() == 1) {
                    ArmsUtils.showToast("您已赞过");
                } else {
                    ArmsUtils.showToast("对沈萍点赞");
                }
            });
            /*是否已分享*/
            mHotShared.setImageResource(commentBean.isIs_pro_user() ? R.drawable.tb : R.drawable.ta);

            /*设置沈萍分享按钮点击事件*/
            mHotShared.setOnClickListener(view -> {
                if (!commentBean.isIs_pro_user()) {
                    ArmsUtils.showToast("分享沈萍");
                } else {
                    ArmsUtils.showToast("您已分享过");
                }

            });

            /*设置沈萍内容*/
            mHotCommText.setText(commentBean.getText());

            mHotCommText.setOnClickListener(view -> {
                ArmsUtils.showToast("跳转所有评论页面");
            });
        }
         /*是否对内容点过赞*/
        mLikeIcon.setImageResource(item.getUser_favorite() == 0 ? R.mipmap.r6 : R.mipmap.r7);

            /*点赞的用户数*/
        mLikeCount.setText(String.valueOf(item.getFavorite_count()));

            /*item内容点赞*/
        mLikeIcon.setOnClickListener(view -> {
            if (item.getUser_favorite() == 1) {
                ArmsUtils.showToast("您已赞过");
            } else if (item.getUser_digg() == 1) {
                ArmsUtils.showToast("您已踩过");
            } else {
                ArmsUtils.showToast("点赞");
            }
        });


            /*当前是否可以差评*/
        mBadIcon.setImageResource(item.getUser_digg() == 0 ? R.mipmap.q9 : R.mipmap.q_);

            /*设置差评数*/
        mBadCount.setText(String.valueOf(item.getDigg_count()));

            /*踩*/
        mBadIcon.setOnClickListener(view -> {
            if (item.getUser_digg() == 1) {
                ArmsUtils.showToast("您已踩过");
            } else if (item.getUser_favorite() == 1) {
                ArmsUtils.showToast("您已赞过");
            } else {
                ArmsUtils.showToast("踩一下");
            }
        });

            /*跳转撰写评论页面*/
        mCommIcon.setOnClickListener(view -> {
            ArmsUtils.showToast("跳转撰写评论页面");
        });

            /*评论数*/
        mCommCount.setText(String.valueOf(item.getComment_count()));


        mSharedIcon.setOnClickListener(view -> {
            if (item.getIs_can_share() == 1) {
                ArmsUtils.showToast("分享:" + item.getShare_url());
            }
        });

            /*设置分享数量*/
        mSharedCount.setText(String.valueOf(item.getShare_count()));

    }

    private void showBigImage(NeiHanContentBean.DataBean.GroupBean.LargeCoverBean large_image) {
        List<ImageInfo> imageInfo = new ArrayList<>();
        ImageInfo imageInfo_1 = new ImageInfo();
        imageInfo_1.setImageViewHeight(large_image.getHeight());
        imageInfo_1.setImageViewWidth(large_image.getWidth());
        imageInfo_1.setThumbnailUrl(large_image.getUrl_list().get(0).getUrl());
        imageInfo_1.setBigImageUrl(large_image.getUrl_list().get(0).getUrl());
        imageInfo.add(imageInfo_1);
        Intent intent = new Intent(mContext, ImagePreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, 0);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(0, 0);
    }

}
