package com.example.test01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test01.R;
import com.example.test01.bmob.Food;
import com.example.test01.others.Infor;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class Activity_Updata_Food extends AppCompatActivity {
    
    EditText ed_foodName,ed_foodPrice;
    Button bt_updata,bt_delete;
    String new_foodName,new_price;
    String old_foodName,old_price,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_food);
         Bmob.initialize(this, "0e3259d5d1db207b04b7e9759deed226");

        ed_foodName=(EditText) findViewById(R.id.ed_foodName);
        ed_foodPrice=(EditText) findViewById(R.id.ed_foodPrice);
        bt_updata=(Button) findViewById(R.id.bt_updata);
        bt_delete=(Button) findViewById(R.id.bt_delete);

        Intent intent01 = getIntent();      //接收页面跳转的数据
        old_foodName = intent01.getStringExtra("extra_foodName");
        old_price = intent01.getStringExtra("extra_price");
        tel= Infor.tel;


        bt_updata.setOnClickListener(new MyButton() );
        bt_delete.setOnClickListener(new MyButton() );




    }


    public void ff_updata()
    {
        BmobQuery<Food> bmobQuery01 = new BmobQuery<Food>();
        bmobQuery01.addWhereEqualTo("shopTel",tel);
        BmobQuery<Food> bmobQuery02 = new BmobQuery<Food>();
        bmobQuery02.addWhereEqualTo("foodName",old_foodName);
        //最后组装完整的and条件
        List<BmobQuery<Food>> andQuerys = new ArrayList<BmobQuery<Food>>();
        andQuerys.add(bmobQuery01);
        andQuerys.add(bmobQuery02);

        BmobQuery<Food> bmobQuery = new BmobQuery<Food>();
        bmobQuery.and(andQuerys);
        bmobQuery.findObjects(new FindListener<Food>() {
            @Override
            public void done(List<Food> list, BmobException e) {
                if(e==null) {                  //查询成功         list.get(0).getObjectId();
                    Toast.makeText(getApplicationContext(), "查询成功  "+list.size(), Toast.LENGTH_SHORT).show();

                    Food p2 = new Food();
                    p2.setFoodName(new_foodName);       //上传新的数据
                    p2.setFoodPrice(new_price);
                    p2.update( list.get(0).getObjectId() , new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){                            //页面跳转
                                Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();

                                Intent intent02=new Intent();
                                setResult(2,intent02);
                                finish();
                            }else{
                                //toast("更新失败：" + e.getMessage());
                            }
                        }

                    });
                }else{
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });
    }

    public void ff_delete()
    {
        BmobQuery<Food> bmobQuery01 = new BmobQuery<Food>();
        bmobQuery01.addWhereEqualTo("shopTel",tel);
        BmobQuery<Food> bmobQuery02 = new BmobQuery<Food>();
        bmobQuery02.addWhereEqualTo("foodName",old_foodName);
        //最后组装完整的and条件
        List<BmobQuery<Food>> andQuerys = new ArrayList<BmobQuery<Food>>();
        andQuerys.add(bmobQuery01);
        andQuerys.add(bmobQuery02);

        BmobQuery<Food> bmobQuery = new BmobQuery<Food>();
        bmobQuery.and(andQuerys);
        bmobQuery.findObjects(new FindListener<Food>() {
            @Override
            public void done(List<Food> list, BmobException e) {
                if(e==null) {                  //查询成功         list.get(0).getObjectId();
                    Toast.makeText(getApplicationContext(), "查询成功  "+list.size(), Toast.LENGTH_SHORT).show();

                    Food p2 = new Food();
                    p2.setObjectId(list.get(0).getObjectId() );
                    p2.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                //页面跳转
                                Intent intent03=new Intent();
                                setResult(2,intent03);
                                finish();
                            }else{
                                //toast("删除失败：" + e.getMessage());
                            }
                        }

                    });
                }else{
                    System.out.println("查询失败：" + e.getMessage());
                }
            }
        });
    }

    class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_updata:
                    new_foodName = ed_foodName.getText().toString();
                    new_price = ed_foodPrice.getText().toString();

                    if( new_foodName.equals("") && new_price.equals("") )        // 全为空
                    {
                        Toast.makeText(getApplicationContext(), "无效操作", Toast.LENGTH_SHORT).show();
                    }else{                                                      // 不全为空
                        if( new_foodName.equals("") )
                            new_foodName=old_foodName;
                        if( new_price.equals("") )
                            new_price=old_price;
                        //先查询ID。再修改
                        ff_updata();
                    }

                    break;
                case R.id.bt_delete:
                    ff_delete();
                    break;
            }
        }
    }
}
