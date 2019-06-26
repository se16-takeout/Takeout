package com.example.test01.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class Activity_ShopInfo extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back_mine;
    private CircleImageView02 iv_photo;
    private EditText ed_shop_name;
    private TextView tv_save_info;
    String old_shopName;
    String new_shopName;
    String tel;

    BitmapFactory.Options options;  //bitmap图片缩放
    Bitmap bitmap_01;
    String path="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopinfo);
        Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");

        iv_back_mine = (ImageView)findViewById(R.id.iv_back_mine) ;
        iv_photo =(CircleImageView02) findViewById(R.id.iv_photo);
        ed_shop_name =(EditText)findViewById(R.id.ed_shop_name);
        tv_save_info = (TextView)findViewById(R.id.tv_save_info);

        Intent intent01 = getIntent();
        //old_shopName = intent01.getStringExtra("extra_shopName");
        old_shopName=Infor.shopName;
        tel = Infor.tel;


        iv_back_mine.setOnClickListener(this);
        iv_photo.setOnClickListener(this);
        ed_shop_name.setOnClickListener(this);
        tv_save_info.setOnClickListener(this);

        options = new BitmapFactory.Options();
        options.inSampleSize=10;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back_mine:
                    Intent intent= new Intent(this, Activity_Fragment.class);
                    setResult(1,intent);
                    finish();
                    break;

            case R.id.iv_photo:
                Intent intent_01 = new Intent ( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult(intent_01, 2);
                    break;

            case R.id.tv_save_info:
                new_shopName = ed_shop_name.getText().toString();
                if(new_shopName.equals("") && path.equals("") ) {
                    Toast.makeText(getApplicationContext(), "无效操作", Toast.LENGTH_SHORT).show();
                }else {
                    if( new_shopName.equals("") )
                    {
                        new_shopName=old_shopName;
                    }
                    ff_query();           //上传信息     先查询，再更新，     如果要更新头像， 先查询，再上传文件，最后更新，
                }

        }
    }

    public void ff_query()
    {
        System.out.println(tel);
        BmobQuery<Shop> bmobQuery02 = new BmobQuery<Shop>();
        bmobQuery02.addWhereEqualTo("shopTel",tel);
        bmobQuery02.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
                if(e==null) {                    //查询成功         list.get(0).getObjectId();
                    Toast.makeText(getApplicationContext(), "查询成功  "+list.size(), Toast.LENGTH_SHORT).show();
                    System.out.println("查询成功  "+list.size());

                    if(path.equals("") )       //不用更新头像
                    {
                        ff_updata( list.get(0).getObjectId() );
                    }else {                    //需要更新头像,
                        ff_sctp_updata( list.get(0).getObjectId() );
                    }
                }else{
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });
    }


    public void ff_sctp_updata( final String ObjiectID )     //传递查询到的ObjectID
    {
        String picPath = path;
        final BmobFile bmobFile01 = new BmobFile(new File(picPath));
        bmobFile01.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    System.out.println("上传文件成功:" + bmobFile01.getFileUrl());


                    Shop s = new Shop();
                    s.setShopName(new_shopName);       //上传新的数据
                    s.setShopLogo( bmobFile01 );
                    s.update( ObjiectID , new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){                            //页面跳转
                                Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();
                                System.out.println("更新成功");

                                Infor.shopName=new_shopName;
                                Infor.shopLogo = BitmapFactory.decodeFile(path);                        //把头像保存到Infor里
                                Intent intent02=new Intent();
                                setResult(1,intent02);
                                finish();
                            }else{
                                Toast.makeText(Activity_ShopInfo.this,"更新失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
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

    public void ff_updata( String ObjectID)
    {
        Shop s = new Shop();
        s.setShopName(new_shopName);       //上传新的数据
        s.update( ObjectID , new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){                            //页面跳转
                    Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();
                    System.out.println("更新成功");

                    Infor.shopName=new_shopName;
                    Intent intent02=new Intent();
                    setResult(1,intent02);
                    finish();
                }else{
                    Toast.makeText(Activity_ShopInfo.this,"更新失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        });
    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case 2:                      //这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
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
                        iv_photo.setImageBitmap(bitmap_01);
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
