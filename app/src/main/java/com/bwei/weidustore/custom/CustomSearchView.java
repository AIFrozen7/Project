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




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //获取子控件数量
        int childCount = getChildCount();
        //子控件数量为 0 时 不占用空间
        if (childCount == 0){
            setMeasuredDimension(0,0);
        }else {
            //ViewGroup宽高均为包裹内容(wrap_)
            if (widthModel == MeasureSpec.AT_MOST && heightMeasureSpec == MeasureSpec.AT_MOST){
                //高度设为子View中最大的数值 宽设为所有子View宽度之和
                int totalWidth = getTotalWidth();
                int maxHeight = getMaxHeight();
                setMeasuredDimension(totalWidth,maxHeight);
            }else if (heightModel == MeasureSpec.AT_MOST){//如果只有高设为包裹内容(wrap_height)
                //高度设为子View中最大的数值 宽设为ViewGroup自己的测量宽度
                setMeasuredDimension(widthSize,getMaxHeight());
            }else if (widthModel == MeasureSpec.AT_MOST){//如果只有宽设为包裹内容(wrap_width)
                //高度设为ViewGroup自己的测量高度 宽设为所有子View宽度之和
                setMeasuredDimension(getTotalWidth(),heightSize);
            }
        }
    }

    /**
     * 所有子View的width总和
     * @return
     */
    public int getTotalWidth(){
        //获取子控件数量
        int childCount = getChildCount();
        int totalWidth = 0;
        for (int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            totalWidth += childView.getMeasuredWidth();
        }
        return totalWidth;
    }
    /**
     * 所有子View中的height最大值
     * @return
     */
    public int getMaxHeight(){
        //获取子控件数量
        int childCount = getChildCount();
        int maxHeight = 0;
        for (int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            if (maxHeight < childView.getMeasuredHeight()){
                maxHeight = childView.getMeasuredHeight();
            }
        }
        return maxHeight;
    }

    //图片点击吐司及跳转
    setOnImgSearchClickListener setOnImgSearchClickListener;

    public interface setOnImgSearchClickListener{
        void setOnImgSearchClick();
    }

    public void setSetOnImgSearchClickListener(CustomSearchView.setOnImgSearchClickListener setOnImgSearchClickListener) {
        this.setOnImgSearchClickListener = setOnImgSearchClickListener;
    }

//    public void getEdit_SearchContent(){
//
//    }
    //搜索Button点击获取搜索框中数据
    setOnBtnSearchClickListener setOnBtnSearchClickListener;

    public interface setOnBtnSearchClickListener{
        void setOnBtnSearchClick(String searchdata);
    }

    public void setOnEditSearchClickListener(CustomSearchView.setOnBtnSearchClickListener setOnBtnSearchClickListener) {
        this.setOnBtnSearchClickListener = setOnBtnSearchClickListener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

}
