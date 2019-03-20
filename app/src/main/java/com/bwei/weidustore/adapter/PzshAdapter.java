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
 * @Date: 2019/2/14 16:46:32
 * @Description:
 */
public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.ViewHolder>{
    private List<ShopListBean.ResultBean.PzshBean.CommodityListBeanX> pzshlist;
    private Context context;

    public PzshAdapter(List<ShopListBean.ResultBean.PzshBean.CommodityListBeanX> pzshlist, Context context) {
        this.pzshlist = pzshlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pzsh_item_layout, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(pzshlist.get(i).getMasterPic()).into(viewHolder.pzsh_img);
        viewHolder.pzsh_title.setText(pzshlist.get(i).getCommodityName());
        viewHolder.pzsh_price.setText(pzshlist.get(i).getPrice()+"");
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(pzshlist.get(i).getCommodityId()+"");
//                context.startActivity(new Intent(context, DetailsActivity.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return pzshlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.pzsh_img)
        ImageView pzsh_img;
        @BindView(R.id.pzsh_title)
        TextView pzsh_title;
        @BindView(R.id.pzsh_price)
        TextView pzsh_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
