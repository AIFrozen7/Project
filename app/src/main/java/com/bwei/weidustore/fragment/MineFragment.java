package com.bwei.weidustore.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwei.weidustore.LoginActivity;
import com.bwei.weidustore.MineActivity;
import com.bwei.weidustore.R;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.bean.PersonBean;
import com.bwei.weidustore.custom.CustomMineView;
import com.bwei.weidustore.model.MineModel;
import com.bwei.weidustore.presenter.HomePresenter;
import com.bwei.weidustore.presenter.MinePresenter;
import com.bwei.weidustore.utils.Contract;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Auther: 李
 * @Date: 2019/2/14 12:16:55
 * @Description:
 */
public class MineFragment extends BaseFragment<MinePresenter> implements Contract.IMineView {
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
    private SharedPreferences sp;
    private Map<String,String> map;


    @Override
    protected MinePresenter getPresenter() {
        presenter = new MinePresenter(this);
        return presenter;
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
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");


        if (!TextUtils.isEmpty(userId+"")&&!TextUtils.isEmpty(sessionId)){
            map = new HashMap<>();
            map.put("userId",userId+"");
            map.put("sessionId",sessionId);
            presenter.getPersonDataById(map);
        }
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.nickname_text, R.id.personal_data, R.id.my_circle, R.id.my_foot, R.id.my_wallet, R.id.my_address, R.id.mine_head_img})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), MineActivity.class);
        switch (view.getId()) {
            case R.id.nickname_text:
                String s = nicknameText.getText().toString();
                if (s .equals("未登录") ){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.personal_data:
                intent.putExtra("fragname", "personal");
                startActivity(intent);
                break;
            case R.id.my_circle:
                intent.putExtra("fragname", "circle");
                startActivity(intent);
                break;
            case R.id.my_foot:
                intent.putExtra("fragname", "foot");
                startActivity(intent);
                break;
            case R.id.my_wallet:
                intent.putExtra("fragname", "wallet");
                startActivity(intent);
                break;
            case R.id.my_address:
                intent.putExtra("fragname", "address");
                startActivity(intent);
                break;
            case R.id.mine_head_img:
                break;
        }
    }

    @Override
    public void getMineData(Object o) {
        PersonBean personBean = (PersonBean) o;
        if (personBean.getStatus().equals("0000")){
            nicknameText.setText(personBean.getResult().getNickName());
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(getActivity()).applyDefaultRequestOptions(requestOptions).load(personBean.getResult().getHeadPic()).into(mineHeadImg);
            Toast.makeText(getActivity(), personBean.getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), personBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
