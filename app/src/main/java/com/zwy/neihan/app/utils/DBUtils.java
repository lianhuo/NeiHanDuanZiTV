package com.zwy.neihan.app.utils;

import android.content.Context;

import com.jess.arms.base.App;
import com.zwy.neihan.NeiHanConfig;
import com.zwy.neihan.app.dbtabs.User;
import com.zwy.neihan.app.greendao.DaoSession;


/**
 * ================================================================
 * 创建时间：2017/8/26 下午8:06
 * 创建人：Alan
 * 文件描述：数据库操作工具类
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public final class DBUtils {
    private static DBUtils mDBUtils;
    private static DaoSession mDaoSession;

    public static DBUtils getInstance(Context context) {
        mDaoSession = ((DaoSession) ((App) context).getAppComponent().extras().get(NeiHanConfig.DBNAME));
        return mDBUtils == null ? new DBUtils() : mDBUtils;
    }

    /*判断用户是否为第一次进入app  我们在主页模拟了用户的登录成功，在user表中写入了数据 我们去读取user表 如果有>0条数据 表示已进入过app了*/

    /**
     * 当前用户为第一次登录时返回true
     *
     * @return
     */
    public boolean isFirstEnterApp() {
        try {
            if (mDaoSession.getUserDao().loadAll() != null && mDaoSession.getUserDao().loadAll().size() > 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return true;
    }


    public void insertUser(User user) {
        mDaoSession.getUserDao().insert(user);
    }
}
