package com.example.six_0111_week3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.six_0111_week3.R;
import com.example.six_0111_week3.bean.ShopBean;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;
/*
* 商家信息
* */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private Context context;
    private ShopBean shopBean;
    //private List<ShopBean.DataBean.ListBean> list;
    private List<ShopBean.DataBean> mList = new ArrayList<>();

    public ShopAdapter(Context context, ShopBean shopBean) {
        this.context = context;
        this.shopBean = shopBean;
    }

    @NonNull
    @Override
    public ShopAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shop_layout, null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopAdapter.MyViewHolder holder, final int position)
    {
        holder.text_title.setText(shopBean.getData().get(position).getSellerName());
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.shop_view.setLayoutManager(manager);
        //创建适配器
        List<ShopBean.DataBean.ListBean> list = shopBean.getData().get(position).getList();
        final CarAdapter carAdapter = new CarAdapter(context, list);
        holder.shop_view.setAdapter(carAdapter);

        //商家,给复选框设置状态,商品全选中,商家选中
       holder.chec_box.setChecked(mList.get(position).isIscheck());
        carAdapter.setListener(new CarAdapter.ShopCallBack() {
            @Override
            public void callBack() {
                //从商品适配器回调回来
                if (shopCallBack!=null)
                {
                    shopCallBack.callBack(mList);
                }
                List<ShopBean.DataBean.ListBean> listBeans = mList.get(position).getList();
                //创建一个临时的标志位，用来记录现在点击的状态
                boolean isAllChecked = true;
                for (ShopBean.DataBean.ListBean bean : listBeans) {
                    if (!bean.isIscheck()) {
                        //只要有一个商品未选中，商家不进行选中,标志位设置成false，并且跳出循环
                        isAllChecked = false;
                        break;
                    }
                }
                //刷新商家的状态,进行选中/未选中
                holder.chec_box.setChecked(isAllChecked);
                mList.get(position).setIscheck(isAllChecked);
                }
        });
        //监听checkBox的点击事件
        //目的是改变商家下面所有商品的选中状态
        holder.chec_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先改变自己的标志位
               mList.get(position).setIscheck(holder.chec_box.isChecked());
                //调用产品adapter的方法，用来进行商品的全选和反选
                carAdapter.selectorRemoveAll(holder.chec_box.isChecked());
            }
        });


    }

    @Override
    public int getItemCount() {
        return shopBean.getData().size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView text_title;
        private final RecyclerView shop_view;
        private final CheckBox chec_box;

        public MyViewHolder(View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            shop_view = itemView.findViewById(R.id.shop_view);
            chec_box = itemView.findViewById(R.id.chec_box);
            }
    }

    public void setList(List<ShopBean.DataBean> mlist) {
        this.mList=mlist;
        notifyDataSetChanged();
    }

    private ShopCallBack shopCallBack;
    public void setListener(ShopCallBack shopCallBack) {
        this.shopCallBack = shopCallBack;
    }

    //定义接口
    public interface ShopCallBack
    {
        void callBack(List<ShopBean.DataBean> list);
    }
}
