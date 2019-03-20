package com.bwei.weidustore.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.weidustore.DetailsActivity;
import com.bwei.weidustore.R;
import com.bwei.weidustore.bean.ShopListBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/2/14 16:46:12
 * @Description:
 */
public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.ViewHolder>{
    private List<ShopListBean.ResultBean.MlssBean.CommodityListBeanXX> mlsslist;
    private Context context;

    public MlssAdapter(List<ShopListBean.ResultBean.MlssBean.CommodityListBeanXX> mlsslist, Context context) {
        this.mlsslist = mlsslist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlss_item_layout, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(mlsslist.get(i).getMasterPic()).into(viewHolder.mlss_img);
        viewHolder.mlss_title.setText(mlsslist.get(i).getCommodityName());
        viewHolder.mlss_price.setText(mlsslist.get(i).getPrice()+"");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(mlsslist.get(i).getCommodityId()+"");
                context.startActivity(new Intent(context, DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlsslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.mlss_img)
        ImageView mlss_img;
        @BindView(R.id.mlss_title)
        TextView mlss_title;
        @BindView(R.id.mlss_price)
        TextView mlss_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
