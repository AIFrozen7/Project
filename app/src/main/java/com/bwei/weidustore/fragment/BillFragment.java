package com.bwei.weidustore.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bwei.weidustore.R;
import com.bwei.weidustore.adapter.SelectBillAdapter;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.bean.ReceiptBean;
import com.bwei.weidustore.bean.SelectBillBean;
import com.bwei.weidustore.presenter.SelectBillPresenter;
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
 * @Date: 2019/3/2 15:19:15
 * @Description:
 */
public class BillFragment extends BaseFragment<SelectBillPresenter> implements Contract.ISelectBillView {


    @BindView(R.id.bill_fragment_recyclerview)
    RecyclerView billFragmentRecyclerview;
    @BindView(R.id.img_all)
    RadioButton imgAll;
    @BindView(R.id.img_waitPay)
    RadioButton imgWaitPay;
    @BindView(R.id.img_waitReceive)
    RadioButton imgWaitReceive;
    @BindView(R.id.img_waitComment)
    RadioButton imgWaitComment;
    @BindView(R.id.img_finish)
    RadioButton imgFinish;
    private SharedPreferences sp;
    private Map<String, String> map;
    private SelectBillAdapter selectBillAdapter;

    @Override
    protected SelectBillPresenter getPresenter() {
        presenter = new SelectBillPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.bill_layout;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        //设置布局
        billFragmentRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
    }

    @Override
    protected void initData() {
        //默认加载全部订单
        //0=查看全部  1=查看待付款  2=查看待收货  3=查看待评价  9=查看已完成
        sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        if (!TextUtils.isEmpty(userId + "") && !TextUtils.isEmpty(sessionId)) {
            map = new HashMap<>();
            map.put("userId", userId + "");
            map.put("sessionId", sessionId);
            presenter.selectBill(map, 0);
        }
    }

    @Override
    protected void initListener() {

    }
    //获取收货订单ID
    @Subscribe(sticky = true)
    public void getMsg(String orderId){
        presenter.receiptData(map,orderId);
    }

    //根据订单状态查询订单
    @Override
    public void selectBill(Object o) {
        SelectBillBean selectBillBean = (SelectBillBean) o;
        List<SelectBillBean.OrderListBean> orderList = selectBillBean.getOrderList();
        selectBillAdapter = new SelectBillAdapter(orderList, getActivity());
        billFragmentRecyclerview.setAdapter(selectBillAdapter);
    }
    //收货
    @Override
    public void receiptData(Object o) {
        ReceiptBean receiptBean = (ReceiptBean) o;
        if (receiptBean.getStatus().equals("0000")){
            if (selectBillAdapter!=null){
                presenter.selectBill(map, 0);
            }
        }
        Toast.makeText(getActivity(), receiptBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_all, R.id.img_waitPay, R.id.img_waitReceive, R.id.img_waitComment, R.id.img_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_all:
                presenter.selectBill(map, 0);
                break;
            case R.id.img_waitPay:
                presenter.selectBill(map, 1);
                break;
            case R.id.img_waitReceive:
                presenter.selectBill(map, 2);
                break;
            case R.id.img_waitComment:
                presenter.selectBill(map, 3);
                break;
            case R.id.img_finish:
                presenter.selectBill(map, 9);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
