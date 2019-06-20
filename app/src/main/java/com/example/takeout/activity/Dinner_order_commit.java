package com.example.takeout.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.takeout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//李楷 2016051604109 软件工程 2016级

public class Dinner_order_commit extends AppCompatActivity {
    String[] names={"半只鸡","秘制鸡腿","烤翅"};
    String[] price={"20元","7元","11元"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop_pay);
        ButterKnife.bind(this);
        MymenuAdapters mymenuAdapters=new MymenuAdapters();
        mlist.setAdapter(mymenuAdapters);
    }
    @BindView(R.id.commits)
    TextView commit;
    @BindView(R.id.mback)
    ImageView mback;
    @BindView(R.id.new_address)
    Button add_adsress;
    @BindView(R.id.listview)
    ListView mlist;
    @OnClick({
            R.id.mback,
            R.id.commits,
            R.id.new_address,
    })
    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.new_address:
                Intent intent=new Intent(this, AddNewadress.class);
                startActivity(intent);
                finish();
                break;
            case R.id.commits:

                break;
            case R.id.mback:
                Intent intent1=new Intent(this, Dinner_shops.class);
                startActivity(intent1);
                finish();
                break;
        }

    }

    public class MymenuAdapters extends BaseAdapter
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
            @SuppressLint("ViewHolder")
            View view= View.inflate(Dinner_order_commit.this, R.layout.activity_user_shop_pay_list,null);
            TextView menu=(TextView)view.findViewById(R.id.order);
            TextView prices=(TextView)view.findViewById(R.id.price);
            //ImageView list_add=(ImageView) view.findViewById(R.id.list_add)
            menu.setText(names[position]);
            prices.setText(price[position]);
            return view;
        }
    }
}
