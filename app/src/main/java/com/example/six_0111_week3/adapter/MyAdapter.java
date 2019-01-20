package com.example.six_0111_week3.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.six_0111_week3.R;
import com.example.six_0111_week3.XiangqingActivity;
import com.example.six_0111_week3.bean.HomeBean;
import com.example.six_0111_week3.bean.ShowBean;

import java.util.List;

public class  MyAdapter extends RecyclerView.Adapter {
    Context context;
    private final int RXXP_VIEW = 0;
    private final int PZSH_VIEW = 1;
    private final int MLSS_VIEW = 2;
    private List<ShowBean.ProductItemBean> resultBeans;
    private List<ShowBean.ProductItemBean.ProductItem> commodityList1;

    public MyAdapter(Context context, List<ShowBean.ProductItemBean> resultBeans) {
        this.context =context;
        this.resultBeans =resultBeans;
    }
    @Override
    public int getItemViewType(int position) {
        switch (position)
        {
            case 0:
                return RXXP_VIEW;
            case 1:
                return PZSH_VIEW;

        }
        return MLSS_VIEW;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView;
        if(i==RXXP_VIEW)
        {
            mView=View.inflate(viewGroup.getContext(),R.layout.shouye_1,null);
            return new Item1ViewHolder(mView);
        }else if(i == PZSH_VIEW)
        {
            return new Item2ViewHolder(LayoutInflater.from(context).inflate(R.layout.shouye_2,viewGroup,false));
        }
        else {
            return new Item3ViewHolder(LayoutInflater.from(context).inflate(R.layout.shouye_3,viewGroup,false));
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i)
    {
        if(viewHolder instanceof Item1ViewHolder)
        {
            ((Item1ViewHolder) viewHolder).rxxp.setText(resultBeans.get(i).name);
            commodityList1 = resultBeans.get(i).commodityList;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((Item1ViewHolder) viewHolder).rv.setLayoutManager(linearLayoutManager);
            //创建适配器
            HListAdapter hListAdapter = new HListAdapter(context,commodityList1);
            ((Item1ViewHolder) viewHolder).rv.setAdapter(hListAdapter);
           hListAdapter.setJieKou(new HListAdapter.JieKou() {
               @Override
               public void onClick(int position, List<ShowBean.ProductItemBean.ProductItem> list) {
                   String id = commodityList1.get(position).commodityId;
                   Intent intent = new Intent(context, XiangqingActivity.class);
                   intent.putExtra("id",id);
                   Log.i( "onClick: ",id);
                   context.startActivity(intent);
               }
           });


        }if(viewHolder instanceof  Item2ViewHolder)
    {
        ((Item2ViewHolder) viewHolder).pzsh.setText(resultBeans.get(i).name);
        commodityList1 = resultBeans.get(i).commodityList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        ((Item2ViewHolder) viewHolder).rv2.setLayoutManager(linearLayoutManager);
        //创建适配器
        TwoListAdapter twoListAdapter = new TwoListAdapter(context,commodityList1);
        ((Item2ViewHolder) viewHolder).rv2.setAdapter(twoListAdapter);
    }
        if(viewHolder instanceof  Item3ViewHolder)
        {
            ((Item3ViewHolder) viewHolder).mlss.setText(resultBeans.get(i).name);
            commodityList1 = resultBeans.get(i).commodityList;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
            gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            ((Item3ViewHolder) viewHolder).rv3.setLayoutManager(gridLayoutManager);
            //创建适配器
            SanListAdapter sanListAdapter = new SanListAdapter(context,commodityList1);
            ((Item3ViewHolder) viewHolder).rv3.setAdapter(sanListAdapter);
        }




    }
    @Override
    public int getItemCount() {
        return  resultBeans.size();

    }
    class Item1ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        TextView rxxp;

        public Item1ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            rxxp = itemView.findViewById(R.id.rxxptv);
        }
    }
    class Item2ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rv2;
        TextView pzsh;
        public Item2ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv2 = itemView.findViewById(R.id.rv);
            pzsh = itemView.findViewById(R.id.pzshtv);
        }
    }
    class Item3ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv3;
        TextView mlss;
        public Item3ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv3 = itemView.findViewById(R.id.rv);
            mlss = itemView.findViewById(R.id.mlsstv);
        }
    }



}
