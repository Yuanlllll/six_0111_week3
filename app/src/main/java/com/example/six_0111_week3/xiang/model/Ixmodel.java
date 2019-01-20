package com.example.six_0111_week3.xiang.model;

public interface Ixmodel
{
    void getxmdata(String url,XlistCallBack xlistCallBack);
     interface XlistCallBack
    {
       void OnSuccess(Object data);
    }
}
