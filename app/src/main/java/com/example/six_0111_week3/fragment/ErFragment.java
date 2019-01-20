package com.example.six_0111_week3.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.six_0111_week3.R;
import com.example.six_0111_week3.adapter.QuanAdapter;
import com.example.six_0111_week3.bean.QuanBean;
import com.example.six_0111_week3.quan.presenter.QuanPresenter;
import com.example.six_0111_week3.quan.view.Iview;
import com.example.six_0111_week3.xiang.view.Ixview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;


public class ErFragment extends Fragment implements Iview {

    private XRecyclerView xrecy_view;
    private List<QuanBean.ResultBean> list;
    private int page=1;
    private int count=10;
    private QuanPresenter quanPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.er_fragment, container, false);
        xrecy_view = view.findViewById(R.id.xrecy_view);
         quanPresenter = new QuanPresenter(this);
        quanPresenter.getpdata(page,count);
        xrecy_view.setPullRefreshEnabled(true);
        xrecy_view.setLoadingMoreEnabled(true);
        xrecy_view.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        page=1;
                        quanPresenter.getpdata(page,count);
                        xrecy_view.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        page++;
                        quanPresenter.getpdata(page,count);
                        xrecy_view.loadMoreComplete();
                    }
                },2000);
            }
        });

        return view;
    }

    @Override
    public void getvdata(Object data)
    {
        QuanBean quanBean = (QuanBean) data;
        list = quanBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        xrecy_view.setLayoutManager(manager);
        QuanAdapter adapter=new QuanAdapter(getActivity(),quanBean);
        xrecy_view.setAdapter(adapter);


    }


}
