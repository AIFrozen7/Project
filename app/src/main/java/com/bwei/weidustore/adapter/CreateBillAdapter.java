package com.bwei.weidustore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.weidustore.R;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.custom.CustomView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/3/28 11:48:41
 * @Description:
 */
public class CreateBillAdapter extends RecyclerView.Adapter<CreateBillAdapter.ViewHolder>{
    private List<ShopCarBean.ResultBean> list;
    private Context context;

    public CreateBillAdapter(List<ShopCarBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_bill_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.billItemName.setText(list.get(i).getCommodityName());
        viewHolder.billItemPrice.setText(list.get(i).getPrice()+"");
        viewHolder.billItemCustom.setNumber(list.get(i).getCount());
        Glide.with(context).load(list.get(i).getPic()).into(viewHolder.billItemImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.bill_item_name)
        TextView billItemName;
        @BindView(R.id.bill_item_price)
        TextView billItemPrice;
        @BindView(R.id.bill_item_img)
        ImageView billItemImg;
        @BindView(R.id.bill_item_custom)
        CustomView billItemCustom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
