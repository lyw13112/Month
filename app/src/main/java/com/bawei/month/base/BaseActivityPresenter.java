package com.bawei.month.base;

import com.bawei.month.contract.IContract;

import java.lang.ref.WeakReference;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 11:54
 * @description
 */
public abstract class BaseActivityPresenter<V extends BaseActivity> implements IContract.IPresenter {
    protected WeakReference<V> v;

    protected void attachView(V v) {
        this.v = new WeakReference<>(v);
    }

    protected void detachView() {
        v.clear();
        v = null;
    }

    protected V getView() {
        return v.get();
    }
}
