package com.bwei.weidustore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.bean.RegistBean;
import com.bwei.weidustore.model.RegistModel;
import com.bwei.weidustore.presenter.HomePresenter;
import com.bwei.weidustore.presenter.RegistPresenter;
import com.bwei.weidustore.utils.Contract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity<RegistPresenter> implements Contract.IRegistView {
    @BindView(R.id.regist_phone)
    EditText registPhone;
    @BindView(R.id.regist_pwd)
    EditText registPwd;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.regist)
    Button regist;


    @Override
    protected RegistPresenter getPresenter() {
        presenter = new RegistPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.text_login, R.id.regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_login:
                startActivity(new Intent(RegistActivity.this,LoginActivity.class));
                finish();
                break;
            case R.id.regist:
                String phone = registPhone.getText().toString();
                String pwd = registPwd.getText().toString();
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    presenter.getRegist(phone,pwd);
                }
                break;
        }
    }

    @Override
    public void getRegistData(Object o) {
        RegistBean registBean = (RegistBean) o;
        if (registBean.getStatus().equals("0000")){
            Toast.makeText(this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
