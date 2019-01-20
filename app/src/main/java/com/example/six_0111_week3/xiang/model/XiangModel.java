package com.example.six_0111_week3.xiang.model;

import com.example.six_0111_week3.bean.XiangBean;
import com.example.six_0111_week3.okhttp.HttpUtils;

public class XiangModel implements Ixmodel {
    @Override
    public void getxmdata(String url, final XlistCallBack xlistCallBack)
    {
        HttpUtils.getInstance().doGet(url, XiangBean.class, new HttpUtils.NetCallBack() {
            @Override
            public void onSuccess(Object oj) {
                xlistCallBack.OnSuccess(oj);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
