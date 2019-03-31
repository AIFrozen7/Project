package com.bwei.weidustore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.bean.PayBillBean;
import com.bwei.weidustore.presenter.PayBillPresenter;
import com.bwei.weidustore.utils.Contract;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayBillActivity extends BaseActivity<PayBillPresenter> implements Contract.IPayBillView {

    @BindView(R.id.pay_radio_money)
    RadioButton payRadioMoney;
    @BindView(R.id.pay_radio_vx)
    RadioButton payRadioVx;
    @BindView(R.id.pay_radio_zfb)
    RadioButton payRadioZfb;
    @BindView(R.id.pag_bill_btn)
    Button pagBillBtn;
    private SharedPreferences sp;
    private Map<String,String> map;
    private String orderId;
    private String totalPrice;

    @Override
    protected PayBillPresenter getPresenter() {
        presenter = new PayBillPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_pay_bill;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //默认选中余额支付
        payRadioMoney.setChecked(true);
        Intent intent = getIntent();
        totalPrice = intent.getStringExtra("totalPrice");
        orderId = intent.getStringExtra("orderId");
        //根据支付方式选中
        pagBillBtn.setText("余额支付"+ totalPrice +"元");
        payRadioMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    pagBillBtn.setText("余额支付"+ totalPrice +"元");
                }
            }
        });
        payRadioVx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    pagBillBtn.setText("微信支付"+ totalPrice +"元");
                }
            }
        });
        payRadioZfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    pagBillBtn.setText("支付宝支付"+ totalPrice +"元");
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }

    //获取支付后信息
    @Override
    public void payBill(Object o) {
        PayBillBean payBillBean = (PayBillBean) o;
        if (payBillBean.getStatus().equals("0000")){
            Toast.makeText(this, payBillBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, payBillBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    //点击支付
    @OnClick(R.id.pag_bill_btn)
    public void onViewClicked() {
        sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");

        if (!TextUtils.isEmpty(userId+"")&&!TextUtils.isEmpty(sessionId)){
            map = new HashMap<>();
            map.put("userId",userId+"");
            map.put("sessionId",sessionId);
            if (payRadioMoney.isChecked()){
                presenter.payBill(map,orderId,1);
            }
            if (payRadioVx.isChecked()){
                presenter.payBill(map,orderId,2);
            }
            if (payRadioZfb.isChecked()){
                presenter.payBill(map,orderId,3);
            }
        }
    }
}
