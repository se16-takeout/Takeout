package cn.itcast.takeout.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.itcast.takeout.MainActivity;
import cn.itcast.takeout.R;
import cn.itcast.takeout.utils.Contants;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        Button bt_login = findViewById(R.id.login_bt);
        Button bt_seller = findViewById(R.id.seller);
        Button bt_diner = findViewById(R.id.diner);
        bt_login.setOnClickListener(this);
        bt_diner.setOnClickListener(this);
        bt_seller.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_bt:
                login();
                break;
            case R.id.diner:break;
            case R.id.seller:break;
        }
    }

    private void login() {

        String username=et_username.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"请输入电话",    Toast.LENGTH_LONG).show();
            return;
        }
        String passwoed=et_password.getText().toString().trim();
        if((Contants.USERNAME.equals(username)&&Contants.PASSWORD.equals(passwoed))){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }else{
            Toast.makeText(this,"登陆失败",    Toast.LENGTH_LONG).show();
        }

    }
}
