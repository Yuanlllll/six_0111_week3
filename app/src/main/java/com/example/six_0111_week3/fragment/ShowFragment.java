package com.example.six_0111_week3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.six_0111_week3.R;
import com.example.six_0111_week3.adapter.MyAdapter;
import com.example.six_0111_week3.bean.Bannerbaen;
import com.example.six_0111_week3.bean.HomeBean;
import com.example.six_0111_week3.bean.NewsBeanTwo;
import com.example.six_0111_week3.bean.ShowBean;
import com.example.six_0111_week3.presenter.ShowPresenter;
import com.example.six_0111_week3.view.IShowView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;


public class ShowFragment extends Fragment implements IShowView {
    private RecyclerView recy;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<HomeBean> list;
    private ShowPresenter showPresenter;
    private String name;
    private XBanner xbanner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shou_layout, container, false);
        recy = view.findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recy.setLayoutManager(linearLayoutManager);
        showPresenter = new ShowPresenter(this);
        showPresenter.GetShowModelData();
        showPresenter.getModelImageData("http://172.17.8.100/small/commodity/v1/bannerShow");
        xbanner = view.findViewById(R.id.xbanner);
        return view;
    }

    public void GetViewData(Object oj) {
        ShowBean productBean= (ShowBean) oj;
        name = productBean.result.mlss.get(0).name;
        Log.d("dddd", "getData: "+name);
        List<ShowBean.ProductItemBean> list = new ArrayList<>();
        list.addAll(productBean.result.rxxp);//添加集合数据到当前集合内
        list.addAll(productBean.result.mlss);
        list.addAll(productBean.result.pzsh);
        MyAdapter shouYeAdapter = new MyAdapter(getActivity(),list);
        recy.setAdapter(shouYeAdapter);
    }


    @Override
    public void GetPresterData(final String data) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                final List<String> list = new ArrayList<>();
                NewsBeanTwo two = gson.fromJson(data, NewsBeanTwo.class);
                List<NewsBeanTwo.ResultBean> result = two.getResult();
                for (int i = 0; i<result.size();i++){
                    list.add(result.get(i).getImageUrl());
                }
                xbanner.setData(list,null);
                xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
                    }
                });
                xbanner.setPageTransformer(Transformer.Default);
                xbanner.setPageChangeDuration(1000);
            }
        });
    }

    }

