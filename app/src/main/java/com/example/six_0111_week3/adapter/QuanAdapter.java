package com.example.six_0111_week3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.six_0111_week3.R;
import com.example.six_0111_week3.bean.QuanBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class QuanAdapter extends XRecyclerView.Adapter {
    private final int ONE = 0;
    private final int TWO = 1;
    private final int SAN = 2;
    private Context context;
    QuanBean quanBean;
    public QuanAdapter(Context context,  QuanBean quanBean) {
        this.context = context;
        this.quanBean = quanBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType==ONE){
            view = View.inflate(context, R.layout.quan_layout, null);
            return new OneImgViewHolder(view);
        }else if (viewType==TWO){
            view = View.inflate(context, R.layout.quan_layout2, null);
            return new TwoImgViewHolder(view);
        } else {
            view = View.inflate(context, R.layout.quan_layout3, null);
            return new SanImgViewHolder(view);
        }
}

     //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        if(holder instanceof OneImgViewHolder)
        {
            ((OneImgViewHolder) holder).nei11.setText(quanBean.getResult().get(position).getContent());
            ((OneImgViewHolder) holder).name11.setText(quanBean.getResult().get(position).getNickName());
            Glide.with(context).load(quanBean.getResult().get(position).getHeadPic()).into(((OneImgViewHolder) holder).img11);
            Glide.with(context).load(quanBean.getResult().get(position).getImage()).into(((OneImgViewHolder) holder).img111);
        }
        if(holder instanceof TwoImgViewHolder)
        {
            ((TwoImgViewHolder) holder).nei22.setText(quanBean.getResult().get(position).getContent());
            ((TwoImgViewHolder) holder).name22.setText(quanBean.getResult().get(position).getNickName());
            Glide.with(context).load(quanBean.getResult().get(position).getHeadPic()).into(((TwoImgViewHolder) holder).img22);
            Glide.with(context).load(quanBean.getResult().get(position).getImage()).into(((TwoImgViewHolder) holder).img122);
            Glide.with(context).load(quanBean.getResult().get(position).getImage()).into(((TwoImgViewHolder) holder).img222);
        }
        if(holder instanceof SanImgViewHolder)
        {
            ((SanImgViewHolder) holder).nei33.setText(quanBean.getResult().get(position).getContent());
            Log.i("aaa", "onBindViewHolder: "+quanBean.getResult().get(position).getContent());
            ((SanImgViewHolder) holder).name33.setText(quanBean.getResult().get(position).getNickName());
            Glide.with(context).load(quanBean.getResult().get(position).getHeadPic()).into(((SanImgViewHolder) holder).img33);
            Glide.with(context).load(quanBean.getResult().get(position).getImage()).into(((SanImgViewHolder) holder).img133);
            Glide.with(context).load(quanBean.getResult().get(position).getImage()).into(((SanImgViewHolder) holder).img233);
            Glide.with(context).load(quanBean.getResult().get(position).getImage()).into(((SanImgViewHolder) holder).img333);
        }
        }

    @Override
    public int getItemCount() {
        return quanBean.getResult().size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position)
        {
            case 0:
                return ONE;
            case 1:
                return TWO;
        }
        return SAN;
    }

    private class OneImgViewHolder extends RecyclerView.ViewHolder {
        private ImageView img11,img111;
        private TextView name11,nei11;
        public OneImgViewHolder(View view) {
            super(view);
            name11 = view.findViewById(R.id.name11);
            nei11 = view.findViewById(R.id.nei11);
            img11 = view.findViewById(R.id.img11);
            img111 = view.findViewById(R.id.img111);
        }
    }

    private class TwoImgViewHolder extends RecyclerView.ViewHolder {
        private ImageView img22,img122,img222;
        private TextView name22,nei22;
        public TwoImgViewHolder(View view) {
            super(view);
            name22 = view.findViewById(R.id.name22);
            nei22 = view.findViewById(R.id.nei22);
            img22 = view.findViewById(R.id.img22);
            img122 = view.findViewById(R.id.img122);
            img222 = view.findViewById(R.id.img222);
        }
    }

    private class SanImgViewHolder extends RecyclerView.ViewHolder {
        private ImageView img133,img33,img233,img333;
        private TextView name33,nei33;
        public SanImgViewHolder(View view) {
            super(view);
            name33 = view.findViewById(R.id.name33);
            nei33 = view.findViewById(R.id.nei33);
            img33 = view.findViewById(R.id.img33);
            img133 = view.findViewById(R.id.img133);
            img233 = view.findViewById(R.id.img233);
            img333 = view.findViewById(R.id.img333);
        }
    }
}
