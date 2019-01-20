package com.example.six_0111_week3.presenter;

import android.util.Log;

import com.example.six_0111_week3.ShowActivity;
import com.example.six_0111_week3.api.Api;
import com.example.six_0111_week3.fragment.ShowFragment;
import com.example.six_0111_week3.model.IShowModel;
import com.example.six_0111_week3.model.ShowModel;

public class ShowPresenter implements IShowPresenter {
    private final ShowModel showModel;
    ShowFragment showFragment;

    public ShowPresenter(ShowFragment showFragment) {
        this.showFragment = showFragment;
        showModel = new ShowModel();
    }

    @Override
    public void GetShowModelData() {
        showModel.GetData(Api.SHOPLIST, new IShowModel.GetShowCallBack() {
            @Override
            public void succ(Object oj) {
             showFragment.GetViewData(oj);
            }
        });
    }
    @Override
    public void getModelImageData(String url) {
        showModel.GetDataImg(url, new IShowModel.GetImgCallBack() {
            @Override
            public void succ(String oj) {
                showFragment.GetPresterData(oj);
            }
        });
    }

}
