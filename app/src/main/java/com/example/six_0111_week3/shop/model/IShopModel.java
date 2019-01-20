package com.example.six_0111_week3.shop.model;

import java.util.Map;

public interface IShopModel
{
    void getshopVdata(String url, Map map, ShopListCallBack shopListCallBack);
    interface ShopListCallBack
    {
        void getOnSuccess(Object obj);
    }

}
