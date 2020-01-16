package com.bawei.month.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei.month.R;
import com.bawei.month.activity.LoginActivity;
import com.bumptech.glide.Glide;

/**
 * @author 刘云蔚
 * @createTime 2019/12/20 11:49
 * @description 我的
 */
public class MyFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView;
    private TextView textView;
    private boolean isLogin = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.textView);
        textView.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建广播类
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String pic = intent.getStringExtra("pic");
                String name = intent.getStringExtra("name");
                textView.setText(name);
                Glide.with(getActivity()).load(pic).into(imageView);

            }
        };
        //注册接收器
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("ln"));
    }

    @Override
    public void onClick(View v) {
        if (!isLogin) startActivity(new Intent(getActivity(), LoginActivity.class));
    }

}
