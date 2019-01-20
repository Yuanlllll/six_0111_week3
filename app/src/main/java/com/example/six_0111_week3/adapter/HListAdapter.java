package com.example.six_0111_week3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.six_0111_week3.R;
import com.example.six_0111_week3.bean.ShowBean;

import java.util.List;

public class HListAdapter extends RecyclerView.Adapter<HListAdapter.HlistViewHodel> {
    Context context;
    List<ShowBean.ProductItemBean.ProductItem> list;
    public HListAdapter(Context context, List<ShowBean.ProductItemBean.ProductItem> list) {
        this.context=context;
        this.list=list;
        }
    JieKou jieKou;





    @NonNull
    @Override
    public HlistViewHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView;
        mView=View.inflate(viewGroup.getContext(),R.layout.item_shouye,null);
        return new HlistViewHodel(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull  HlistViewHodel hlistViewHodel, final int i) {
        hlistViewHodel.name.setText(list.get(i).commodityName);
        hlistViewHodel.price.setText(list.get(i).price);
        Glide.with(context).load(list.get(i).masterPic).into(hlistViewHodel.img);
        hlistViewHodel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jieKou.onClick(i,list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HlistViewHodel extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public HlistViewHodel(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            price = itemView.findViewById(R.id.price);
            name =  itemView.findViewById(R.id.name);
        }
    }

    public interface JieKou{
        void onClick(int position,List<ShowBean.ProductItemBean.ProductItem> list);
    }
    public void setJieKou(JieKou jieKou){
        this.jieKou=jieKou;
    }

}
