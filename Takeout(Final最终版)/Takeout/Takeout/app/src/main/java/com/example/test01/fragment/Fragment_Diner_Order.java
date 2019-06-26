package com.example.test01.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.test01.R;
import com.example.test01.activity.Activity_Diner_ShopFood;
import com.example.test01.bmob.Order;
import com.example.test01.bmob.Shop;
import com.example.test01.others.Infor;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.example.test01.others.Infor.dinertel;

public class Fragment_Diner_Order extends Fragment {
    View view;

    MyBaseAdapter mAdapter;
    ListView mListView;
    String names[];       //等于Shop里面的shopNamw
    String tels[];
    String orderContent[];
    String orderstate[];
    String orderId[];



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_diner_order,container,false);

        mListView= (ListView) view.findViewById(R.id.listView_diner_order);
        mAdapter=new MyBaseAdapter();

        ff_query_order();

        return view;
    }


    public void ff_query_order()
    {
        BmobQuery<Order> bmobQuery = new BmobQuery<Order>();
        bmobQuery.addWhereEqualTo("dinerTel", Infor.dinertel);                   //
        bmobQuery.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> object, BmobException e) {
                if(e==null) {                  //查询成功


                    System.out.println("查询成功    "+object.size());
                    if(object.size()>0)
                    {
                        names = new String[object.size()];              //定义names数组 长度
                        tels = new String[object.size()];
                        orderContent = new String[object.size()];
                        orderstate = new String[object.size()];
                        orderId = new String[object.size()];

                        for ( int  i = 0; i < object.size(); i++)                 //遍历
                        {
                            names[i] = object.get(i).getShopName();        //给names数组 赋值
                            tels[i] = object.get(i).getShopTel();
                            orderContent[i]=object.get(i).getOrderContent();
                            orderstate[i]=object.get(i).getOrderState();
                            orderId[i]=object.get(i).getObjectId();
                        }
                        mListView.setAdapter(mAdapter);
                    }
                }else{
                    System.out.println("查询失败：" + e.getMessage());
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
            View view= View.inflate(getApplicationContext(),R.layout.list_item_diner_order,null);

            TextView tv_order_shopname=(TextView)view.findViewById(R.id.tv_order_shopname);
            TextView tv_order_food=(TextView)view.findViewById(R.id.tv_order_food);
            final TextView tv_order_state=(TextView) view.findViewById(R.id.tv_order_state);
            TextView tv_orderId=(TextView) view.findViewById(R.id.tv_orderId);
            Button bt_order_comfirm=(Button) view.findViewById(R.id.bt_order_comfirm);

            tv_order_shopname.setText(names[position]);
            tv_order_food.setText(orderContent[position]);
            tv_order_state.setText(orderstate[position]);
            tv_orderId.setText(orderId[position]);

            bt_order_comfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( orderstate[position].equals( "2") )
                    {
                        Order order = new Order();
                        order.setOrderState("3");      //订单状态3 : 点餐者确认收货
                        order.update( orderId[position] , new UpdateListener() {     //获取的订单id目前为 ObjectId
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    tv_order_state.setText("3");
                                    Toast.makeText(getApplicationContext(), "商家接单成功", Toast.LENGTH_SHORT).show();

                                }else{
                                    //toast("更新失败：" + e.getMessage());
                                }
                            }

                        });
                    }
                }

            });



            return view;
        }
    }
}
