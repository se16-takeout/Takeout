package com.example.test01.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test01.R;
import com.example.test01.bmob.Food;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

public class Activity_Shop_To_Food extends AppCompatActivity {

    TextView tv_shopName,tv_tel,tv_address;

    MyBaseAdapter mAdapter;
    ListView mListView;

    String names[];
    String prices[];
    String tel,shopName,shopAddress;

    public Bitmap bitmaps[];
    BmobFile bmobfiles[];
    BitmapFactory.Options options;  //bitmap图片缩放



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_to_food);
        //Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");

        tv_shopName=(TextView)findViewById(R.id.tv_shopName);
        tv_tel=(TextView)findViewById(R.id.tv_tel);
        tv_address=(TextView)findViewById(R.id.tv_address);

        mListView= (ListView) findViewById(R.id.listView_shop_food);
        mAdapter=new MyBaseAdapter();

        Intent intent01 = getIntent();      //接收页面跳转的数据
        tel = intent01.getStringExtra("extra_tel");
        shopName = intent01.getStringExtra("extra_shopName");
        shopAddress = intent01.getStringExtra("extra_shopAddress");
        tv_shopName.setText(shopName);
        tv_tel.setText(tel);
        tv_address.setText(shopAddress);

        options = new BitmapFactory.Options();
        options.inSampleSize=10;

        ff_query_shop_food();
    }


    public void ff_query_shop_food()
    {
        BmobQuery<Food> bmobQuery = new BmobQuery<Food>();
        bmobQuery.addWhereEqualTo("shopTel",tel);
        bmobQuery.findObjects(new FindListener<Food>() {
            @Override
            public void done(List<Food> object, BmobException ee) {
                System.out.println("shop里面food查询成功");
                if(ee==null) {                  //查询成功
                    if(object.size()>0)
                    {
                        names = new String[object.size()];              //定义names数组 长度
                        prices = new String[object.size()];
                        bitmaps=new Bitmap[object.size()];
                        bmobfiles=new BmobFile[object.size()];

                        for (int i = 0; i < object.size(); i++)                 //遍历
                        {
                            names[i] = object.get(i).getFoodName();        //给names数组 赋值
                            prices[i] = object.get(i).getFoodPrice();
                            bmobfiles[i]=object.get(i).getFoodPic();
                            ff_DownLoad( bmobfiles[i] , i );
                        }
                    }
                }else{
                    System.out.println("查询失败 ：" + ee.getMessage());
                }
            }
        });
    }


    public void ff_DownLoad(BmobFile bmobFile,final int i)
    {
        File saveFile = new File("storage/emulated/0/bmxz620", bmobfiles[i].getFilename());
        bmobFile.download(saveFile, new DownloadFileListener() {
            @Override
            public void onStart() {
                System.out.println("开始下载...");
            }
            @Override
            public void done(String savePath,BmobException e) {
                if(e==null){

                    System.out.println("下载成功,保存路径:，应该是tpxz_slt     "+savePath);
                    bitmaps[i] = BitmapFactory.decodeFile(savePath, options);
                    if(i==names.length-1)
                        mListView.setAdapter(mAdapter);

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





    public class MyBaseAdapter extends BaseAdapter
    {
        public int getCount()
        {
            return names.length;
        }
        public Object getItem(int position)
        {
            return names[position];
        }
        public long getItemId(int position)
        {
            return position;
        }
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view= View.inflate(getApplicationContext(),R.layout.list_item_shop_food,null);

            TextView tv_food=(TextView)view.findViewById(R.id.tv_food);
            TextView tv_price=(TextView)view.findViewById(R.id.tv_price);
            ImageView iv_food=(ImageView) view.findViewById(R.id.iv_food);

            tv_food.setText(names[position]);
            tv_price.setText(prices[position]);
            iv_food.setImageBitmap( bitmaps[position] );

            return view;
        }
    }




}
