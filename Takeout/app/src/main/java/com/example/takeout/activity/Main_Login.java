package com.example.takeout.activity;
//2016051604121 周鹏刚
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeout.R;
import com.example.takeout.activity.bmob.Dinerinfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


//李楷 2016051604109 软件工程 2016级

public class Main_Login extends AppCompatActivity {
    // private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bmob.initialize(this, "cd37c8430268b5d553cffa32e339817d");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_login);
        ButterKnife.bind(this);

        // mDBOpenHelper = new DBOpenHelper(this);
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
                Intent intent = new Intent(this, Dinner_register.class);
                startActivity(intent);
                break;
            case R.id.loginactivity_login:
                blogin();
                break;
            case R.id.shop:
                Intent intent1 = new Intent(this, Seller_register.class);
                startActivity(intent1);
                break;
        }
    }

    //    public void login(){
//        String num = number.getText().toString().trim();
//        String passwords = password.getText().toString().trim();
//        String scrname = user.getText().toString().trim();
//        if (!TextUtils.isEmpty(num) && !TextUtils.isEmpty(passwords)) {
//            ArrayList<User> data = mDBOpenHelper.getAllData();
//            boolean match = false;
//            for(int i=0;i<data.size();i++) {
//                User user = data.get(i);
//                if (num.equals(user.getName()) && passwords.equals(user.getPassword())){
//                    match = true;
//                    break;
//                }else{
//                    match = false;
//                }
//            }
//
//            if (match) {
//                Intent intent=new Intent(this, Dinner_main.class);
//                startActivity(intent);
//                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();//
//                finish();
//            }else {
//                Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
//        }
//    }
    public void blogin(){

        final String num = number.getText().toString().trim();
        final String passwords = password.getText().toString().trim();
        String scrname = user.getText().toString().trim();
        BmobQuery<Dinerinfo> bmobQuery = new BmobQuery<Dinerinfo>();
        bmobQuery.addWhereEqualTo("phonenumber",num);
        bmobQuery.findObjects(new FindListener<Dinerinfo>() {
            @Override
            public void done(List<Dinerinfo> list, BmobException e) {
                if(e==null) {                  //查询成功
                    if(list.size()==1)
                    {
                        if( list.get(0).getPassword().equals(passwords)) {
                            Intent intent = new Intent(Main_Login.this, Dinner_main.class);

                            startActivity(intent);
//

                            Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                        System.out.println("查询失败：" + e.getMessage());


                    }
                }
            }
        });

    }
}
