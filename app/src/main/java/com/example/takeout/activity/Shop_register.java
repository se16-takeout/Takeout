package com.example.takeout.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.takeout.R;
import butterknife.ButterKnife;

public class Shop_register extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
        ButterKnife.bind(this);
    }
}
