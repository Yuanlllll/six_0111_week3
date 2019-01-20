package com.example.six_0111_week3.xiang.presenter;

import com.example.six_0111_week3.XiangqingActivity;
import com.example.six_0111_week3.api.Api;
import com.example.six_0111_week3.xiang.model.Ixmodel;
import com.example.six_0111_week3.xiang.model.XiangModel;

public class XiangPresenter implements Ixpresenter{
    XiangqingActivity xview;
    private final XiangModel xiangModel;

    public XiangPresenter(XiangqingActivity xview) {
        this.xview = xview;
        xiangModel = new XiangModel();
    }

    @Override
    public void getxpdata(int id)
    {
        xiangModel.getxmdata(Api.XIANG+"?commodityId="+id, new Ixmodel.XlistCallBack() {
            @Override
            public void OnSuccess(Object data) {
                xview.getxvdata(data);
            }
        });

    }
}
