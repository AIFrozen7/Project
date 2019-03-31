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
import com.bwei.weidustore.bean.SearchBean;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/3/29 19:45:29
 * @Description:
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<SearchBean.ResultBean> result;
    private Context context;
    private int TYPE_ONE = 1,TYPE_TWO = 2,TYPE_THREE = 3;

    public SearchAdapter(List<SearchBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==TYPE_THREE){
            return new FootViewHolder(LayoutInflater.from(context).inflate(R.layout.footer,null));
        }else {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_recyclerview_item,null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof ViewHolder){
            //设置圆角
            RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(30));
            Glide.with(context).load(result.get(i).getMasterPic()).apply(requestOptions).into(((ViewHolder) viewHolder).search_item_img);

            ((ViewHolder) viewHolder).search_item_title.setText(result.get(i).getCommodityName());
            ((ViewHolder) viewHolder).search_item_price.setText("￥"+result.get(i).getPrice());
            ((ViewHolder) viewHolder).search_item_num.setText("已售"+result.get(i).getSaleNum()+"件");
        }else {

        }

    }


    @Override
    public int getItemCount() {
        return result.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==result.size()){
            return TYPE_THREE;
        }else if (position%2==0){
            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_item_img)
        ImageView search_item_img;
        @BindView(R.id.search_item_title)
        TextView search_item_title;
        @BindView(R.id.search_item_num)
        TextView search_item_num;
        @BindView(R.id.search_item_price)
        TextView search_item_price;
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
