package com.bwei.weidustore;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.fragment.BillFragment;
import com.bwei.weidustore.fragment.CircleFragment;
import com.bwei.weidustore.fragment.HomeFragment;
import com.bwei.weidustore.fragment.MineFragment;
import com.bwei.weidustore.fragment.ShopCarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

//    @BindView(R.id.rb1)
//    RadioButton rb1;
//    @BindView(R.id.rb2)
//    RadioButton rb2;
//    @BindView(R.id.rb3)
//    RadioButton rb3;
//    @BindView(R.id.rb4)
//    RadioButton rb4;
//    @BindView(R.id.rb5)
//    RadioButton rb5;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private CircleFragment circleFragment;
    private ShopCarFragment shopCarFragment;
    private MineFragment mineFragment;
    private BillFragment billFragment;
    private FragmentManager manager;
    private Unbinder bind;
    private HomeFragment homeFragment;

    //初始化P层
    @Override
    protected Object getPresenter() {
        return null;
    }
    //初始化布局
    @Override
    protected int getlayoutResID() {
        return R.layout.activity_main;
    }
    //初始化控件
    @Override
    protected void initView() {
        bind = ButterKnife.bind(this);
    }

    //初始化数据
    @Override
    protected void initData() {

    }
    //初始化监听
    @Override
    protected void initListener() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.relative_layout, homeFragment);
        transaction.commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();

                if (homeFragment !=null){
                    transaction.hide(homeFragment);
                }
                if (circleFragment!=null){
                    transaction.hide(circleFragment);
                }
                if (shopCarFragment!=null){
                    transaction.hide(shopCarFragment);
                }
                if (billFragment!=null){
                    transaction.hide(billFragment);
                }
                if (mineFragment!=null){
                    transaction.hide(mineFragment);
                }
                switch (checkedId){
                    case R.id.rb1:
                        if (homeFragment ==null){
                            homeFragment = new HomeFragment();
                            transaction.add(R.id.relative_layout, homeFragment);
                        }else {
                            transaction.show(homeFragment);
                        }
                        break;
                    case R.id.rb2:
                        if (circleFragment==null){
                            circleFragment = new CircleFragment();
                            transaction.add(R.id.relative_layout,circleFragment);
                        }else {
                            transaction.show(circleFragment);
                        }
                        break;
                    case R.id.rb3:
                        if (shopCarFragment==null){
                            shopCarFragment = new ShopCarFragment();
                            transaction.add(R.id.relative_layout,shopCarFragment);
                        }else {
                            transaction.show(shopCarFragment);
                        }
                        break;
                    case R.id.rb4:
                        if (billFragment==null){
                            billFragment = new BillFragment();
                            transaction.add(R.id.relative_layout,billFragment);
                        }else {
                            transaction.show(billFragment);
                        }
                        break;
                    case R.id.rb5:
                        if (mineFragment==null){
                            mineFragment = new MineFragment();
                            transaction.add(R.id.relative_layout,mineFragment);
                        }else {
                            transaction.show(mineFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
