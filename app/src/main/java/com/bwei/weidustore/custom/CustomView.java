package com.bwei.weidustore.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwei.weidustore.R;
import com.bwei.weidustore.adapter.ShopCarAdapter;
import com.bwei.weidustore.bean.ShopCarBean;

import java.util.List;


/**
 * @Auther: 李
 * @Date: 2019/2/15 16:46:19
 * @Description:
 */
public class CustomView extends RelativeLayout {


    private ImageView shopcar_item_remove;
    private EditText shopcar_item_edit;
    private ImageView shopcar_item_add;
    private String nums;
    private int num = 0;
    private ShopCarAdapter shopCarAdapter;
    private List<ShopCarBean.ResultBean> list;
    private int position;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(final Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.custom_layout,this);
        shopcar_item_remove = findViewById(R.id.shopcar_item_remove);
        shopcar_item_edit = findViewById(R.id.shopcar_item_edit);
        shopcar_item_add = findViewById(R.id.shopcar_item_add);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.edit_shopcar);
        nums = typedArray.getString(R.styleable.edit_shopcar_nums);
        typedArray.recycle();

        shopcar_item_edit.setText(nums);

        //加减
        shopcar_item_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(setOnAddOrDelClickListener!=null){
                    setOnAddOrDelClickListener.addClick();
                    shopCarAdapter.notifyItemChanged(position);
                }
            }
        });
        shopcar_item_remove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(setOnAddOrDelClickListener!=null){
                    setOnAddOrDelClickListener.delClick();
                    shopCarAdapter.notifyItemChanged(position);
                }
            }
        });
    }

    public void setData(ShopCarAdapter shopCarAdapter, List<ShopCarBean.ResultBean> list, int i) {
        this.shopCarAdapter = shopCarAdapter;
        this.list = list;
        position = i;
        num = list.get(i).getCount();
        shopcar_item_edit.setText(num + "");
    }


    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNumber(int num) {
        if (num > 0) {
            shopcar_item_edit.setText(num + "");
        } else {
            toast();
        }
    }
    public int getNumber() {
        String numStr = shopcar_item_edit.getText().toString().trim();
        num = Integer.valueOf(numStr);
        return num;
    }
    private void toast() {
        Toast.makeText(getContext(), "不能为0", Toast.LENGTH_SHORT).show();
    }
    private void mToast() {
        Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
    }


    //计算总价
    setOnAddOrDelClickListener setOnAddOrDelClickListener;

    public interface setOnAddOrDelClickListener{
        void addClick();
        void delClick();
    }

    public void setSetOnAddOrDelClickListener(CustomView.setOnAddOrDelClickListener setOnAddOrDelClickListener) {
        this.setOnAddOrDelClickListener = setOnAddOrDelClickListener;
    }
}
