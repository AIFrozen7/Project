package com.bwei.weidustore.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwei.weidustore.R;


/**
 * @Auther: 李
 * @Date: 2019/2/20 16:51:49
 * @Description:
 */
public class CustomMineView extends RelativeLayout {

    private ImageView custom_mine_img;
    private TextView custom_mine_text;

    public CustomMineView(Context context) {
        super(context);
    }

    public CustomMineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.customview_mine,this);
        custom_mine_img = findViewById(R.id.custom_mine_img);
        custom_mine_text = findViewById(R.id.custom_mine_text);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.mine_custom_attr);
        String mine_custom_attr_mine_custom_textview_text = typedArray.getString(R.styleable.mine_custom_attr_mine_custom_textview_text);
        int mine_custom_attr_mine_custom_img_src = typedArray.getResourceId(R.styleable.mine_custom_attr_mine_custom_img_src, 0);
        typedArray.recycle();

        custom_mine_img.setImageResource(mine_custom_attr_mine_custom_img_src);
        custom_mine_text.setText(mine_custom_attr_mine_custom_textview_text);

    }

    public CustomMineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }




}
