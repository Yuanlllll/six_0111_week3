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

public class SanListAdapter extends RecyclerView.Adapter <SanListAdapter.SanListViewHolder>{
private Context context;
private List<ShowBean.ProductItemBean.ProductItem> commodityList1;

public SanListAdapter(Context context, List<ShowBean.ProductItemBean.ProductItem> commodityList1) {
        this.context = context;
        this.commodityList1 = commodityList1;
        }

@NonNull
@Override
public SanListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_shouye, null);
        SanListViewHolder sanListViewHolder = new SanListViewHolder(view);
        return sanListViewHolder;
        }

@Override
public void onBindViewHolder(@NonNull SanListViewHolder sanListViewHolder, int i) {
     sanListViewHolder.name.setText(commodityList1.get(i).commodityName);
     sanListViewHolder.price.setText(commodityList1.get(i).price);
        Glide.with(context).load(commodityList1.get(i).masterPic).into(sanListViewHolder.img);
        }

@Override
public int getItemCount() {
        return commodityList1.size();
        }

public class SanListViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView name;
    TextView price;
    public SanListViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        price = itemView.findViewById(R.id.price);
        name =  itemView.findViewById(R.id.name);
    }
}
}
