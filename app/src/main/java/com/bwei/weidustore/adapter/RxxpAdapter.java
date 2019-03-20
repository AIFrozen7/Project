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
import com.bwei.weidustore.R;
import com.bwei.weidustore.bean.ShopListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/2/14 16:45:58
 * @Description:
 */
public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.ViewHolder>{
    private List<ShopListBean.ResultBean.RxxpBean.CommodityListBean> rxxplist;
    private Context context;

    public RxxpAdapter(List<ShopListBean.ResultBean.RxxpBean.CommodityListBean> rxxplist, Context context) {
        this.rxxplist = rxxplist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rxxp_item_layout, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(rxxplist.get(i).getMasterPic()).into(viewHolder.rxxp_img);
        viewHolder.rxxp_item1.setText(rxxplist.get(i).getCommodityName());
        viewHolder.rxxp_item2.setText(rxxplist.get(i).getPrice()+"");
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(rxxplist.get(i).getCommodityId()+"");
//                context.startActivity(new Intent(context, DetailsActivity.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return rxxplist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rxxp_img)
        ImageView rxxp_img;
        @BindView(R.id.rxxp_item1)
        TextView rxxp_item1;
        @BindView(R.id.rxxp_item2)
        TextView rxxp_item2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
