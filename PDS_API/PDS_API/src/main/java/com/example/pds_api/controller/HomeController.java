package com.example.pds_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.pds_api.mapper.GoodsClassificationMapper;
import com.example.pds_api.mapper.GoodsMapper;
import com.example.pds_api.mapper.NoticeMapper;
import com.example.pds_api.model.Goods;
import com.example.pds_api.model.GoodsClassification;
import com.example.pds_api.model.Notice;
import com.example.pds_api.model.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
//用户端
public class HomeController {

    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private GoodsClassificationMapper goodsClassificationMapper;
    @Resource
    private GoodsMapper goodsMapper;


    //    获取通知栏信息
    @GetMapping("/getnotice")
    public Result getNotice(){
        Notice notice = noticeMapper.selectById(1);
        return Result.success("获取通知信息",notice);
    }

//    获取产品分类列表
    @GetMapping("/getgoodsclassificationlist")
    public Result getGoodsClassificationList(){
        List<GoodsClassification> goodsClassifications = goodsClassificationMapper.selectList(null);
        return Result.success("获取产品分类列表",goodsClassifications);
    }

//    获取产品列表
    @GetMapping("/getgoodslist")
    public Result getGoodsList(@RequestParam("classificationId") Integer classificationId){
        List<Goods> goodsList;
        if (classificationId == 0) {
//            goodsList = goodsMapper.selectList(null);
             goodsList = goodsMapper.selectGoodsWithInventory();
        }else{
//            QueryWrapper<Goods> wrapper = new QueryWrapper<>();
//            wrapper.eq("goods_classification_id",classificationId);
//            goodsList = goodsMapper.selectList(wrapper);
            goodsList = goodsMapper.selectGoodsWithInventoryByClassification(classificationId);
        }
        return Result.success("获取产品列表",goodsList);
    }

//    查询商品详情根据id
    @PostMapping("/getgoodsdetailbyid")
    public Result getGoodsDetailById(@RequestParam("goodsId") Integer goodsId){
//        Goods goods = goodsMapper.selectById(goodsId);
        Goods goods = goodsMapper.selectGoodsWithInventoryById(goodsId);
        return Result.success("获取商品详情",goods);
    }

//    搜索商品模糊搜索
    @PostMapping("/searchgoods")
    public Result searchGoods(@RequestParam("goodsName") String goodsName){
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_name",goodsName);
        List<Goods> goodsList = goodsMapper.selectList(wrapper);
        return Result.success("搜索商品",goodsList);
    }



}
