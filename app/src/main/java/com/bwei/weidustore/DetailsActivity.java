package com.bwei.weidustore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.weidustore.adapter.CommentAdapter;
import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.bean.AddShopCar;
import com.bwei.weidustore.bean.AllShopCarBean;
import com.bwei.weidustore.bean.CommetListBean;
import com.bwei.weidustore.bean.DetailsBean;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.presenter.DetailsPresenter;
import com.bwei.weidustore.utils.Contract;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements Contract.IDetailsView {

    @BindView(R.id.detailds_xbanner)
    XBanner detaildsXbanner;
    @BindView(R.id.detailds_price)
    TextView detaildsPrice;
    @BindView(R.id.detailds_num)
    TextView detaildsNum;
    @BindView(R.id.detailds_name)
    TextView detaildsName;
    @BindView(R.id.detailds_comments)
    TextView detaildsComments;
    @BindView(R.id.detailds_comments_recyclerview)
    RecyclerView detaildsCommentsRecyclerview;
    @BindView(R.id.no_comment_text)
    TextView noCommentText;
    @BindView(R.id.details_web)
    WebView detailsWeb;
    @BindView(R.id.detailds_add)
    ImageButton detaildsAdd;
    @BindView(R.id.detailds_buy)
    ImageButton detaildsBuy;
    private String commodityIdS;
    private List<String> xbannerlist;
    private SharedPreferences sp;
    private Map<String, String> map;
    private List<AllShopCarBean> shopCarBeans = new ArrayList<>();


    @Override
    protected DetailsPresenter getPresenter() {
        presenter = new DetailsPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        //设置recyclerView布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        detaildsCommentsRecyclerview.setLayoutManager(linearLayoutManager);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(commodityIdS)) {
            int commodityId = Integer.parseInt(commodityIdS);
            presenter.getData(commodityId);
            presenter.getCommentData(commodityId);
        }
    }

    @Override
    protected void initListener() {

    }

    //EventBus接收商品ID
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getMsg(String commodityIdString) {
        commodityIdS = commodityIdString;
    }

    //
    @Override
    public void getData(Object o) {
        final DetailsBean detailsBean = (DetailsBean) o;
        DetailsBean.ResultBean result = detailsBean.getResult();

        String picture = detailsBean.getResult().getPicture();
        //轮播
        xbannerlist = Arrays.asList(picture.split(","));
        detaildsXbanner.setData(xbannerlist, null);
        detaildsXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(DetailsActivity.this).load(xbannerlist.get(position)).into((ImageView) view);
            }
        });
        //价格数量名称评论数量
        detaildsPrice.setText("￥" + result.getPrice());
        detaildsNum.setText("已售" + result.getSaleNum() + "件");
        detaildsName.setText(result.getCommodityName());
        detaildsComments.setText("当前评论总数:" + result.getCommentNum());
        //商品规格
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                WebSettings settings = detailsWeb.getSettings();
                settings.setJavaScriptEnabled(true);
                detailsWeb.loadDataWithBaseURL(null, detailsBean.getResult().getDetails(), "text/html", "UTF-8", null);
                detailsWeb.setWebViewClient(new WebViewClient());
            }
        });
    }

    @Override
    public void getCommentData(Object o) {
        CommetListBean commetListBean = (CommetListBean) o;
        List<CommetListBean.ResultBean> result = commetListBean.getResult();

        if (result.size() == 0) {
            noCommentText.setVisibility(View.VISIBLE);
            detaildsCommentsRecyclerview.setVisibility(View.GONE);
        } else {
            CommentAdapter commentAdapter = new CommentAdapter(result, DetailsActivity.this);
            detaildsCommentsRecyclerview.setAdapter(commentAdapter);
        }
    }

    //同步购物车
    @Override
    public void addShopCarData(Object o) {
        AddShopCar addShopCar = (AddShopCar) o;
        Toast.makeText(this, addShopCar.getMessage(), Toast.LENGTH_SHORT).show();
    }
    //查询购物车
    @Override
    public void getShopCarData(Object o) {
        ShopCarBean shopCarBean = (ShopCarBean) o;
        List<ShopCarBean.ResultBean> result = shopCarBean.getResult();

        if (result!=null){
            //将所有查询数据写入集合
            for (int i = 0; i< result.size(); i++){
                    AllShopCarBean allShopCarBean = new AllShopCarBean();
                    allShopCarBean.setCommodityId(result.get(i).getCommodityId());
                    allShopCarBean.setCount(result.get(i).getCount());
                    shopCarBeans.add(allShopCarBean);
            }
            //判断是否存在相同商品
            boolean isExitis = false;
            for (int i=0;i<shopCarBeans.size();i++){
                if (shopCarBeans.get(i).getCommodityId()==Integer.parseInt(commodityIdS)){
                    shopCarBeans.get(i).setCount(shopCarBeans.get(i).getCount()+1);
                    isExitis = true;
                    break;
                }
            }
            if(!isExitis){
                AllShopCarBean allShopCarBean2 = new AllShopCarBean(Integer.parseInt(commodityIdS),1);
                shopCarBeans.add(allShopCarBean2);
            }
            try {
                        JSONArray jsonArray = new JSONArray();
                        for (int i=0;i<shopCarBeans.size();i++){
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("commodityId",shopCarBeans.get(i).getCommodityId());
                            jsonObject.put("count",shopCarBeans.get(i).getCount());
                            jsonArray.put(jsonObject);
                        }
                        String s = jsonArray.toString();
                        Log.i("xxx",s);
                        presenter.addShopCarData(map,s);
                    } catch (Exception e) {
                        e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.detailds_add, R.id.detailds_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detailds_add:
                sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
                int userId = sp.getInt("userId", 0);
                String sessionId = sp.getString("sessionId", "");
                if (!TextUtils.isEmpty(userId+"")&&!TextUtils.isEmpty(sessionId)){
                    map = new HashMap<>();
                    map.put("userId",userId+"");
                    map.put("sessionId",sessionId);
                    presenter.getShopCarData(map);
//                    try {
//                        JSONArray jsonArray = new JSONArray();
//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("commodityId",commodityIdS);
//                        jsonObject.put("count",1);
//
//                        jsonArray.put(jsonObject);
//                        String s = jsonArray.toString();
//                        presenter.addShopCarData(map,s);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.detailds_buy:
                break;
        }
    }
}
