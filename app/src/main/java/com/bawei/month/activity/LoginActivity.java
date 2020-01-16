package com.bawei.month.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.month.R;
import com.bawei.month.base.BaseActivity;
import com.bawei.month.base.BaseActivityPresenter;
import com.bawei.month.bean.LoginBean;
import com.bawei.month.presenter.MyActivityPresenter;
import com.bawei.month.url.MyUrl;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 刘云蔚
 * @createTime 2019/12/20 13:45
 * @description 登录页面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String pic;
    private String name;

    @Override
    protected void initData() {

    }

    @Override
    protected BaseActivityPresenter initPresenter() {
        return new MyActivityPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void viewSuccess(Object o) {
        if (o instanceof LoginBean) {
            LoginBean bean = (LoginBean) o;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getStatus().equals("0000")) {
                pic = bean.getResult().getHeadPic();
                name = bean.getResult().getNickName();
            }
        }
    }

    @Override
    public void viewError(String error) {

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String phone = editPhone.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", pwd);
        p.pPostParam(MyUrl.login, map, LoginBean.class);

        Intent intent = new Intent();
        intent.setAction("ln");
        Log.i("TAG", "onViewClicked: " + name);
        intent.putExtra("pic", pic);
        intent.putExtra("name", name);
        //发送广播————注意：需要点击事件才能发送！！！
        sendBroadcast(intent);
    }
}