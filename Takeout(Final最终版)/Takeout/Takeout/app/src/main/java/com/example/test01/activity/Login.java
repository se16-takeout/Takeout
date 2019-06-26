package com.example.test01.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test01.R;
import com.example.test01.bmob.Shop;
import com.example.test01.others.Infor;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

public class Login extends AppCompatActivity {

    EditText ed_userName,ed_passWord;
    Button bt_login,bt_go_register;
    String userName=null;
    String passWord=null;

    BmobFile bmobFile;   //保存用户头像

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");

        ed_userName=(EditText) findViewById(R.id.ed_userName);
        ed_passWord=(EditText) findViewById(R.id.ed_passWord);
        bt_login=(Button) findViewById(R.id.bt_login);
        bt_go_register=(Button) findViewById(R.id.bt_go_register);

        bt_login.setOnClickListener(new MyButton() );
        bt_go_register.setOnClickListener(new MyButton() );

    }

    public void ff_login()
    {
        BmobQuery<Shop> bmobQuery = new BmobQuery<Shop>();
        bmobQuery.addWhereEqualTo("shopTel",userName);
        bmobQuery.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
                if(e==null) {                  //查询成功
                    if(list.size()==1)
                    {
                        if( list.get(0).getShopPassWord().equals(passWord) )      //密码相同
                        {
                            if( list.get(0).getShopLogo()==null ) {     //判断账号信息是否全面，因为登录成功要保存头像等等
                                Toast.makeText(getApplicationContext(), "登录失败,账号信息不全。如:无头像", Toast.LENGTH_SHORT).show();
                            }else {
                                Infor.tel = userName;  //保存信息
                                Infor.isLogin = true;
                                Infor.shopName = list.get(0).getShopName();
                                bmobFile = list.get(0).getShopLogo();    //得到头像
                                ff_downLoad(bmobFile);
                                //Intent intent03 =new Intent(Login.this, Activity_Fragment.class);
                                //startActivity(intent03);
                            }
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });
    }


    public void ff_downLoad(BmobFile bmobFile)
    {
        File saveFile = new File("storage/emulated/0/bmxz620", bmobFile.getFilename());
        bmobFile.download(saveFile, new DownloadFileListener() {
            @Override
            public void onStart() {
                System.out.println("开始下载...");
            }
            @Override
            public void done(String savePath,BmobException e) {
                if(e==null){

                    System.out.println("下载成功,保存路径:，应该是tpxz_slt     "+savePath);
                    Infor.shopLogo = BitmapFactory.decodeFile(savePath);                        //把头像保存到Infor里面

                    Intent intent03 =new Intent(Login.this, Activity_Fragment.class);
                    startActivity(intent03);

                }else{
                    System.out.println("下载失败："+e.getErrorCode()+","+e.getMessage());
                }
            }
            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob","下载进度："+value+","+newworkSpeed);
            }
        });
    }



    class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_login:
                    userName = ed_userName.getText().toString();
                    passWord = ed_passWord.getText().toString();

                    if( userName.equals("") ){
                        Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                    } else if( passWord.equals("") ){
                        Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else{
                        ff_login();
                    }
                    break;
                case R.id.bt_go_register:
                    Intent intent02 = new Intent(Login.this, Register.class);
                    startActivity(intent02);
                    break;

            }
        }
    }


}
