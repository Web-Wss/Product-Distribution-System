package com.example.pds_api.mapper;

import com.example.pds_api.model.Goods;

import java.util.List;

public interface HomeMapper {

    //    获取产品列表
    List<Goods> getGoodsList(Integer classificationId);

    //    查询商品详情根据id
    Goods getGoodsInfoById(Integer goodsId);

    //    搜索商品模糊搜索
    List<Goods> searchGoods(String goodsName);
}
