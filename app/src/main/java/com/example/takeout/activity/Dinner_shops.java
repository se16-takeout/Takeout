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

public class Dinner_shops extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop);
        ButterKnife.bind(this);
    }
    @BindView(R.id.id_backs)
    ImageView backs;
    @BindView(R.id.id_pay)
    TextView pays;

    @OnClick({
            R.id.id_backs,
            R.id.id_pay,
    })
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.id_pay:
                Intent intent =new Intent(this,Dinner_order_commit.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.id_backs:
                Intent intent1 =new Intent(this,Dinner_main.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
