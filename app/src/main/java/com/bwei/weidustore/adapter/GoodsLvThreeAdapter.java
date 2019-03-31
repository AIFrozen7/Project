package com.bwei.weidustore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bwei.weidustore.R;
import com.bwei.weidustore.bean.GoodsLvThreeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/3/30 15:18:55
 * @Description:
 */
public class GoodsLvThreeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<GoodsLvThreeBean.ResultBean> list;
    private Context context;
    private int TYPE_ONE = 1,TYPE_TWO = 2,TYPE_THREE = 3;

    public GoodsLvThreeAdapter(List<GoodsLvThreeBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==TYPE_THREE){
            return new FootViewHolder(LayoutInflater.from(context).inflate(R.layout.footer,null));
        }else {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lv_three_item,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){
            //设置圆角
            RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(30));
            Glide.with(context).load(list.get(i).getMasterPic()).apply(requestOptions).into(((ViewHolder) viewHolder).lv_three_item_img);

            ((ViewHolder) viewHolder).lv_three_item_title.setText(list.get(i).getCommodityName());
            ((ViewHolder) viewHolder).lv_three_item_price.setText("￥"+list.get(i).getPrice());
            ((ViewHolder) viewHolder).lv_three_item_num.setText("已售"+list.get(i).getSaleNum()+"件");
        }else {

        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position==list.size()){
            return TYPE_THREE;
        }else if (position%2==0){
            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lv_three_item_img)
        ImageView lv_three_item_img;
        @BindView(R.id.lv_three_item_title)
        TextView lv_three_item_title;
        @BindView(R.id.lv_three_item_num)
        TextView lv_three_item_num;
        @BindView(R.id.lv_three_item_price)
        TextView lv_three_item_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private class FootViewHolder extends RecyclerView.ViewHolder{
        private ContentLoadingProgressBar progressBar;
        public FootViewHolder(View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.pb_progress);
        }
    }
}
