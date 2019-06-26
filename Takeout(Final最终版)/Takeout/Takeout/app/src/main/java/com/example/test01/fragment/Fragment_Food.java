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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.test01.R;
import com.example.test01.activity.Activity_Add_Food;
import com.example.test01.activity.Activity_Updata_Food;
import com.example.test01.bmob.Food;
import com.example.test01.others.Infor;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class Fragment_Food extends Fragment {

    View view;
    MyBaseAdapter mAdapter;
    ListView mListView;

    private ImageView iv_addFood;
    String names[];
    String prices[];
    String tel;

    public Bitmap bitmaps[];
    BmobFile bmobfiles[];
    BitmapFactory.Options options;  //bitmap图片缩放

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_food,container,false);

        ImageView iv_addFood = (ImageView)  view.findViewById(R.id.iv_add);
        mListView= (ListView) view.findViewById(R.id.listView_food);
        mAdapter=new MyBaseAdapter();

        options = new BitmapFactory.Options();
        options.inSampleSize=10;

        ff_query_food();
        ff_listView_click();

        iv_addFood.setOnClickListener(new View.OnClickListener() {       //减少
            @Override
            public void onClick(View v) {
                Intent intent01=new Intent(getApplicationContext(), Activity_Add_Food.class);
                startActivityForResult(intent01,1);
            }
        });
        return view;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override       //接收回传的数据 userName
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==1)
        {
            ff_query_food();
        }
        if(resultCode==2)
        {
            ff_query_food();
        }
    }


    public void  ff_query_food()
    {
        BmobQuery<Food> bmobQuery = new BmobQuery<Food>();
        bmobQuery.addWhereEqualTo("shopTel", Infor.tel);
        bmobQuery.findObjects(new FindListener<Food>() {
            @Override
            public void done(List<Food> object, BmobException e) {
                if(e==null) {                  //查询成功
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
                    System.out.println("查询失败：" + e.getMessage());
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


    public void ff_listView_click(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent02=new Intent(getApplicationContext(), Activity_Updata_Food.class);
                intent02.putExtra("extra_foodName",names[i]);    //传递数据 菜品名称
                intent02.putExtra("extra_price",prices[i]);    //传递数据 菜品名称
                startActivityForResult(intent02,2);


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
            View view= View.inflate(getApplicationContext(),R.layout.list_item_food,null);

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
