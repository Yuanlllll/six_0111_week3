package com.example.six_0111_week3.shop.presenter;

import com.example.six_0111_week3.fragment.SanFragment;
import com.example.six_0111_week3.shop.model.IShopModel;
import com.example.six_0111_week3.shop.model.ShopModel;

import java.util.Map;

public class ShopPresenter implements IShopPresenter
{
    SanFragment sanFragment;
    private final ShopModel shopModel;

    public ShopPresenter(SanFragment sanFragment) {
        this.sanFragment = sanFragment;
        shopModel = new ShopModel();
    }

    @Override
    public void getshopPdata(String url, Map map)
    {
       shopModel.getshopVdata(url, map, new IShopModel.ShopListCallBack() {
           @Override
           public void getOnSuccess(Object obj) {
               sanFragment.getShopVData(obj);
           }
       });


    }
}
