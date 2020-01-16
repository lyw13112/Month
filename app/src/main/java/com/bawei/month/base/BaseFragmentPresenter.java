package com.bawei.month.base;

import com.bawei.month.contract.IContract;

import java.lang.ref.WeakReference;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 11:55
 * @description
 */
public abstract class BaseFragmentPresenter<V extends BaseFragment> implements IContract.IPresenter {
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
