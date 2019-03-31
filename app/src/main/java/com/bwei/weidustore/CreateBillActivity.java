package com.bwei.weidustore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.weidustore.adapter.CreateBillAdapter;
import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.bean.CreateBillBean;
import com.bwei.weidustore.bean.CreateBillGoodsBean;
import com.bwei.weidustore.bean.SelectAddressBean;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.presenter.AddressPresenter;
import com.bwei.weidustore.presenter.CreateBillPresenter;
import com.bwei.weidustore.utils.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateBillActivity extends BaseActivity<CreateBillPresenter> implements Contract.ICreateBillView {

    @BindView(R.id.recyclerview_bill)
    RecyclerView recyclerviewBill;
    @BindView(R.id.bill_goods_count)
    TextView billGoodsCount;
    @BindView(R.id.bill_goods_price)
    TextView billGoodsPrice;
    @BindView(R.id.commit_bill_btn)
    Button commitBillBtn;
    @BindView(R.id.bill_address_name)
    TextView billAddressName;
    @BindView(R.id.bill_address_phone)
    TextView billAddressPhone;
    @BindView(R.id.bill_address_text)
    TextView billAddressText;
    private List<ShopCarBean.ResultBean> list2 = new ArrayList<>();
    private SharedPreferences sp;
    private Map<String, String> map;
    private int addressId;
    private double totalPrice;
    private List<CreateBillGoodsBean> goodsList = new ArrayList<>();
    private int intentCommodityId;
    private int intentPrice;
    private String intentName;
    private String intentPic;


    @Override
    protected CreateBillPresenter getPresenter() {
        presenter = new CreateBillPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_create_bill;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        recyclerviewBill.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
    }

    @Override
    protected void initData() {
        sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");

        if (!TextUtils.isEmpty(userId + "") && !TextUtils.isEmpty(sessionId)) {
            map = new HashMap<>();
            map.put("userId", userId + "");
            map.put("sessionId", sessionId);
            presenter.getCreateAddressData(map);
        }else {
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
        }

        //详情页直接创建订单
        Intent intent = getIntent();
        intentCommodityId = intent.getIntExtra("commodityId",0);
        intentPrice = intent.getIntExtra("price", 0);
        intentName = intent.getStringExtra("name");
        intentPic = intent.getStringExtra("pic");
        ShopCarBean.ResultBean resultBean = new ShopCarBean.ResultBean();
        resultBean.setCommodityId(intentCommodityId);
        resultBean.setCount(1);
        resultBean.setCommodityName(intentName);
        resultBean.setPic(intentPic);
        resultBean.setPrice(intentPrice);
        list2.add(resultBean);
        for (int i=0;i<list2.size();i++){
            CreateBillGoodsBean createBillGoodsBean = new CreateBillGoodsBean();
            createBillGoodsBean.setCommodityId(list2.get(i).getCommodityId());
            createBillGoodsBean.setAmount(list2.get(i).getCount());
            goodsList.add(createBillGoodsBean);
        }
        recyclerviewBill.setAdapter(new CreateBillAdapter(list2, this));
        billGoodsPrice.setText(intentPrice +"");
        billGoodsCount.setText(1+"");
    }

    @Override
    protected void initListener() {

    }

    //购物车创建订单获取商品信息
    @Subscribe(sticky = true)
    public void getMsg(List<ShopCarBean.ResultBean> list) {
        int totalNum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCheck()) {
                ShopCarBean.ResultBean resultBean = new ShopCarBean.ResultBean();
                resultBean.setCommodityId(list.get(i).getCommodityId());
                resultBean.setCount(list.get(i).getCount());
                resultBean.setCommodityName(list.get(i).getCommodityName());
                resultBean.setPic(list.get(i).getPic());
                resultBean.setPrice(list.get(i).getPrice());
                list2.add(resultBean);
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            totalPrice += list2.get(i).getPrice();
            totalNum += list2.get(i).getCount();
        }
        billGoodsPrice.setText(totalPrice + "");
        billGoodsCount.setText(totalNum + "");
        for (int i=0;i<list2.size();i++){
            CreateBillGoodsBean createBillGoodsBean = new CreateBillGoodsBean();
            createBillGoodsBean.setCommodityId(list2.get(i).getCommodityId());
            createBillGoodsBean.setAmount(list2.get(i).getCount());
            goodsList.add(createBillGoodsBean);
        }
        recyclerviewBill.setAdapter(new CreateBillAdapter(list2, this));
    }

    //点击创建订单
    @OnClick(R.id.commit_bill_btn)
    public void onViewClicked() {
        if (map != null){
            if (goodsList!=null){
                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i=0;i<goodsList.size();i++){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("commodityId",goodsList.get(i).getCommodityId());
                        jsonObject.put("amount",goodsList.get(i).getAmount());
                        jsonArray.put(jsonObject);
                    }
                    String s = jsonArray.toString();
                    Log.i("xxx",s);
                    String s1 = billGoodsPrice.getText().toString();
                    int price = Integer.parseInt(s1);
                    presenter.createBill(map,s,price,addressId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //查询地址
    @Override
    public void getCreateAddressData(Object o) {
        SelectAddressBean selectAddressBean = (SelectAddressBean) o;
        List<SelectAddressBean.ResultBean> result = selectAddressBean.getResult();
        if (result != null){
            billAddressName.setText(result.get(1).getRealName());
            billAddressPhone.setText(result.get(1).getPhone());
            billAddressText.setText(result.get(1).getAddress());
            addressId = result.get(1).getId();
        }
    }

    //创建订单(获得订单号)
    @Override
    public void createBill(Object o) {
        CreateBillBean createBillBean = (CreateBillBean) o;
        if (createBillBean.getStatus().equals("0000")){
            Toast.makeText(this, createBillBean.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateBillActivity.this,PayBillActivity.class);
            intent.putExtra("orderId",createBillBean.getOrderId());
            intent.putExtra("totalPrice",billGoodsPrice.getText().toString());
            startActivity(intent);
        }else {
            Toast.makeText(this, createBillBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
