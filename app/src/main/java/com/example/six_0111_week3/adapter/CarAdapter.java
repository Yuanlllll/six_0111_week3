package com.example.six_0111_week3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.six_0111_week3.AddBar;
import com.example.six_0111_week3.R;
import com.example.six_0111_week3.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;
/*
* 商品信息
* */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private Context context;
    private List<ShopBean.DataBean.ListBean> slist=new ArrayList<>();

    public CarAdapter(Context context, List<ShopBean.DataBean.ListBean> slist) {
        this.context = context;
        this.slist = slist;
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shopcar_layout, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, final int position)
    {
        holder.car_title.setText(slist.get(position).getTitle());
        holder.car_price.setText("¥"+slist.get(position).getPrice()+"");
        Glide.with(context).load(slist.get(position).getImages().split("\\|")[0].replace("https","http")).into(holder.car_img);

        //商品
        //根据记录状态,改变勾选,设置复选框状态
        holder.car_checbox.setChecked(slist.get(position).isIscheck());
       //商品跟商家不同,商品的选中改变监听
        holder.car_checbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //先改变自己的状态
                slist.get(position).setIscheck(isChecked);
                //回调 ,到activity,选中状态被改变
                if (shopCallBack!=null)
                {
                    shopCallBack.callBack();
                }
            }
        });

        //给输入框赋值
        holder.add_view.setData(this,slist,position);
        holder.add_view.setOnCallBack(new AddBar.CarCallBack() {
            @Override
            public void callBack() {
               if (shopCallBack!=null)
                {
                    shopCallBack.callBack();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return slist.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox car_checbox;
        private final ImageView car_img;
        private final TextView car_title;
        private final TextView car_price;
        private final AddBar add_view;

        public ViewHolder(View itemView) {
            super(itemView);
            car_img = itemView.findViewById(R.id.car_img);
            car_checbox = itemView.findViewById(R.id.car_checbox);
            car_title = itemView.findViewById(R.id.car_title);
            car_price = itemView.findViewById(R.id.car_price);
            add_view = itemView.findViewById(R.id.add_view);
        }
    }


    //点击商家,商品进行全选反选,
    // 修改子商品的全选和反选
    public void selectorRemoveAll(boolean ischecked)
    {
        for (ShopBean.DataBean.ListBean listBean:slist)
        {
            listBean.setIscheck(ischecked);
         }
            notifyDataSetChanged();
    }

    private ShopCallBack shopCallBack;
    public void setListener(ShopCallBack shopCallBack) {
        this.shopCallBack = shopCallBack;
    }

    //定义接口
    public interface ShopCallBack
    {
        void callBack();
    }
}
