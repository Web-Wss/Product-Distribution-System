package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Goods;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface GoodsMapper extends BaseMapper<Goods> {

//    获取商品列表
    List<Goods> selectGoodsWithInventory();

//    获取商品列表根据分类id
    List<Goods> selectGoodsWithInventoryByClassification(@RequestParam("classificationId")Integer classificationId);


//    获取产品详情根据id
    Goods selectGoodsWithInventoryById(@RequestParam("goodsId")Integer goodsId);

//    模糊搜索商品
    List<Goods> searchGoods(@RequestParam("goodsName") String goodsName);




}
