package com.example.test01.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.test01.R;
import com.example.test01.fragment.Fragment_Diner_Home;
import com.example.test01.fragment.Fragment_Diner_Menu;

public class Diner_Activity_Fragment extends AppCompatActivity {
    public FragmentTransaction beginTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diner_fragment);

        Fragment_Diner_Menu fragment_Dienr_Menu=new Fragment_Diner_Menu();
        Fragment_Diner_Home fragment_Diner_Home=new Fragment_Diner_Home();

        beginTransaction=getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fLayout_diner_menu,fragment_Dienr_Menu);             //  activity里面,fLayout_menu只是位置
        beginTransaction.replace(R.id.fLayout_diner_content,fragment_Diner_Home);      //   activity里面,fLayout_content只是位置
        beginTransaction.commit();


    }
}
