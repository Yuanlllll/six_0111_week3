package com.example.six_0111_week3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.six_0111_week3.bean.XiangBean;
import com.example.six_0111_week3.xiang.presenter.XiangPresenter;
import com.example.six_0111_week3.xiang.view.Ixview;

public class XiangqingActivity extends AppCompatActivity implements Ixview {

    private WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        web_view = findViewById(R.id.web_view);
        String sid = getIntent().getStringExtra("id");
        XiangPresenter xiangPresenter = new XiangPresenter(this);
        xiangPresenter.getxpdata(Integer.parseInt(sid));
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });


    }

    @Override
    public void getxvdata(Object vdata)
    {
        XiangBean xiangBean= (XiangBean) vdata;
        Log.i( "getxvdata: ",vdata.toString());
        String details = xiangBean.getResult().getDetails();
        web_view.loadDataWithBaseURL(null,details,"text / html","UTF-8",null);

    }
}
