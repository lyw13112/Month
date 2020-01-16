package com.bawei.month.model;

import com.bawei.month.contract.IContract;
import com.bawei.month.util.NetUtil;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 14:11
 * @description
 */
public class MyModel implements IContract.IModel {
    @Override
    public void getNoParam(String url, Class cls, IContract.ModelCallback callback) {
        NetUtil.getInstance().getNoParam(url, cls, new NetUtil.NetCallback() {
            @Override
            public void netSuccess(Object o) {
                callback.modelSuccess(o);
            }

            @Override
            public void netError(String error) {
                callback.modelError(error);
            }
        });
    }

    @Override
    public void postParam(String url, Map<String, String> map, Class cls, IContract.ModelCallback callback) {
        NetUtil.getInstance().postParam(url, map, cls, new NetUtil.NetCallback() {
            @Override
            public void netSuccess(Object o) {
                callback.modelSuccess(o);
            }

            @Override
            public void netError(String error) {
                callback.modelError(error);
            }
        });
    }
}
