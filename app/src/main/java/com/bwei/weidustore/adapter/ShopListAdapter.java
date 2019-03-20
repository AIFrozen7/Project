package com.bwei.weidustore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.weidustore.R;
import com.bwei.weidustore.bean.AllBean;
import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.ShopListBean;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/2/28 10:30:16
 * @Description:
 */
public class ShopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private AllBean allBean;
    private Context context;
    private int TYPE_ONE = 0,TYPE_TWO = 1,TYPE_THREE = 2,TYPE_FOUR = 3;

    public ShopListAdapter(AllBean allBean, Context context) {
        this.allBean = allBean;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (TYPE_ONE == i){
            return new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_xbanner,null));
        }else if (TYPE_TWO == i){
            return new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.item_rxxp,null));
        }else if (TYPE_THREE == i){
            return new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.item_mlss,null));
        }else {
            return new ViewHolder4(LayoutInflater.from(context).inflate(R.layout.item_pzsh,null));
        }
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof  ViewHolder1){
            List<BannerBean.ResultBean> result = allBean.getResult();
            if (result!=null){
                final List<String> list = new ArrayList<>();
                for (int j =0; j<result.size(); j++){
                    list.add(result.get(j).getImageUrl());
                }
                ((ViewHolder1) viewHolder).xbanner.setData(list,null);
                ((ViewHolder1) viewHolder).xbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(context).load(list.get(position)).into((ImageView) view);
                        ((ViewHolder1) viewHolder).xbanner.setPageChangeDuration(1000);
                    }
                });
                ((ViewHolder1) viewHolder).xbanner.startAutoPlay();
            }
        }
        if (viewHolder instanceof ViewHolder2){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            ((ViewHolder2) viewHolder).recyclerView_rxxp.setLayoutManager(linearLayoutManager);

            ShopListBean.ResultBean.RxxpBean rxxp = allBean.getRxxp();
            ((ViewHolder2) viewHolder).rxxp_text.setText(rxxp.getName());
            RxxpAdapter rxxpAdapter = new RxxpAdapter(rxxp.getCommodityList(),context);
            ((ViewHolder2) viewHolder).recyclerView_rxxp.setAdapter(rxxpAdapter);
        }
        if (viewHolder instanceof ViewHolder3){
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
            linearLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
            ((ViewHolder3) viewHolder).recyclerView_mlss.setLayoutManager(linearLayoutManager2);

            ShopListBean.ResultBean.MlssBean mlss = allBean.getMlss();
            ((ViewHolder3) viewHolder).mlss_text.setText(mlss.getName());
            MlssAdapter mlssAdapter = new MlssAdapter(mlss.getCommodityList(),context);
            ((ViewHolder3) viewHolder).recyclerView_mlss.setAdapter(mlssAdapter);
        }
        if (viewHolder instanceof ViewHolder4){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
            ((ViewHolder4) viewHolder).recyclerView_pzsh.setLayoutManager(gridLayoutManager);

            ShopListBean.ResultBean.PzshBean pzsh = allBean.getPzsh();
            ((ViewHolder4) viewHolder).pzsh_text.setText(pzsh.getName());
            PzshAdapter pzshAdapter = new PzshAdapter(pzsh.getCommodityList(),context);
            ((ViewHolder4) viewHolder).recyclerView_pzsh.setAdapter(pzshAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_ONE;
        }else if (position==1){
            return TYPE_TWO;
        }else if (position==2){
            return TYPE_THREE;
        }else{
            return TYPE_FOUR;
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{

        @BindView(R.id.xbanner)
        XBanner xbanner;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{

        @BindView(R.id.rxxp_text)
        TextView rxxp_text;
        @BindView(R.id.recyclerView_rxxp)
        RecyclerView recyclerView_rxxp;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder{

        @BindView(R.id.mlss_text)
        TextView mlss_text;
        @BindView(R.id.recyclerView_mlss)
        RecyclerView recyclerView_mlss;
        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder{

        @BindView(R.id.pzsh_text)
        TextView pzsh_text;
        @BindView(R.id.recyclerView_pzsh)
        RecyclerView recyclerView_pzsh;
        public ViewHolder4(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
}
