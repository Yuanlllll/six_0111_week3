package com.example.six_0111_week3.quan.presenter;

import com.example.six_0111_week3.api.Api;
import com.example.six_0111_week3.fragment.ErFragment;
import com.example.six_0111_week3.quan.model.Imodel;
import com.example.six_0111_week3.quan.model.QuanModel;

public class QuanPresenter implements Ipresenter
{
    private ErFragment erFragment;
    private final QuanModel quanModel;




    public QuanPresenter(ErFragment erFragment) {
        this.erFragment = erFragment;
        quanModel = new QuanModel();
    }

    @Override
    public void getpdata(int page,int count)
    {
        quanModel.getmdata(Api.QUAN+"?page="+page+"&count="+count, new Imodel.ListCallBack() {
            @Override
            public void getSuccess(Object obj) {
                erFragment.getvdata(obj);
            }
        });

    }


}
