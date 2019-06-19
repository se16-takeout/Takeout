package com.example.takeout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.takeout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewadress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_address_list);
        ButterKnife.bind(this);
    }
    @BindView(R.id.add_address)
    TextView add;
    @BindView(R.id.sback)
    ImageView back;
    @OnClick({
            R.id.sback,
            R.id.add_address,
    })
    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.add_address:
                Intent intent=new Intent(this,Addadress.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sback:
                Intent intent1=new Intent(this,Dinner_order_commit.class);
                startActivity(intent1);
                finish();
                break;
        }

    }
}
