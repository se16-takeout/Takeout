package com.example.test01.activity;
//2016051604121 周鹏刚
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.test01.activity.Dinner_Register_Activity;
import com.example.test01.R;
import com.example.test01.bmob.Diner;
import com.example.test01.bmob.Shop;
import com.example.test01.others.Infor;

import java.util.List;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import androidx.appcompat.app.AppCompatActivity;
//李楷 2016051604109 软件工程 2016级

public class Diner_Login_Activity extends AppCompatActivity implements View.OnClickListener{

    EditText ed_diner_login;
    EditText ed_loginactivity_password;
    Button bt_loginactivity_login;
    Button bt_diner_register;

    String dinerTel;
    String dinerPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diner_login_activity);
        Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");

        ed_diner_login=(EditText)findViewById(R.id.ed_diner_login);
        ed_loginactivity_password= (EditText)findViewById(R.id.ed_loginactivity_password);
        bt_loginactivity_login= (Button)findViewById(R.id.bt_loginactivity_login);
        bt_diner_register= (Button)findViewById(R.id.bt_diner_register);

        bt_loginactivity_login.setOnClickListener(this);
        bt_diner_register.setOnClickListener(this);

    }

    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.bt_diner_register:
                Intent intent = new Intent(this, Dinner_Register_Activity.class);
                startActivity(intent);
                break;
            case R.id.bt_loginactivity_login:
                dinerTel = ed_diner_login.getText().toString();
                dinerPassWord = ed_loginactivity_password.getText().toString();

                if( dinerTel.equals("") ){
                    Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if( dinerPassWord.equals("") ){
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                } else{
                    blogin();
                }
                break;

        }
    }

    public void blogin(){
        BmobQuery<Diner> bmobQuery = new BmobQuery<Diner>();
        bmobQuery.addWhereEqualTo("dinerTel",dinerTel);
        bmobQuery.findObjects(new FindListener<Diner>() {
            @Override
            public void done(List<Diner> list, BmobException e) {
                if(e==null) {                  //查询成功
                    if(list.size()==1)
                    {
                        if( list.get(0).getDinerPassWord().equals(dinerPassWord) )      //密码相同
                        {
                            Infor.dinertel=dinerTel;  //保存信息
                            Infor.isLogin=true;
                            Infor.dinerName=list.get(0).getDinerName();
                            Intent intent03 =new Intent(Diner_Login_Activity.this, Diner_Activity_Fragment.class);
                            startActivity(intent03);
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });

    }
}
