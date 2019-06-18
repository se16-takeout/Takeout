package com.example.takeout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeout.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main_Login extends AppCompatActivity {
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        ButterKnife.bind(this);

        mDBOpenHelper = new DBOpenHelper(this);
        Intent intents = getIntent();
        String num = intents.getStringExtra("num");
        String passwords = intents.getStringExtra("password");
        String scrnames = intents.getStringExtra("scrname");
        number.setText(num);
        password.setText(passwords);
        user.setText(scrnames);
    }

    @BindView(R.id.username)
    TextView user;
    @BindView(R.id.user)
    Button register;
    @BindView(R.id.shop)
    Button registershop;
    @BindView(R.id.loginactivity_number)
    EditText number;
    @BindView(R.id.loginactivity_password)
    EditText password;
    @BindView(R.id.loginactivity_login)
    Button login;
    @OnClick({
            R.id.loginactivity_login,
            R.id.shop,
            R.id.user,
    })

    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.user:
                Intent intent = new Intent(this,User_register.class);
                startActivity(intent);
                finish();
                break;
            case R.id.loginactivity_login:
                login();
                break;
            case R.id.shop:
                Intent intent1 = new Intent(this,Shop_register.class);
                startActivity(intent1);
                finish();
                break;
        }
    }

    public void login(){
        String num = number.getText().toString().trim();
        String passwords = password.getText().toString().trim();
        String scrname = user.getText().toString().trim();
        if (!TextUtils.isEmpty(num) && !TextUtils.isEmpty(passwords)) {
            ArrayList<User> data = mDBOpenHelper.getAllData();
            boolean match = false;
            for(int i=0;i<data.size();i++) {
                User user = data.get(i);
                if (num.equals(user.getName()) && passwords.equals(user.getPassword())){
                    match = true;
                    break;
                }else{
                    match = false;
                }
            }

            if (match) {
                Intent intent=new Intent(this,User_main.class);
                startActivity(intent);
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();//TODO 跳转
            }else {
                Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
        }
    }
}
