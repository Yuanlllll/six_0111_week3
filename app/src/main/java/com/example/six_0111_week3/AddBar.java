package com.example.six_0111_week3;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.six_0111_week3.adapter.CarAdapter;
import com.example.six_0111_week3.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class AddBar extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private CarAdapter cadapter;
    private List<ShopBean.DataBean.ListBean> list=new ArrayList<>();
    private int position;
    private EditText edit_shop_car;
    private ImageView add_car;
    private ImageView jian_car;



    public AddBar(Context context) {
        super(context);
        init(context);
    }

    public AddBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public AddBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        this.context = context;
        View view = View.inflate(context, R.layout.add_layout, null);
        add_car = view.findViewById(R.id.add_car);
        jian_car = view.findViewById(R.id.jian_car);
        edit_shop_car = view.findViewById(R.id.edit_shop_car);
        //加加减减的点击事件
        add_car.setOnClickListener(this);
        jian_car.setOnClickListener(this);
        addView(view);
        edit_shop_car.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num=Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setData(CarAdapter cadapter, List<ShopBean.DataBean.ListBean> list,int i)
    {
        this.list=list;
        this.cadapter=cadapter;
        position = i;
        String num = list.get(i).getNum();
        edit_shop_car.setText(num+"");


    }


    //点击事件
    private int num;
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.add_car:
                num++;
                edit_shop_car.setText(num+"");
                //list.get(position).setNum(String.valueOf(num));
                carCallBack.callBack();
                //cadapter.notifyDataSetChanged();
                break;
            case R.id.jian_car:
                if (num>1)
                {
                    num--;
                }
                 else {
                    Toast.makeText(context,"111",Toast.LENGTH_SHORT).show();
                }
                edit_shop_car.setText(num+"");
                //list.get(position).setNum(String.valueOf(num));
                carCallBack.callBack();
                //cadapter.notifyDataSetChanged();
                break;
        }

    }

    private CarCallBack carCallBack;
    public void setOnCallBack(CarCallBack carCallBack) {
        this.carCallBack = carCallBack;
    }
    //定义接口
    public  interface CarCallBack
    {
        void callBack();
    }
}
