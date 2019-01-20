package com.example.six_0111_week3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.six_0111_week3.R;
import com.example.six_0111_week3.adapter.MyAdapter;
import com.example.six_0111_week3.adapter.ShopAdapter;
import com.example.six_0111_week3.api.Api;
import com.example.six_0111_week3.bean.ShopBean;
import com.example.six_0111_week3.shop.presenter.ShopPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SanFragment extends Fragment {

    private HashMap<Object, Object> hashMap;
    private RecyclerView recyclerview;
    private ShopBean shopBean;
    private List<ShopBean.DataBean> mlist=new ArrayList<>();
    private CheckBox iv_cricle;
    private TextView total_num;
    private TextView total_price;
    private ShopAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.san_fragment, container, false);
        recyclerview = view.findViewById(R.id.recyclerview);
        iv_cricle = view.findViewById(R.id.iv_cricle);
        total_num = view.findViewById(R.id.total_num);
        total_price = view.findViewById(R.id.total_price);

        iv_cricle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSeller(iv_cricle.isChecked());
                adapter.notifyDataSetChanged();
            }
        });
        getData();
        return view;
    }

    private void getData()
    {
        ShopPresenter shopPresenter = new ShopPresenter(this);
        hashMap = new HashMap<>();
        hashMap.put("uid","71");
        shopPresenter.getshopPdata(Api.URL_GET_SHOP_CAR_INFO,hashMap);
    }

    public void getShopVData(Object object)
    {
        if (object instanceof ShopBean)
        {
            shopBean = (ShopBean) object;
            Log.i("sss","getShopVData"+object.toString());
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(manager);
        mlist = shopBean.getData();
        //创建适配器
        adapter = new ShopAdapter(getActivity(),shopBean);
        recyclerview.setAdapter(adapter);
        if (mlist!=null)
        {
            mlist.remove(0);
            adapter.setList(mlist);

        }

        adapter.setListener(new ShopAdapter.ShopCallBack() {
            @Override
            public void callBack(List<ShopBean.DataBean> list) {
                double zj=0;
                //勾选商品数量,不是商品的购买数量
                int num=0;
                int totalNum=0;
                for (int i=0;i<list.size();i++)
                {
                    //获取商家里面的商品
                    List<ShopBean.DataBean.ListBean> listAll = list.get(i).getList();
                    for (int j=0;j<listAll.size();j++)
                    {
                        totalNum=totalNum+Integer.parseInt(listAll.get(j).getNum());
                        //取选中状态
                        if (listAll.get(j).isIscheck())
                        {
                            zj=zj+(listAll.get(j).getPrice()*Integer.parseInt(listAll.get(j).getNum()));

                            num+=Integer.parseInt(listAll.get(j).getNum());

                        }
                    }
                }
                if (num<totalNum)
                {
                    //不是全部选中
                    iv_cricle.setChecked(false);
                }
                else
                {
                    //全部选中
                    iv_cricle.setChecked(true);
                }
                total_price.setText("合计:"+zj);
                total_num.setText("去结算("+num+")");
                }
        });

        }

        //修改选中状态,获取价格和数量
    private void checkSeller(boolean b)
    {
        double zj=0;
        int num=0;
        for (int j=0;j<mlist.size();j++)
        {
            ShopBean.DataBean dataBean = mlist.get(j);
            dataBean.setIscheck(b);

            List<ShopBean.DataBean.ListBean> listBeans = mlist.get(j).getList();
            for (int i=0;i<listBeans.size();i++)
            {
                listBeans.get(i).setIscheck(b);
                zj=zj+((Integer.parseInt(listBeans.get(i).getNum()))*listBeans.get(i).getPrice());
                //zj=zj+(Integer.parseInt(listBeans.get(i).getNum()))*Double.valueOf(listBeans.get(i).getPrice());
                num=num+Integer.parseInt(listBeans.get(i).getNum());

            }
        }

        if (b)
        {
            total_price.setText("合计:"+zj);
            total_num.setText("去结算(" + num + ")");
        }
        else
        {
            total_price.setText("合计:0.0");
            total_num.setText("去结算(0)");
        }

    }
}
