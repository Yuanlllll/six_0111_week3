package com.example.six_0111_week3.model;

import android.util.Log;

import com.example.six_0111_week3.bean.ShowBean;
import com.example.six_0111_week3.okhttp.HttpUtils;
import com.example.six_0111_week3.okhttp.OkHttp3;

public class ShowModel implements IShowModel {
    @Override
    public void GetData(String url, final GetShowCallBack getShowCallBack) {
        HttpUtils.getInstance().doGet(url, ShowBean.class, new HttpUtils.NetCallBack() {
            @Override
            public void onSuccess(Object oj) {
                getShowCallBack.succ(oj);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void GetDataImg(String url, final GetImgCallBack getImgCallBack) {
        OkHttp3.okHttpGet(url, new OkHttp3.GetBackGet() {
            @Override
            public void getTrue(String succ) {
                getImgCallBack.succ(succ);
                Log.i( "getTrue: ",succ);
            }
        });
    }


}
