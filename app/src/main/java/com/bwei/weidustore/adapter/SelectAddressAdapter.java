package com.bwei.weidustore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwei.weidustore.R;
import com.bwei.weidustore.bean.SelectAddressBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/3/27 20:13:49
 * @Description:
 */
public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.ViewHolder>{
    private List<SelectAddressBean.ResultBean> list;
    private Context context;

    public SelectAddressAdapter(List<SelectAddressBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.address_fragment_item_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.addressName.setText(list.get(i).getRealName());
        viewHolder.addressPhone.setText(list.get(i).getPhone());
        viewHolder.addressText.setText(list.get(i).getAddress());
        viewHolder.addressDefaultCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(i).setCheck(viewHolder.addressDefaultCheckbox.isChecked());
                EventBus.getDefault().postSticky(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.address_name)
        TextView addressName;
        @BindView(R.id.address_phone)
        TextView addressPhone;
        @BindView(R.id.address_text)
        TextView addressText;
        @BindView(R.id.address_default_checkbox)
        CheckBox addressDefaultCheckbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    setDefaultCheckBoxListener DefaultCheckBoxListener;

    public interface setDefaultCheckBoxListener{
        void setCheck(List<SelectAddressBean.ResultBean> list);
    }

    public void setDefaultCheckBoxListener(setDefaultCheckBoxListener defaultCheckBoxListener) {
        DefaultCheckBoxListener = defaultCheckBoxListener;
    }
}
