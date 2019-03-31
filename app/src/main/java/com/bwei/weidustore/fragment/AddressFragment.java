package com.bwei.weidustore.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwei.weidustore.AddAddressActivity;
import com.bwei.weidustore.R;
import com.bwei.weidustore.adapter.SelectAddressAdapter;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.bean.DefaultAddressBean;
import com.bwei.weidustore.bean.SelectAddressBean;
import com.bwei.weidustore.presenter.AddressPresenter;
import com.bwei.weidustore.utils.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther: 李
 * @Date: 2019/3/27 19:02:46
 * @Description:
 */
public class AddressFragment extends BaseFragment<AddressPresenter> implements Contract.IAddressView {

    @BindView(R.id.address_recyclerView)
    RecyclerView addressRecyclerView;
    @BindView(R.id.addAddress_btn)
    Button addAddressBtn;
    @BindView(R.id.set_DefaultAddress)
    Button setDefaultAddress;
    private SharedPreferences sp;
    private Map<String, String> map;

    @Override
    protected AddressPresenter getPresenter() {
        presenter = new AddressPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.address_fragment;
    }

    @Override
    protected void initView() {
        //设置布局
        addressRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

        sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");


        if (!TextUtils.isEmpty(userId + "") && !TextUtils.isEmpty(sessionId)) {
            map = new HashMap<>();
            map.put("userId", userId + "");
            map.put("sessionId", sessionId);
            presenter.getAddressData(map);
        }else {
            Toast.makeText(getActivity(), "未登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initListener() {

    }

    //查询收货地址
    @Override
    public void getAddressData(Object o) {
        SelectAddressBean selectAddressBean = (SelectAddressBean) o;
        List<SelectAddressBean.ResultBean> result = selectAddressBean.getResult();
        if (result != null){
            SelectAddressAdapter selectAddressAdapter = new SelectAddressAdapter(result, getActivity());
            addressRecyclerView.setAdapter(selectAddressAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        if (!TextUtils.isEmpty(userId + "") && !TextUtils.isEmpty(sessionId)) {
            map = new HashMap<>();
            map.put("userId", userId + "");
            map.put("sessionId", sessionId);
            presenter.getAddressData(map);
        }
    }

    //设置默认地址
    @Override
    public void getDefaultAddressData(Object o) {
        DefaultAddressBean defaultAddressBean = (DefaultAddressBean) o;
        Toast.makeText(getActivity(), defaultAddressBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(sticky = true)
    public void getMsg(final List<SelectAddressBean.ResultBean> list) {
        setDefaultAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCheck()) {
                        presenter.setAddressData(map,list.get(i).getId());
                    }
                }


            }
        });
    }


    @OnClick(R.id.addAddress_btn)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), AddAddressActivity.class));
    }

}
