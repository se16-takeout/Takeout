package com.example.test01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test01.R;

import cn.bmob.v3.Bmob;


public class MainActivity extends AppCompatActivity {

    Button bt_shop;
    Button bt_diner;
    Intent intent01,intent02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");


        bt_shop=(Button)findViewById(R.id. bt_shop);
        bt_shop.setOnClickListener(new MyButton() );
        bt_diner=(Button)findViewById(R.id.bt_diner);
        bt_diner.setOnClickListener(new MyButton());




    }


    class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_shop:
                    intent01 = new Intent(getApplication(), Login.class);
                    startActivity(intent01);
                    break;
                case R.id.bt_diner:
                    intent02 = new Intent(getApplication(),Diner_Login_Activity.class);
                    startActivity(intent02);
                    break;

            }
        }
    }

}
