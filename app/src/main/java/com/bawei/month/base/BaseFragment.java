package com.bawei.month.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei.month.contract.IContract;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 11:54
 * @description
 */
public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment implements IContract.IView {
    protected P p;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(initLayout(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        p = initPresenter();
        if (p != null) p.attachView(this);
        initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (p != null) {
            p.detachView();
            p = null;
        }
    }

    protected abstract void initView(View view);

    protected abstract P initPresenter();

    protected abstract int initLayout();

}
