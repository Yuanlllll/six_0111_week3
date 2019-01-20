package com.example.six_0111_week3.login.model;

public interface ILoginModel {
    void GetData(String url,String name,String pwd,LoginCallBack loginCallBack);
    interface LoginCallBack{
        void succ(String oj);
    }
}
