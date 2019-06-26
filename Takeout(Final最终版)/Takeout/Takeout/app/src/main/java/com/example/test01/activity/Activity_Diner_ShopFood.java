package com.example.test01.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test01.R;
import com.example.test01.bmob.Food;
import com.example.test01.bmob.Order;
import com.example.test01.others.Infor;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class Activity_Diner_ShopFood extends AppCompatActivity {

    TextView tv_shopName,tv_tel,tv_address;

    MyBaseAdapter mAdapter;
    ListView mListView;

    String names[];
    String prices[];
    int int_prices[];
    String tel,shopName,shopAddress;

    public Bitmap bitmaps[];
    BmobFile bmobfiles[];
    BitmapFactory.Options options;  //bitmap图片缩放

    /////////////////////////////////////////////////////
    int money=0,number=0;
    Button bt_goto_fuqian;
    TextView tv_number,tv_money;
    int number01[];      //记录每个菜品的个数
    String str_food="";        //记录订购的菜单，传到服务器



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diner_shopfood);
        //Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");

        tv_shopName=(TextView)findViewById(R.id.tv_shopName);
        tv_tel=(TextView)findViewById(R.id.tv_tel);
        tv_address=(TextView)findViewById(R.id.tv_address);

        //下面购物车
        tv_number=(TextView) findViewById(R.id.tv_number);
        tv_money=(TextView) findViewById(R.id.tv_money);
        bt_goto_fuqian=(Button) findViewById(R.id.bt_goto_fuqian);
        bt_goto_fuqian.setOnClickListener(new MyButton() );

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

                        int_prices=new int[object.size()];   //转换价格
                        number01=new int[object.size()];

                        for (int i = 0; i < object.size(); i++)                 //遍历
                        {
                            names[i] = object.get(i).getFoodName();        //给names数组 赋值
                            prices[i] = object.get(i).getFoodPrice();

                            int_prices[i]=0;
                            int_prices[i] = Integer.parseInt( prices[i] );   //转换类型
                            number01[i] = 0;

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


    public void ff_add_order( String dinerTel,String shopTel,String orderContent,String shopName,String dinerName)
    {
        Order order = new Order();
        order.setDinerTel( dinerTel );
        order.setDinerName( dinerName );
        order.setShopTel( shopTel );
        order.setShopName(shopName);
        order.setOrderState("1");//订单状态1：已提交订单，等待接单
        order.setOrderContent( orderContent );
        order.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(getApplicationContext(), "订单提交成功", Toast.LENGTH_SHORT).show();
                    System.out.println("顾客上传订单成功，返回objectId为："+objectId);
                }else{
                    System.out.println("创建数据失败：" + e.getMessage());
                }
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
            View view= View.inflate(getApplicationContext(),R.layout.list_item_diancan,null);

            TextView list_name=(TextView)view.findViewById(R.id.list_name);
            TextView list_money=(TextView)view.findViewById(R.id.list_money);
            ImageView list_add=(ImageView) view.findViewById(R.id.list_add);
            ImageView list_image=(ImageView) view.findViewById(R.id.list_image);
            final ImageView list_reduce=(ImageView) view.findViewById(R.id.list_reduce);
            final TextView list_nubmer=(TextView)view.findViewById(R.id.list_number);

            list_name.setText(names[position]);
            list_money.setText("￥ "+prices[position]);
            list_image.setImageBitmap( bitmaps[position] );



            list_add.setOnClickListener(new View.OnClickListener() {           //增加
                @Override
                public void onClick(View v) {
                    number01[position]++;                      //单个菜品
                    number++;                                  //总和
                    money=money+int_prices[position];             //总价
                    list_reduce.setVisibility(View.VISIBLE);
                    list_nubmer.setVisibility(View.VISIBLE);
                    list_nubmer.setText(""+number01[position]); //单个菜品
                    tv_number.setText(""+number);              //总和
                    tv_money.setText("￥ "+money);                //总价
                }
            });

            list_reduce.setOnClickListener(new View.OnClickListener() {       //减少
                @Override
                public void onClick(View v) {
                    number01[position]--;
                    number--;
                    money=money-int_prices[position];
                    tv_number.setText(""+number);
                    tv_money.setText("￥ "+money);

                    if(number01[position]<1) {
                        list_reduce.setVisibility(View.INVISIBLE);
                        list_nubmer.setVisibility(View.INVISIBLE); }
                    else {
                        list_nubmer.setText("" + number01[position]); }
                }
            });
            //防止listview滑动时，滑出屏幕的item数据归零
            if(number01[position]>0)
            {
                list_reduce.setVisibility(View.VISIBLE);
                list_nubmer.setVisibility(View.VISIBLE);
                list_nubmer.setText("" + number01[position]);
            }

            return view;
        }
    }

    class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_goto_fuqian:
                    if (number > 0)                                        // 没点餐，不能付钱
                    {
                        for (int i = 0; i < names.length; i++)               // 从头到尾遍历，看点了哪些菜
                        {
                            if (number01[i] > 0)                       // 只记录点的菜
                            {
                                for (int j = 0; j < number01[i]; j++)        // 一个菜品点了多份，记录就有多份
                                {
                                    str_food = str_food + " " + names[i];
                                }
                            }
                        }
                        System.out.println(str_food);
                        if (Infor.isLogin == true)                    //判断是否登录
                        {
                            ff_add_order( Infor.dinertel , tel , str_food ,shopName,Infor.dinerName);     //上传订单，点餐者电话、商家电话、点餐内容
                            money = 0;                                       //结算后，清洁界面
                            number = 0;
                            tv_number.setText("" + 0);
                            tv_money.setText("￥ " + 0);
                            for (int m = 0; m < names.length; m++) {
                                number01[m] = 0;
                            }
                            mListView.setAdapter(mAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "请登录", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "你还未购物", Toast.LENGTH_SHORT).show();
                    }
                    str_food = "";
                    break;
            }
        }
    }




}
