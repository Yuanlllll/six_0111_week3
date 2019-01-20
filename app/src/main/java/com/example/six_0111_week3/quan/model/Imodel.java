package com.example.six_0111_week3.quan.model;

public interface Imodel
{
    void getmdata(String url,ListCallBack listCallBack);

    interface ListCallBack
    {
        void getSuccess(Object obj);
    }
}
