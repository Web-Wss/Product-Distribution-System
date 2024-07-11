package com.example.pds_api.controller;

import com.example.pds_api.model.Goods;
import com.example.pds_api.model.GoodsClassification;
import com.example.pds_api.model.Notice;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.HomeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/home")
//用户端
public class HomeController {

    @Resource
    private HomeService homeService;


    //    获取通知栏信息
    @GetMapping("/getnotice")
    public Result getNotice(){
        Notice notice = homeService.getNotice();
        return Result.success("获取通知信息",notice);
    }

//    获取产品分类列表
    @GetMapping("/getgoodsclassificationlist")
    public Result getGoodsClassificationList(){
        List<GoodsClassification> goodsClassificationList = homeService.getGoodsClassificationList();
        return Result.success("获取产品分类列表",goodsClassificationList);
    }

//    获取产品列表
    @GetMapping("/getgoodslist")
    public Result getGoodsList(@RequestParam("classificationId") Integer classificationId){
        List<Goods> goodsList = homeService.getGoodsList(classificationId);
        return Result.success("获取产品列表",goodsList);
    }

//    查询商品详情根据id
    @PostMapping("/getgoodsdetailbyid")
    public Result getGoodsDetailById(@RequestParam("goodsId") Integer goodsId){
        Goods goodsDetailById = homeService.getGoodsDetailById(goodsId);
        return Result.success("获取商品详情",goodsDetailById);
    }

//    搜索商品模糊搜索
    @PostMapping("/searchgoods")
    public Result searchGoods(@RequestParam("goodsName") String goodsName){
        List<Goods> goodsList = homeService.searchGoods(goodsName);
        return Result.success("搜索商品",goodsList);
    }



}
