package com.example.six_0111_week3.quan.model;

import android.util.Log;

import com.example.six_0111_week3.bean.QuanBean;
import com.example.six_0111_week3.bean.XiangBean;
import com.example.six_0111_week3.okhttp.HttpUtils;

public class QuanModel implements Imodel {
    @Override
    public void getmdata(String url, final ListCallBack listCallBack)
    {
        HttpUtils.getInstance().doGet(url, QuanBean.class, new HttpUtils.NetCallBack() {
            @Override
            public void onSuccess(Object oj) {
                listCallBack.getSuccess(oj);
                Log.i("onSuccess: ",oj.toString());
            }

            @Override
            public void onFailure(Exception e) {


            }
        });


    }



}
