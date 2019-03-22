package com.bwei.weidustore.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.weidustore.R;
import com.bwei.weidustore.adapter.ShopCarAdapter;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.presenter.ShopCarPresenter;
import com.bwei.weidustore.utils.Contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Auther: 李
 * @Date: 2019/2/14 12:16:40
 * @Description:
 */
public class ShopCarFragment extends BaseFragment<ShopCarPresenter> implements Contract.IShopCarView {
    @BindView(R.id.shopcar_all_xrecyclerview)
    RecyclerView shopcarAllXrecyclerview;
    @BindView(R.id.shopcar_checkbox_all)
    CheckBox shopcarCheckboxAll;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.shopcar_allprice)
    TextView shopcarAllprice;
    @BindView(R.id.shopcar_btn)
    Button shopcarBtn;
    Unbinder unbinder;
    private List<ShopCarBean.ResultBean> result;
    private ShopCarAdapter shopCarAdapter;
    private Map<String,String>map = new HashMap<>();
    private int priceAll = 0;
    private int countAll = 0;

    @Override
    protected ShopCarPresenter getPresenter() {
        presenter = new ShopCarPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.shop_car_layout;
    }

    @Override
    protected void initView() {
        shopcarAllXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL,false));
    }

    @Override
    protected void initData() {
        //获取登录账户信息
        SharedPreferences sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        //账户信息存入map进行传值
        map.put("userId",userId+"");
        map.put("sessionId",sessionId);
        presenter.getShopCarDataById(map);
    }

    @Override
    protected void initListener() {
        //全选改变状态
        shopcarCheckboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopCarAdapter!=null){
                    shopCarAdapter.getCheckBoxAllState(shopcarCheckboxAll.isChecked());
                }
            }
        });
    }

    //查询购物车
    @Override
    public void getShopCarData(Object o) {
        ShopCarBean shopCarBean = (ShopCarBean) o;
        result = shopCarBean.getResult();
        shopCarAdapter = new ShopCarAdapter(result, getActivity());
        shopCarAdapter.setOnCheckListener(new ShopCarAdapter.OnCheckListener() {
            @Override
            public void onCheck(boolean isCheck) {
                shopcarCheckboxAll.setChecked(isCheck);
            }
        });

        shopcarAllXrecyclerview.setAdapter(shopCarAdapter);
        shopCarAdapter.setSetAllPriceListenter(new ShopCarAdapter.setAllPriceListenter() {
            @Override
            public void setAllPrice(List<ShopCarBean.ResultBean> list) {
                int totalPrice = 0;
                int totalNum = 0;
                for (int i=0;i<list.size();i++){
                    if (list.get(i).getCheck()){
                        int price = list.get(i).getPrice();
                        int count = list.get(i).getCount();
                        priceAll = price * count;
                        countAll = count;
                    }else {
                        priceAll = 0;
                        countAll = 0;
                    }
                    totalPrice += priceAll;
                    totalNum += countAll;
                }
                shopcarAllprice.setText(totalPrice+"");
                shopcarBtn.setText("结算("+totalNum+")");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map!=null){
            presenter.getShopCarDataById(map);
        }
    }
}
