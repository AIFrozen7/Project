package com.bwei.weidustore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.weidustore.LoginActivity;
import com.bwei.weidustore.R;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.custom.CustomMineView;
import com.bwei.weidustore.model.MineModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Auther: 李
 * @Date: 2019/2/14 12:16:55
 * @Description:
 */
public class MineFragment extends BaseFragment<BasePresenter<MineModel>> {
    @BindView(R.id.nickname_text)
    TextView nicknameText;
    @BindView(R.id.personal_data)
    CustomMineView personalData;
    @BindView(R.id.my_circle)
    CustomMineView myCircle;
    @BindView(R.id.my_foot)
    CustomMineView myFoot;
    @BindView(R.id.my_wallet)
    CustomMineView myWallet;
    @BindView(R.id.my_address)
    CustomMineView myAddress;
    @BindView(R.id.mine_head_img)
    ImageView mineHeadImg;
    Unbinder unbinder;

    @Override
    protected BasePresenter<MineModel> getPresenter() {
        return null;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.mine_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.nickname_text, R.id.personal_data, R.id.my_circle, R.id.my_foot, R.id.my_wallet, R.id.my_address, R.id.mine_head_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nickname_text:
                String s = nicknameText.getText().toString();
                if (s == "未登录"){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {

                }
                break;
            case R.id.personal_data:
                break;
            case R.id.my_circle:
                break;
            case R.id.my_foot:
                break;
            case R.id.my_wallet:
                break;
            case R.id.my_address:
                break;
            case R.id.mine_head_img:
                break;
        }
    }
}
