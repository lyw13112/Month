package com.bawei.month.presenter;

import com.bawei.month.base.BaseActivityPresenter;
import com.bawei.month.contract.IContract;
import com.bawei.month.model.MyModel;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 14:20
 * @description
 */
public class MyActivityPresenter extends BaseActivityPresenter {
    @Override
    public void pGetNoParam(String url, Class cls) {
        new MyModel().getNoParam(url, cls, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewSuccess(o);
            }

            @Override
            public void modelError(String error) {
                getView().viewError(error);
            }
        });
    }

    @Override
    public void pPostParam(String url, Map<String, String> map, Class cls) {
        new MyModel().postParam(url, map, cls, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewSuccess(o);
            }

            @Override
            public void modelError(String error) {
                getView().viewError(error);
            }
        });
    }
}
