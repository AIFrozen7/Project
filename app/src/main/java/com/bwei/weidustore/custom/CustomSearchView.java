package com.bwei.weidustore.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bwei.weidustore.R;


/**
 * @Auther: 李
 * @Date: 2019/2/19 09:52:38
 * @Description:
 */
public class CustomSearchView extends RelativeLayout {

    private ImageView img_search;
    private EditText edit_search;
    private Button btn_search;
    private String search_attr_edit_search_text;
    private String search_attr_right_button_text;
    private int search_attr_left_img_src;
    private String search_attr_edit_search_hint;

    public CustomSearchView(Context context) {
        super(context);
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取布局
        LayoutInflater.from(context).inflate(R.layout.customsearchview, this);
        //控件
        img_search = findViewById(R.id.img_search);
        edit_search = findViewById(R.id.edit_search);
        btn_search = findViewById(R.id.btn_search);



        //拿到属性attr资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.search_attr);
        //获取资源中属性
        search_attr_left_img_src = typedArray.getResourceId(R.styleable.search_attr_left_img_src, 0);
        search_attr_edit_search_text = typedArray.getString(R.styleable.search_attr_edit_search_text);
        search_attr_edit_search_hint = typedArray.getString(R.styleable.search_attr_edit_search_hint);
        search_attr_right_button_text = typedArray.getString(R.styleable.search_attr_right_button_text);
        //释放资源
        typedArray.recycle();
        //将资源赋值 到 控件
        img_search.setImageResource(search_attr_left_img_src);
        edit_search.setText(search_attr_edit_search_text);
        btn_search.setText(search_attr_right_button_text);
        edit_search.setHint(search_attr_edit_search_hint);

        img_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnImgSearchClickListener.setOnImgSearchClick();
            }
        });
        btn_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnBtnSearchClickListener.setOnBtnSearchClick(edit_search.getText().toString());
            }
        });
    }

    public CustomSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //图片点击吐司及跳转
    setOnImgSearchClickListener setOnImgSearchClickListener;

    public interface setOnImgSearchClickListener{
        void setOnImgSearchClick();
    }

    public void setSetOnImgSearchClickListener(CustomSearchView.setOnImgSearchClickListener setOnImgSearchClickListener) {
        this.setOnImgSearchClickListener = setOnImgSearchClickListener;
    }

    //搜索Button点击获取搜索框中数据
    setOnBtnSearchClickListener setOnBtnSearchClickListener;

    public interface setOnBtnSearchClickListener{
        void setOnBtnSearchClick(String searchdata);
    }

    public void setOnEditSearchClickListener(CustomSearchView.setOnBtnSearchClickListener setOnBtnSearchClickListener) {
        this.setOnBtnSearchClickListener = setOnBtnSearchClickListener;
    }


}
