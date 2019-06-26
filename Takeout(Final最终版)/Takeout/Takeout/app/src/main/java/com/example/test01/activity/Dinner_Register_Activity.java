package com.example.test01.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.test01.R;
import com.example.test01.activity.Activity_Fragment;
import com.example.test01.activity.Diner_Login_Activity;
import com.example.test01.bmob.Diner;
import com.example.test01.others.CircleImageView02;
import com.example.test01.others.Infor;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

//李楷 2016051604109 软件工程 2016级
//2016051604121 周鹏刚

public class Dinner_Register_Activity extends AppCompatActivity implements View.OnClickListener{

    EditText ed_diner_name;
    EditText ed_diner_tel;
    EditText ed_diner_password;
    EditText ed_diner_password2;
    CircleImageView02 iv_diner_logo;
    Button bt_diner_reg;

    String dinerName=null;
    String dinertel=null;
    String passWord=null;
    String passWord02=null;

    BitmapFactory.Options options;  //bitmap图片缩放
    Bitmap bitmap_01;
    String path;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diner_register_activity);
        Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");

        ed_diner_name=(EditText)findViewById(R.id.ed_diner_name);
        ed_diner_tel=(EditText)findViewById(R.id.ed_diner_tel);
        ed_diner_password=(EditText)findViewById(R.id.ed_diner_password);
        ed_diner_password2=(EditText)findViewById(R.id.ed_diner_password2);
        iv_diner_logo=(CircleImageView02) findViewById(R.id.iv_diner_logo);
        bt_diner_reg=(Button) findViewById(R.id.bt_diner_reg);

        iv_diner_logo.setOnClickListener(this);
        bt_diner_reg.setOnClickListener(this);

        options = new BitmapFactory.Options();
        options.inSampleSize=10;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registeractivity_back:
                Intent intent = new Intent(this, Diner_Login_Activity.class);
                startActivity(intent);
                break;
            case R.id.bt_diner_reg:
                dinerName= ed_diner_name.getText().toString();
                passWord = ed_diner_password.getText().toString();
                dinertel = ed_diner_tel.getText().toString();
                passWord02 = ed_diner_password2.getText().toString();
                if( dinerName.equals("")||passWord.equals("")||dinertel.equals("")||passWord02.equals("") ||path.equals("")){
                    Toast.makeText(getApplicationContext(), "请输入完整信息", Toast.LENGTH_SHORT).show();
                } else if(! passWord.equals(passWord02) ){
                    Toast.makeText(getApplicationContext(), "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    ff_diner_register();
                }
                break;
            case R.id.iv_diner_logo:
                Intent intent_01 = new Intent ( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult(intent_01, 1);
                break;
        }
    }

    public void ff_diner_register(){
        BmobQuery<Diner> bmobQuery = new BmobQuery<Diner>();
        bmobQuery.addWhereEqualTo("dinerTel",dinertel);
        bmobQuery.findObjects(new FindListener<Diner>() {
            @Override
            public void done(List<Diner> list, BmobException e) {
                if(e==null) {                  //查询成功
                    if(list.size()==0)
                    {
                        add_diner();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });
    }

    public void add_diner(){
        String picPath = path;
        final BmobFile bmobFile01 = new BmobFile(new File(picPath));
        bmobFile01.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    System.out.println("上传文件成功:" + bmobFile01.getFileUrl());

                    Diner diner= new Diner();
                    diner.setDinerName(dinerName);
                    diner.setDinerLogo(bmobFile01);
                    diner.setDinerTel(dinertel);
                    diner.setDinerPassWord(passWord);

                    diner.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Infor.dinertel=dinertel;  //保存信息
                                Infor.isLogin=true;
                                Infor.dinerName=dinerName;
                                Intent intent = new Intent(Dinner_Register_Activity.this, Diner_Activity_Fragment.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

                }else{
                    System.out.println("上传文件失败：" + e.getMessage());
                }
            }
            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case 1:                      //这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        path = cursor.getString(columnIndex);       //获取照片路径,后面需要判断

                        bitmap_01= BitmapFactory.decodeFile(path, options);
                        iv_diner_logo.setImageBitmap(bitmap_01);
                        cursor.close();
                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
