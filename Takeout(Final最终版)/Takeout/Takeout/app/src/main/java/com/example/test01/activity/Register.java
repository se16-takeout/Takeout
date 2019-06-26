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
import com.example.test01.bmob.Shop;
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

public class Register extends AppCompatActivity {

    private EditText ed_shopName;
    private EditText ed_tel;
    private EditText ed_address;
    private EditText ed_passWord;
    private EditText ed_passWord02;
    private CircleImageView02 iv_log;
    private Button bt_register;

    String shopName=null;
    String tel=null;
    String address=null;
    String passWord=null;
    String passWord02=null;

    BitmapFactory.Options options;  //bitmap图片缩放
    Bitmap bitmap_01;
    String path=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
        Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");


        ed_shopName = (EditText)findViewById(R.id.ed_shopName);
        ed_tel = (EditText)findViewById(R.id.ed_tel);
        ed_address = (EditText)findViewById(R.id.ed_address);
        ed_passWord = (EditText)findViewById(R.id.ed_passWord);
        ed_passWord02 = (EditText)findViewById(R.id.ed_passWord02);
        bt_register = (Button)findViewById(R.id.bt_register);
        iv_log = (CircleImageView02)findViewById(R.id.iv_logo);

        bt_register.setOnClickListener(new MyButton() );
        iv_log.setOnClickListener(new MyButton() );

        options = new BitmapFactory.Options();
        options.inSampleSize=10;

    }
    public void ff_register(){
        BmobQuery<Shop> bmobQuery = new BmobQuery<Shop>();
        bmobQuery.addWhereEqualTo("shopTel",tel);
        bmobQuery.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
                if(e==null) {                  //查询成功
                    if(list.size()==0)
                    {
                        add_shop();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });
    }

    class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_register:
                    shopName = ed_shopName.getText().toString();
                    passWord = ed_passWord.getText().toString();
                    tel = ed_tel.getText().toString();
                    address = ed_address.getText().toString();
                    passWord02 = ed_passWord02.getText().toString();
                    if( shopName.equals("")||passWord.equals("")||tel.equals("")||address.equals("")||passWord02.equals("") ||path.equals("")){
                        Toast.makeText(getApplicationContext(), "请输入完整信息", Toast.LENGTH_SHORT).show();
                    } else if(! passWord.equals(passWord02) ){
                        Toast.makeText(getApplicationContext(), "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    } else {
                        ff_register();
                    }
                    break;
                case R.id.iv_logo:
                    Intent intent_01 = new Intent ( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                    startActivityForResult(intent_01, 1);
                    break;
            }
        }
    }
    public void add_shop()
    {

        String picPath = path;
        final BmobFile bmobFile01 = new BmobFile(new File(picPath));
        bmobFile01.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    System.out.println("上传文件成功:" + bmobFile01.getFileUrl());

                    Shop shop = new Shop();
                    shop.setShopName(shopName);
                    shop.setShopLogo(bmobFile01);
                    shop.setShopAddress(address);
                    shop.setShopTel(tel);
                    shop.setShopPassWord(passWord);
                    //shop.shopAddress="defqfe";

                    shop.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Infor.tel=tel;  //保存信息
                                Infor.isLogin=true;
                                Infor.shopName=shopName;
                                Infor.shopLogo = BitmapFactory.decodeFile(path);                        //把头像保存到Infor里面
                                Intent intent = new Intent(Register.this, Activity_Fragment.class);
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
                        iv_log.setImageBitmap(bitmap_01);
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
