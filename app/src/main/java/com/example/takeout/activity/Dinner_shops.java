package com.example.takeout.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.takeout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dinner_shops extends AppCompatActivity {
    String[] names={"半只鸡","秘制鸡腿","烤翅"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shop);
        ButterKnife.bind(this);

        MymenuAdapters adapter=new MymenuAdapters();
        mlist.setAdapter(adapter);
    }
    @BindView(R.id.id_backs)
    ImageView backs;
    @BindView(R.id.id_pay)
    TextView pays;
    @BindView(R.id.listview)
    ListView mlist;

    @OnClick({
            R.id.id_backs,
            R.id.id_pay,
    })
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.id_pay:
                Intent intent =new Intent(this, Dinner_order_commit.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.id_backs:
                Intent intent1 =new Intent(this, Dinner_main.class);
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
            View view= View.inflate(Dinner_shops.this, R.layout.activity_user_shop_menu,null);
            TextView menu=(TextView)view.findViewById(R.id.menu_names);
            //ImageView list_add=(ImageView) view.findViewById(R.id.list_add)
            menu.setText(names[position]);
            return view;
        }
    }
}
