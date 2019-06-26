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
import com.example.test01.activity.Activity_Shop_To_Food;
import com.example.test01.bmob.Shop;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class Fragment_Home  extends Fragment {

    View view;

    MyBaseAdapter mAdapter;
    ListView mListView;
    String names[];       //等于Shop里面的shopNamw
    String tels[];
    String shopAddresses[];

    public Bitmap bitmaps[];
    public String savePaths[];
    BmobFile bmobfiles[];

    BitmapFactory.Options options;  //bitmap图片缩放
    Bitmap bitmap_01;
    String path;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_home,container,false);

        mListView= (ListView) view.findViewById(R.id.listView_home);
        mAdapter=new MyBaseAdapter();

        options = new BitmapFactory.Options();
        options.inSampleSize=10;

        ff_query_shop();
        ff_listView_click();

        return view;
    }


    public void ff_query_shop()
    {
        BmobQuery<Shop> bmobQuery = new BmobQuery<Shop>();
        bmobQuery.addWhereNotEqualTo("shopTel","");                   //查询商家电话不等于空的
        bmobQuery.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> object, BmobException e) {
                if(e==null) {                  //查询成功

                    Shop shop222=object.get(2);
                    System.out.println(shop222.shopName);


                    System.out.println("查询成功    "+object.size());
                    if(object.size()>0)
                    {
                        names = new String[object.size()];              //定义names数组 长度
                        tels = new String[object.size()];
                        shopAddresses = new String[object.size()];
                        bitmaps=new Bitmap[object.size()];
                        bmobfiles=new BmobFile[object.size()];


                        for ( int  i = 0; i < object.size(); i++)                 //遍历
                        {
                            names[i] = object.get(i).getShopName();        //给names数组 赋值
                            //names[i] = object.get(i).shopName+"555";     //可行
                            tels[i] = object.get(i).getShopTel();
                            shopAddresses[i] = object.get(i).getShopAddress();
                            bmobfiles[i]=object.get(i).getShopLogo();
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
                Intent intent01=new Intent(getApplicationContext(), Activity_Shop_To_Food.class);
                intent01.putExtra("extra_tel",tels[i]);    //传递数据
                intent01.putExtra("extra_shopName",names[i]);
                intent01.putExtra("extra_shopAddress",shopAddresses[i]);
                startActivity(intent01);
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
            View view= View.inflate(getApplicationContext(),R.layout.list_item_shop,null);

            TextView list_tv_shop=(TextView)view.findViewById(R.id.list_tv_shop);
            ImageView list_iv_shop=(ImageView) view.findViewById(R.id.list_iv_shop);

            list_tv_shop.setText(names[position]);
            list_iv_shop.setImageBitmap( bitmaps[position] );



            return view;
        }
    }


}
