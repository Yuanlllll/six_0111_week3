package com.example.six_0111_week3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.six_0111_week3.bean.HomeBean;
import com.example.six_0111_week3.login.presenter.LoginPresenter;
import com.example.six_0111_week3.login.view.LoginView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    EditText mLogin,mRegister;
    Button mLoginBut;
    private LoginPresenter loginPresenter;
    private TextView but_res;
    private CheckBox che_box;
    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin = findViewById(R.id.login_name_et);
        mRegister =  findViewById(R.id.login_pswd_et);
        mLoginBut=  findViewById(R.id.login_lgbt);
        but_res=  findViewById(R.id.but_res);
        che_box =  findViewById(R.id.che_box);
        //初始persenter
        loginPresenter = new LoginPresenter(this);
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        String phone = config.getString("phone", "");
        String pwd1 = config.getString("pwd", "");
        boolean flag = config.getBoolean("flag", false);
        che_box.setChecked(flag);
        if(flag){
            mLogin.setText(phone);
            mRegister.setText(pwd1);
        }
        mLoginBut.setOnClickListener(this);
        but_res.setOnClickListener(this);
         }

    @Override
    public void GetLoginData(final String data) {
        new Thread(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {



                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        HomeBean bean = gson.fromJson(data, HomeBean.class);
                        Toast.makeText(MainActivity.this,bean.getMessage(),Toast.LENGTH_SHORT).show();
                        if(bean.getMessage().equals("登录成功")){
                            SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
                            SharedPreferences.Editor edit = config.edit();
                            if(che_box.isChecked()){
                                edit.putString("phone",name);
                                edit.putString("pwd",pwd);
                                edit.putBoolean("flag",true);
                            }else{
                                edit.putBoolean("flag",false);
                            }
                            edit.commit();
                            startActivity(new Intent(MainActivity.this,ShowActivity.class));
                        }
                    }
                });
            }
        }.start();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_lgbt:
                name = mLogin.getText().toString();
                pwd = mRegister.getText().toString();
                if(name !=null&& pwd !=null){
                    loginPresenter.GetLoginModelData(name,pwd);
                }
                break;
            case R.id.but_res:
                startActivity(new Intent(MainActivity.this,ZhuActivity.class));
                break;
        }

    }
}
