package com.bwei.weidustore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.weidustore.R;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.custom.CustomView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/3/2 14:53:40
 * @Description:
 */
public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.ViewHolder>{
    private List<ShopCarBean.ResultBean> list;
    private Context context;
    private int defaultprice;
    private int price;

    public ShopCarAdapter(List<ShopCarBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //接收全选按钮状态
    public void getCheckBoxAllState(Boolean checked){
        for (int i=0;i<list.size();i++){
            list.get(i).setCheck(checked);
        }
        //刷新适配器
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopcar_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getPic()).into(viewHolder.shopcar_item_img);
        viewHolder.shopcar_item_name.setText(list.get(i).getCommodityName());
        viewHolder.shopcar_item_price.setText("￥"+list.get(i).getPrice()+"");
        viewHolder.shopcar_item_checkbox.setChecked(list.get(i).getCheck());

        //数量改变时
        viewHolder.shopcar_item_custom.setSetOnAddOrDelClickListener(new CustomView.setOnAddOrDelClickListener() {
            @Override
            public void addClick() {
                int number = viewHolder.shopcar_item_custom.getNumber();
                number++;
//                viewHolder.shopcar_item_custom.setNumber(number);
                list.get(i).setCount(number);

            }

            @Override
            public void delClick() {
                int number2 = viewHolder.shopcar_item_custom.getNumber();
                number2--;
//                viewHolder.shopcar_item_custom.setNumber(number2);
                if (number2<0){
                    Toast.makeText(context, "不能为0", Toast.LENGTH_SHORT).show();
                }else {
                    list.get(i).setCount(number2);
                }

            }
        });

        viewHolder.shopcar_item_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(i).setCheck(isChecked);
                for (int j=0;j<list.size();j++){
                    if (!list.get(j).getCheck()){
                        listener.onCheck(false);
                        setAllPriceListenter.setAllPrice(list);
                        return;
                    }
                }

                listener.onCheck(true);
                setAllPriceListenter.setAllPrice(list);

            }
        });
        viewHolder.shopcar_item_custom.setData(this,list,i);
        setAllPriceListenter.setAllPrice(list);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.shopcar_item_name)
        TextView shopcar_item_name;
        @BindView(R.id.shopcar_item_price)
        TextView shopcar_item_price;
        @BindView(R.id.shopcar_item_img)
        ImageView shopcar_item_img;
        @BindView(R.id.shopcar_item_checkbox)
        CheckBox shopcar_item_checkbox;
        @BindView(R.id.shopcar_item_custom)
        CustomView shopcar_item_custom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    //定义CheckBox点击的接口回调
    private OnCheckListener listener;

    public interface OnCheckListener {
        void onCheck(boolean isCheck);
    }
    public void setOnCheckListener(OnCheckListener listener) {
        this.listener = listener;
    }

    //计算总价
    setAllPriceListenter setAllPriceListenter;

    public interface setAllPriceListenter{
        void setAllPrice(List<ShopCarBean.ResultBean> list);
    }
    public void setSetAllPriceListenter(ShopCarAdapter.setAllPriceListenter setAllPriceListenter) {
        this.setAllPriceListenter = setAllPriceListenter;
    }
}
