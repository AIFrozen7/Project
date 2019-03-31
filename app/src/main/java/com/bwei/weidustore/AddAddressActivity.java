package com.bwei.weidustore;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.bean.AddAddress;
import com.bwei.weidustore.presenter.AddAddressPresenter;
import com.bwei.weidustore.utils.Contract;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> implements Contract.IAddAddressView {

    @BindView(R.id.add_address_name)
    EditText addAddressName;
    @BindView(R.id.add_address_phone)
    EditText addAddressPhone;
    @BindView(R.id.add_address_text)
    EditText addAddressText;
    @BindView(R.id.add_address_details)
    EditText addAddressDetails;
    @BindView(R.id.add_address_zipCode)
    EditText addAddressZipcode;
    @BindView(R.id.save_address_btn)
    Button saveAddressBtn;
    private CityPicker mCP;
    private SharedPreferences sp;
    private Map<String,String>map = new HashMap<>();

    @Override
    protected AddAddressPresenter getPresenter() {
        presenter = new AddAddressPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_add_address;
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

    //添加收货地址
    @Override
    public void AddAddressData(Object o) {
        AddAddress addAddress = (AddAddress) o;
        if (addAddress.getStatus().equals("0000")){
            Toast.makeText(this, addAddress.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, addAddress.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //三级联动选择地址
    public void mYunCityPicher() {
        mCP = new CityPicker.Builder(AddAddressActivity.this)
                .textSize(20)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        //监听/把选择好的地址放在这个TextView上
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                String code = citySelected[3];
                addAddressText.setText(province + city + district);
            }

            @Override
            public void onCancel() {
            }
        });
    }



    @OnClick({R.id.add_address_text, R.id.save_address_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_address_text:
                mYunCityPicher();
                mCP.show();
                break;
            case R.id.save_address_btn:
                sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
                int userId = sp.getInt("userId", 0);
                String sessionId = sp.getString("sessionId", "");

                String name = addAddressName.getText().toString();
                String phone = addAddressPhone.getText().toString();
                String address = addAddressText.getText().toString();
                String details = addAddressDetails.getText().toString();
                String zipcode = addAddressZipcode.getText().toString();
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(address)&&!TextUtils.isEmpty(details)&&!TextUtils.isEmpty(zipcode)){
                    String detailsAddress = address + "" + details;
                    if (!TextUtils.isEmpty(userId+"")&&!TextUtils.isEmpty(sessionId)){
                        map = new HashMap<>();
                        map.put("userId",userId+"");
                        map.put("sessionId",sessionId);
                        presenter.AddAddressData(map,name,phone,detailsAddress,zipcode);
                    }
                }else {
                    Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
