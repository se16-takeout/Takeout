package com.example.takeout.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.takeout.R;
import com.example.takeout.activity.bmob.Order;

import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static cn.bmob.v3.Bmob.getApplicationContext;


public class FragmentOrder extends Fragment {
     ListView mListView;

   View view;
   MyBaseAdapter mAdapter;

    String shopames[];       //等于Shop里面的shopNamw
    String tels[];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_user_main_order, container, false);
//        ButterKnife.bind(this, view);
        mListView = (ListView)view.findViewById(R.id.odlv);

        mAdapter=new MyBaseAdapter();
        find_query_order();

        return view;
    }


    public void find_query_order(){

    BmobQuery<Order> bmobQuery = new BmobQuery<Order>();
    bmobQuery.addWhereNotEqualTo("tel","");
    bmobQuery.findObjects(new FindListener<Order>() {
        @Override
        public void done(List<Order> list, BmobException e) {
            if(e==null) {   //查询成功
                System.out.println("shop里面food查询成功");
                if(list.size()>0)
                {
                    shopames = new String[list.size()];              //定义names数组 长度
                    tels = new String[list.size()];


                for (int i = 0; i < list.size(); i++)                 //遍历
                {
                  shopames[i] = list.get(i).getShopname();        //给names数组 赋值
                    tels[i] = list.get(i).getTel();

                }
               mListView.setAdapter(mAdapter);
            }
        }else{
            System.out.println("查询失败：" + e.getMessage());
        }
    }
});
    }

//    public void find_listView_click(){
//        odlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent01=new Intent(getApplicationContext(),.class);
//                intent01.putExtra("extra_tel",tels[i]);    //传递数据
//                intent01.putExtra("extra_shopName",shopames[i]);
//
//                startActivity(intent01);
//            }
//        });
//    }

    public class MyBaseAdapter extends BaseAdapter
    {
        public int getCount()
        {
            return shopames.length;
        }
        public Object getItem(int position)
        {
            return shopames[position];
        }
        public long getItemId(int position)
        {
            return position;
        }
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view= View.inflate(getApplicationContext(),R.layout.layout_order_listitem,null);

            TextView list_tv_shop=(TextView)view.findViewById(R.id.tv_shop);
            //ImageView list_add=(ImageView) view.findViewById(R.id.list_add);

            list_tv_shop.setText(shopames[position]);


            return view;
        }
    }
}
