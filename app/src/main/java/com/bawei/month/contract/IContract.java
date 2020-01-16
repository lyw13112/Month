package com.bawei.month.contract;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 11:45
 * @description
 */
public interface IContract {
    interface IModel {
        void getNoParam(String url, Class cls, ModelCallback callback);

        void postParam(String url, Map<String, String> map, Class cls, ModelCallback callback);
    }

    interface ModelCallback<T> {
        void modelSuccess(T t);

        void modelError(String error);
    }

    interface IView<T> {
        void viewSuccess(T t);

        void viewError(String error);
    }

    interface IPresenter {
        void pGetNoParam(String url, Class cls);

        void pPostParam(String url, Map<String, String> map, Class cls);
    }
}
