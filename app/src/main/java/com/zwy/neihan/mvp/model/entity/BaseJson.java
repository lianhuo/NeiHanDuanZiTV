package com.zwy.neihan.mvp.model.entity;


import com.zwy.neihan.mvp.model.api.Api;

import java.io.Serializable;

/**
 * 如果你服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * 替换范型即可重用BaseJson
 * Created by jess on 26/09/2016 15:19
 * Contact with jess.yan.effort@gmail.com
 */

public class BaseJson<T> implements Serializable{
    private T weather;
    private String status;
    private String msg;

    public T getData() {
        return weather;
    }

    public String getCode() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (status.equals(Api.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
