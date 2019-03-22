package com.bwei.weidustore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.bean.LoginBean;
import com.bwei.weidustore.presenter.LoginPresenter;
import com.bwei.weidustore.utils.Contract;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements Contract.ILoginView {


    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.rmb_checkbox)
    CheckBox rmbCheckbox;
    @BindView(R.id.qq_login)
    ImageView qqLogin;
    @BindView(R.id.text_regist)
    TextView textRegist;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private SharedPreferences sp;


    @Override
    protected LoginPresenter getPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //记住密码
        sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        if (sp.getBoolean("rmb_checkbox", false)) {
            loginPhone.setText(sp.getString("phone", ""));
            loginPwd.setText(sp.getString("pwd", ""));
            rmbCheckbox.setChecked(sp.getBoolean("rmb_checkbox", false));
        }
    }

    @Override
    protected void initListener() {

    }


    //登录
    @Override
    public void getLoginData(Object o) {
        LoginBean loginBean = (LoginBean) o;
        if (loginBean.getStatus().equals("0000")) {
            //记住密码
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("rmb_checkbox", rmbCheckbox.isChecked());
            edit.putString("phone", loginPhone.getText().toString());
            edit.putString("pwd", loginPwd.getText().toString());
            edit.putInt("userId", loginBean.getResult().getUserId());
            edit.putString("sessionId", loginBean.getResult().getSessionId());
            edit.commit();
            finish();
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    @OnClick({R.id.text_regist, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
            case R.id.btn_login:
                String phone = loginPhone.getText().toString();
                String pwd = loginPwd.getText().toString();
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    presenter.getLogin(phone,pwd);
                }
                break;
        }
    }
}
