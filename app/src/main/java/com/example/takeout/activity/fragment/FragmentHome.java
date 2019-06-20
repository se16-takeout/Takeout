package com.example.takeout.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.takeout.R;
import com.example.takeout.activity.Dinner_shops;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentHome extends Fragment{

    String[] names={"肯德基","碗碗菜","烤肉饭","水果店","火锅店","奶茶店","串串香","鸡公煲"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_user_main_frament, container, false);
        ButterKnife.bind(this, view);
        MyBaseAdapter adapter=new MyBaseAdapter();
        mlist.setAdapter(adapter);
        return view;
    }

    @BindView(R.id.fun1)
    LinearLayout fun1;
    @BindView(R.id.listview)
    ListView mlist;
    @OnClick({
            R.id.fun1,
    })

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fun1:
                Intent intent1=new Intent(getActivity(), Dinner_shops.class);
                startActivity(intent1);
                break;
        }
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
            @SuppressLint("ViewHolder")
            View view= View.inflate(getActivity(), R.layout.activity_user_main_frament_shoplist,null);
            TextView lshop=(TextView)view.findViewById(R.id.shop_names);
            //ImageView list_add=(ImageView) view.findViewById(R.id.list_add)
            lshop.setText(names[position]);
            return view;
        }
    }
}
