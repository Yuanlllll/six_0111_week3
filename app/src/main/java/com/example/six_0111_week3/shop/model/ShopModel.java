package com.example.six_0111_week3.shop.model;

import com.example.six_0111_week3.bean.ShopBean;
import com.example.six_0111_week3.okhttp.HttpUtils;

import java.util.Map;

public class ShopModel implements IShopModel
{
    @Override
    public void getshopVdata(String url, Map map, final ShopListCallBack shopListCallBack)
    {
        HttpUtils.getInstance().doPost(url, ShopBean.class, map, new HttpUtils.NetCallBack() {
            @Override
            public void onSuccess(Object oj) {
                shopListCallBack.getOnSuccess(oj);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
