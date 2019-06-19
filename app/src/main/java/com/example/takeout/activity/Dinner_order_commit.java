package com.example.takeout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.takeout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//李楷 2016051604109 软件工程 2016级

public class Dinner_order_commit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop_pay);
        ButterKnife.bind(this);
    }
    @BindView(R.id.commits)
    TextView commit;
    @BindView(R.id.mback)
    ImageView mback;
    @BindView(R.id.new_address)
    Button add_adsress;
    @OnClick({
            R.id.mback,
            R.id.commits,
            R.id.new_address,
    })
    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.new_address:
                Intent intent=new Intent(this,AddNewadress.class);
                startActivity(intent);
                finish();
                break;
            case R.id.commits:

                break;
            case R.id.mback:
                Intent intent1=new Intent(this,Dinner_shops.class);
                startActivity(intent1);
                finish();
                break;
        }

    }

}
