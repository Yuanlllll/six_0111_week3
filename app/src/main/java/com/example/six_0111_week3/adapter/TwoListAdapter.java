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

class TwoListAdapter extends RecyclerView.Adapter <TwoListAdapter.TwoListViewHolder>{
   private Context context;
    private List<ShowBean.ProductItemBean.ProductItem> commodityList1;

    public TwoListAdapter(Context context, List<ShowBean.ProductItemBean.ProductItem> commodityList1) {
        this.context = context;
        this.commodityList1 = commodityList1;
    }

    @NonNull
    @Override
    public TwoListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.layout_list_two, null);
        TwoListViewHolder twoListViewHolder = new TwoListViewHolder(view);
        return twoListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TwoListViewHolder twoListViewHolder, int i) {
      twoListViewHolder.name.setText(commodityList1.get(i).commodityName);
        twoListViewHolder.price.setText("¥"+commodityList1.get(i).price);
        Glide.with(context).load(commodityList1.get(i).masterPic).into(twoListViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return commodityList1.size();
    }

    public class TwoListViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public TwoListViewHolder(@NonNull View itemView) {
            super(itemView);
             img = itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price);
            name =  itemView.findViewById(R.id.title);
        }
    }
}
