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

public class AddNewadress extends AppCompatActivity {
    String[] names={"小白龙"};
    String[] phone={"10086"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_address_list);
        ButterKnife.bind(this);

        MyaddressAdapters myaddressAdapters=new MyaddressAdapters();
        mlist.setAdapter(myaddressAdapters);
    }
    @BindView(R.id.add_address)
    TextView add;
    @BindView(R.id.sback)
    ImageView back;
    @BindView(R.id.listview)
    ListView mlist;
    @OnClick({
            R.id.sback,
            R.id.add_address,
    })
    public void onClick(View view){

        switch (view.getId())
        {
            case R.id.add_address:
                Intent intent=new Intent(this, Addadress.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sback:
                Intent intent1=new Intent(this, Dinner_order_commit.class);
                startActivity(intent1);
                finish();
                break;
        }

    }

    public class MyaddressAdapters extends BaseAdapter
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
            View view= View.inflate(AddNewadress.this, R.layout.pay_adress_list_add,null);
            TextView menu=(TextView)view.findViewById(R.id.name);
            TextView prices=(TextView)view.findViewById(R.id.phone);
            //ImageView list_add=(ImageView) view.findViewById(R.id.list_add)
            menu.setText(names[position]);
            prices.setText(phone[position]);
            return view;
        }
    }
}
