package com.example.takeout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.takeout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Addadress extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_add_recrving_address);
        ButterKnife.bind(this);
    }
    @BindView(R.id.fback)
    ImageView mback;
    @OnClick({
            R.id.fback,
    })
    public void onClick(View view){

        switch (view.getId())
        {

            case R.id.fback:
                Intent intent1=new Intent(this,AddNewadress.class);
                startActivity(intent1);
                finish();
                break;
        }

    }
}
