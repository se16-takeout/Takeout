package com.example.test01.others;

import android.graphics.Bitmap;

import cn.bmob.v3.datatype.BmobFile;

public class Infor {
    public static String tel=null;
    public static String shopName=null;
    public static boolean isLogin=false;
    public static Bitmap shopLogo=null;
    public static String dinertel=null;
    public  static String dinerName=null;














    /*

    bmob_update_button_cancel_bg_normal


    public void  ff_query_food()
    {
        System.out.println(tel );
        BmobQuery<Food> bmobQuery08 = new BmobQuery<Food>();
        //bmobQuery01.addWhereEqualTo("foodName",tel);
        bmobQuery08.addWhereNotEqualTo("tel","");                   //查询商家电话不等于空的
        System.out.println(tel );
        bmobQuery08.findObjects(new FindListener<Food>() {
            @Override
            public void done(List<Food> object, BmobException e) {
                if(e==null) {                  //查询成功
                    System.out.println("查询成功：" + e.getMessage());
                    if(object.size()>0)
                    {
                        names = new String[object.size()];              //定义names数组 长度
                        prices = new String[object.size()];

                        for (int i = 0; i < object.size(); i++)                 //遍历
                        {
                            names[i] = object.get(i).getFoodName();        //给names数组 赋值
                            prices[i] = object.get(i).getPrice();
                        }
                        mListView.setAdapter(mAdapter);
                    }
                }else{
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });
    }




    <TextView
            android:id="@+id/tv_address"
            android:layout_width="wrap_content"
            android:layout_height="50dp"
            android:layout_gravity="center_horizontal"
            android:text="商家地址"
            android:textSize="20dp"/>





            public void sl_update()
    {

        String picPath = path;
        final BmobFile bmobFile01 = new BmobFile(new File(picPath));
        bmobFile01.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    System.out.println("上传文件成功:" + bmobFile01.getFileUrl());



                    System.out.println(tel);
                    BmobQuery<Shop> bmobQuery02 = new BmobQuery<Shop>();
                    bmobQuery02.addWhereEqualTo("tel",tel);
                    bmobQuery02.findObjects(new FindListener<Shop>() {
                        @Override
                        public void done(List<Shop> list, BmobException e) {
                            if(e==null) {                  //查询成功         list.get(0).getObjectId();
                                Toast.makeText(getApplicationContext(), "查询成功  "+list.size(), Toast.LENGTH_SHORT).show();



                                Shop s = new Shop();
                                s.setShopName(new_shopName);       //上传新的数据
                                s.update( list.get(0).getObjectId() , new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e==null){                            //页面跳转
                                            Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();

                                            Infor.shopName=new_shopName;
                                            Intent intent02=new Intent();
                                            setResult(1,intent02);
                                            finish();
                                        }else{
                                            Toast.makeText(Activity_ShopInfo.this,"更新失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                });





                            }else{
                                System.out.println("查询失败：" + e.getMessage());
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






     */
}


