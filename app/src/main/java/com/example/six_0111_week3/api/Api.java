package com.example.six_0111_week3.api;

public class Api {

    public static final String BASE_URL="http://172.17.8.100";
    //首页商品信息列表
    public static String SHOPLIST=BASE_URL+"/small/commodity/v1/commodityList";
    public static String XBANNER=BASE_URL+"/small/commodity/v1/bannerShow";
    //登录接口
    public static String LOGIN=BASE_URL+"/small/user/v1/login";
    public static String Zhu=BASE_URL+"/small/user/v1/register";
    public static String HAHA= "http://api.expoon.com/AppNews/getNewsList/type/1/p?page=1";
    public static String XIANG=BASE_URL+"/small/commodity/v1/findCommodityDetailsById";


    //圈子
    public static String QUAN=BASE_URL+"/small/circle/v1/findCircleList";
    public static final String URL_GET_SHOP_CAR_INFO = "http://www.zhaoapi.cn/product/getCarts";





}
