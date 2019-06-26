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
import com.example.test01.bmob.Food;
import com.example.test01.others.Infor;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;


public class Activity_Add_Food extends AppCompatActivity  implements View.OnClickListener {
    private ImageView iv_back;
    private EditText ed_food_name;
    private EditText ed_food_price;
    private TextView tv_commit;
    private ImageView iv_food_pic;

    BitmapFactory.Options options;  //bitmap图片缩放
    Bitmap bitmap_01;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        iv_back =(ImageView)findViewById(R.id.iv_back);
        ed_food_name =(EditText)findViewById(R.id.ed_food_name);
        ed_food_price = (EditText)findViewById(R.id.ed_food_price);
        tv_commit = (TextView)findViewById(R.id.tv_commit);
        iv_food_pic=(ImageView)findViewById(R.id.iv_food_pic);

        iv_back.setOnClickListener(this);
        iv_food_pic.setOnClickListener(this);
        tv_commit.setOnClickListener(this);

        options = new BitmapFactory.Options();
        options.inSampleSize=10;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                Intent intent= new Intent(this, Activity_Fragment.class);
                setResult(1,intent);
                finish();
                break;
            case R.id.tv_commit:
                final String foodName;
                final String foodPrice;
                final BmobFile foodPic;
                foodName = ed_food_name.getText().toString();
                foodPrice = ed_food_price.getText().toString();
                if( foodPrice.equals("") || foodName.equals("") || path.equals("") ){
                    Toast.makeText(this,"请输入完整信息   ！",Toast.LENGTH_SHORT).show();
                }else {

                    String picPath = path;
                    final BmobFile bmobFile01 = new BmobFile(new File(picPath));
                    bmobFile01.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                //bmobFile.getFileUrl()--返回的上传文件的完整地址
                                System.out.println("上传文件成功:" + bmobFile01.getFileUrl());

                                Food food = new Food();
                                food.setFoodName(foodName);
                                food.setFoodPrice(foodPrice);
                                food.setShopTel(Infor.tel);           //从本地提取
                                food.setFoodPic(bmobFile01);     //上传图片
                                food.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if(e==null){
                                            Intent i=new Intent();
                                            setResult(1,i);
                                            finish();
                                        }else{
                                            Toast.makeText(Activity_Add_Food.this,"新增菜品失败",Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.iv_food_pic:
                Intent intent_01 = new Intent ( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult(intent_01, 1);
                break;
        }
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
                        iv_food_pic.setImageBitmap(bitmap_01);
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
