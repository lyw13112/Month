package com.bawei.month.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.month.R;
import com.bawei.month.fragment.HomeFragment;
import com.bawei.month.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 11:01
 * @description 首页
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private List<Fragment> fList;
    private List<String> sList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fList.get(position);
            }

            @Override
            public int getCount() {
                return fList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return sList.get(position);
            }
        });
    }

    private void initData() {
        fList = new ArrayList<>();
        fList.add(new HomeFragment());
        fList.add(new MyFragment());
        sList = new ArrayList<>();
        sList.add("首页");
        sList.add("我的");
    }
}
