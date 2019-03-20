package com.bwei.weidustore;

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

import com.bumptech.glide.Glide;
import com.bwei.weidustore.adapter.CommentAdapter;
import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.bean.CommetListBean;
import com.bwei.weidustore.bean.DetailsBean;
import com.bwei.weidustore.model.DetailsModel;
import com.bwei.weidustore.presenter.HomePresenter;
import com.bwei.weidustore.utils.Contract;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity<BasePresenter<DetailsModel>> implements Contract.IDetailsView {

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


    @Override
    protected BasePresenter<DetailsModel> getPresenter() {
        presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        //设置recyclerView布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this)   ;
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        detaildsCommentsRecyclerview.setLayoutManager(linearLayoutManager);
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
        detaildsXbanner.setData(xbannerlist,null);
        detaildsXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(DetailsActivity.this).load(xbannerlist.get(position)).into((ImageView)view);
            }
        });
        //价格数量名称评论数量
        detaildsPrice.setText("￥"+detailsBean.getResult().getPrice());
        detaildsNum.setText("已售"+detailsBean.getResult().getSaleNum()+"件");
        detaildsName.setText(detailsBean.getResult().getCategoryName());
        detaildsComments.setText("当前评论总数:"+detailsBean.getResult().getCommentNum());
        //商品规格
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                WebSettings settings = detailsWeb.getSettings();
                settings.setJavaScriptEnabled(true);
                detailsWeb.loadDataWithBaseURL(null,detailsBean.getResult().getDetails(),"text/html","UTF-8",null);
                detailsWeb.setWebViewClient(new WebViewClient());
            }
        });
    }

    @Override
    public void getCommentData(Object o) {
        CommetListBean commetListBean = (CommetListBean) o;
        List<CommetListBean.ResultBean> result = commetListBean.getResult();

        if (result.size()==0){
            noCommentText.setVisibility(View.VISIBLE);
            detaildsCommentsRecyclerview.setVisibility(View.GONE);
        }else {
            CommentAdapter commentAdapter = new CommentAdapter(result, DetailsActivity.this);
            detaildsCommentsRecyclerview.setAdapter(commentAdapter);
        }
    }

    @Override
    public void addShopCarData(Object o) {

    }
}
