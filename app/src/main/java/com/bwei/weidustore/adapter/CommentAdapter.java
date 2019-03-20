package com.bwei.weidustore.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.bwei.weidustore.bean.CommetListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/2/28 21:26:37
 * @Description:
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private List<CommetListBean.ResultBean> list;
    private Context context;

    public CommentAdapter(List<CommetListBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutInflater.from(context).inflate(R.layout.comment_item,null)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.comment_name.setText(list.get(i).getNickName());
        viewHolder.comment_time.setText( list.get(i).getCreateTime()+"");
        viewHolder.comment_content.setText(list.get(i).getContent());
        //设置圆角
        RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(30));
        Glide.with(context).applyDefaultRequestOptions(requestOptions).load(list.get(i).getImage()).into(viewHolder.comment_content_img);

//        viewHolder.comment_content_img.setImageURI(Uri.parse(list.get(i).getImage()));
        //圆角头像
        RequestOptions requestOptions2 = RequestOptions.circleCropTransform();
        Glide.with(context).load(list.get(i).getHeadPic()).apply(requestOptions2).into(viewHolder.comment_headimg);

//        viewHolder.comment_headimg.setImageURI(Uri.parse(list.get(i).getImage()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.comment_name)
        TextView comment_name;
        @BindView(R.id.comment_time)
        TextView comment_time;
        @BindView(R.id.comment_content)
        TextView comment_content;
        @BindView(R.id.comment_headimg)
        ImageView comment_headimg;
        @BindView(R.id.comment_content_img)
        ImageView comment_content_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
