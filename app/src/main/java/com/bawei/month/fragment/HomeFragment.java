package com.bawei.month.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.month.R;
import com.bawei.month.adapter.HomeLeftAdapter;
import com.bawei.month.adapter.HomeRightAdapter;
import com.bawei.month.base.BaseFragment;
import com.bawei.month.base.BaseFragmentPresenter;
import com.bawei.month.bean.FindCategoryBean;
import com.bawei.month.presenter.MyFragmentPresenter;
import com.bawei.month.url.MyUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 14:32
 * @description 首页
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView recycler_left;
    private RecyclerView recycler_right;
    private List<FindCategoryBean.ResultBean> leftList = new ArrayList<>();
    private List<FindCategoryBean.ResultBean.SecondCategoryVoBean> rightList = new ArrayList<>();
    private HomeLeftAdapter leftAdapter;
    private HomeRightAdapter rightAdapter;

    @Override
    protected void initView(View view) {
        recycler_left = view.findViewById(R.id.recycler_left);
        recycler_right = view.findViewById(R.id.recycler_right);
        recycler_left.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_right.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        leftAdapter = new HomeLeftAdapter(getActivity(), leftList);
        rightAdapter = new HomeRightAdapter(getActivity(), rightList);
        recycler_left.setAdapter(leftAdapter);
        recycler_right.setAdapter(rightAdapter);

        p.pGetNoParam(MyUrl.findCategory, FindCategoryBean.class);

        leftAdapter.setItemClick(position -> {
            leftAdapter.setColorPosition(position);
            rightList.clear();
            rightList.addAll(leftList.get(position).getSecondCategoryVo());
            rightAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected BaseFragmentPresenter initPresenter() {
        return new MyFragmentPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void viewSuccess(Object o) {
        if (o instanceof FindCategoryBean) {
            FindCategoryBean bean = (FindCategoryBean) o;
            leftList.addAll(bean.getResult());
            leftAdapter.notifyDataSetChanged();
            rightList.addAll(bean.getResult().get(0).getSecondCategoryVo());
            rightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void viewError(String error) {

    }
}
