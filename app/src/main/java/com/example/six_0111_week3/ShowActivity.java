package com.example.six_0111_week3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.six_0111_week3.fragment.ErFragment;
import com.example.six_0111_week3.fragment.SanFragment;
import com.example.six_0111_week3.fragment.ShowFragment;
import com.example.six_0111_week3.fragment.WoFragment;
import com.example.six_0111_week3.fragment.siFragment;

import com.hjm.bottomtabbar.BottomTabBar;

public class ShowActivity extends AppCompatActivity {

    private BottomTabBar fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        fragment = findViewById(R.id.fragment);
        fragment.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(8)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页", R.drawable.fangs, ShowFragment.class)
                .addTabItem("分类", R.drawable.ufos, ErFragment.class)
                .addTabItem("发现", R.drawable.shop, SanFragment.class)
                .addTabItem("购物车", R.drawable.btn_list_n_hdpi, siFragment.class)
                .addTabItem("我的", R.drawable.my, WoFragment.class)
                .isShowDivider(false);
    }
}
