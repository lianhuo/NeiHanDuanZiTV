package com.zwy.neihan.dbtabs;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ================================================================
 * 创建时间：2017/8/26 下午8:04
 * 创建人：Alan
 * 文件描述：数据库存储的用户信息表。
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    @Generated(hash = 152095471)
    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
