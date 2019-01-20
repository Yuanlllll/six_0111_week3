package com.example.six_0111_week3.model;

public interface IShowModel {
    void GetData(String url,GetShowCallBack getShowCallBack);
    interface GetShowCallBack{
        void succ(Object oj);
    }
    void GetDataImg(String url,GetImgCallBack getImgCallBack);
    interface GetImgCallBack{
        void succ(String oj);
    }
}
