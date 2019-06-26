package com.example.test01.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.test01.R;
import com.example.test01.fragment.Fragment_Home;
import com.example.test01.fragment.Fragment_Menu;

public class Activity_Fragment extends AppCompatActivity {

    public FragmentTransaction beginTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Fragment_Menu fragment_Menu=new Fragment_Menu();
        Fragment_Home fragment_Home=new Fragment_Home();

        beginTransaction=getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fLayout_menu,fragment_Menu);             //  activity里面,fLayout_menu只是位置
        beginTransaction.replace(R.id.fLayout_content,fragment_Home);      //   activity里面,fLayout_content只是位置
        beginTransaction.commit();


    }
}
