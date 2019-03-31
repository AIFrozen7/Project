package com.bwei.weidustore;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.weidustore.fragment.AddressFragment;
import com.bwei.weidustore.fragment.PersonalFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineActivity extends AppCompatActivity {

    @BindView(R.id.mine_title)
    TextView mineTitle;
    PersonalFragment personalFragment;
    AddressFragment addressFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);

        String fragname = getIntent().getStringExtra("fragname");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (fragname){
            case "personal":
                if (personalFragment!=null){
                    transaction.show(personalFragment);
                }else {
                    personalFragment = new PersonalFragment();
                    transaction.add(R.id.mine_framelayout,personalFragment).show(personalFragment);
                }

                break;
            case "address":
                if (addressFragment!=null){
                    transaction.show(addressFragment);
                }else {
                    addressFragment = new AddressFragment();
                    transaction.add(R.id.mine_framelayout,addressFragment).show(addressFragment);
                }
                mineTitle.setText("我的收货地址");
                break;
        }
        transaction.commit();
    }
}
