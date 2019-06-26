package com.example.takeout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.takeout.R;
import com.example.takeout.activity.bmob.Dinerinfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

//李楷 2016051604109 软件工程 2016级
//2016051604121 周鹏刚

public class Dinner_register extends AppCompatActivity {

    private String realCode;
    //  private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        ButterKnife.bind(this);

        //  mDBOpenHelper = new DBOpenHelper(this);

        //将验证码用图片的形式显示出来
        showCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }


    @BindView(R.id.registeractivity_number)
    EditText number;
    @BindView(R.id.registeractivity_password1)
    EditText pwd1;
    @BindView(R.id.registeractivity_password2)
    EditText pwd2;
    @BindView(R.id.registeractivity_phoneCodes)
    EditText phoneCode;
    @BindView(R.id.screenname)
    EditText name;
    @BindView(R.id.iv_registeractivity_showCode)
    ImageView showCode;
    @BindView(R.id.registeractivity_back)
    ImageView back;
    @BindView(R.id.registeractivity_register)
    Button register;

    @OnClick({
            R.id.registeractivity_back,
            R.id.registeractivity_register,
            R.id.iv_registeractivity_showCode,
    })

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registeractivity_back:
                Intent intent = new Intent(this,Main_Login.class);
                startActivity(intent);
                break;
            case R.id.registeractivity_register:
                register();
                break;
            case R.id.iv_registeractivity_showCode:
                showCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
        }
    }

    public void register(){
        Bmob.initialize(this, "cd37c8430268b5d553cffa32e339817d");
        String scrname = name.getText().toString().trim();
        String num = number.getText().toString().trim();
        String password = pwd1.getText().toString().trim();
        String password2=pwd2.getText().toString().trim();
        String phoneCodes = phoneCode.getText().toString().toLowerCase();
        Dinerinfo diner=new Dinerinfo();

        String bpd;

        diner.setPhonenumber( num);
        diner.setName(scrname);
        if(password.equals(password2)){
            bpd=password;
            diner.setPassword(bpd);
        }
        diner.save(new SaveListener<String>() {
            @Override
            public void done(String dine, BmobException e) {

            }
        });


        if (!password.equals(password2)){
            Toast.makeText(this,  "两次密码不相等，重新输入", Toast.LENGTH_SHORT).show();
        }
        //注册验证
        else if (!TextUtils.isEmpty(scrname)&& !TextUtils.isEmpty(num) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneCodes) ) {
            if (phoneCodes.equals(realCode)) {
                //将用户名和密码加入到数据库中
//                mDBOpenHelper.add(num, password,scrname);
                Intent intent2 = new Intent(this,Main_Login.class);
//                intent2.putExtra("num",num);
//                intent2.putExtra("password",password);
//                intent2.putExtra("scrname",scrname);
                startActivity(intent2);
                finish();
                Toast.makeText(this,  "验证通过，注册成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
        }
    }
}
