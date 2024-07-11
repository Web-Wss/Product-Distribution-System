package com.example.pds_api.service;

import com.example.pds_api.model.Goods;
import com.example.pds_api.model.GoodsClassification;
import com.example.pds_api.model.Notice;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface HomeService {

//    获取通知栏信息
    Notice getNotice();

//    获取产品分类列表
    List<GoodsClassification> getGoodsClassificationList();

//    获取产品列表
    List<Goods> getGoodsList(@RequestParam("classificationId") Integer classificationId);

//    查询产品详情根据id
    Goods getGoodsDetailById(@RequestParam("goodsId") Integer goodsId);

//    模糊搜索商品
    List<Goods> searchGoods(@RequestParam("goodsName") String goodsName);

}
