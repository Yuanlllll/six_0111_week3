package com.example.six_0111_week3.login.presenter;

import com.example.six_0111_week3.MainActivity;
import com.example.six_0111_week3.api.Api;
import com.example.six_0111_week3.login.model.ILoginModel;
import com.example.six_0111_week3.login.model.LoginModel;

public class LoginPresenter implements ILoginPresenter {
    private  LoginModel loginModel;
    MainActivity mainActivity;



    public LoginPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        loginModel = new LoginModel();
    }

    @Override
    public void GetLoginModelData(String name,String pwd) {
        loginModel.GetData(Api.LOGIN, name,pwd,new  ILoginModel.LoginCallBack() {
            @Override
            public void succ(String oj) {
             mainActivity.GetLoginData(oj);
            }
        });
    }
}
