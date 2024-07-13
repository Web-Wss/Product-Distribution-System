package com.example.pds_api.controller;

import com.example.pds_api.model.DTO.GoodsDTO;
import com.example.pds_api.model.Goods;
import com.example.pds_api.model.Orders;
import com.example.pds_api.model.Result;
import com.example.pds_api.model.User;
import com.example.pds_api.service.AdministratorsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdministratorsController {

    @Resource
    private AdministratorsService administratorsService;


//    获取经营看板数据
    @GetMapping("/getdashboarddata")
    public Result getDashboardData() {
        HashMap<String, Object> dashboardData = administratorsService.getDashboardData();
            return Result.success("经营看板数据",dashboardData);
    }

//    获取商品列表信息
    @GetMapping("/getgoodslist")
    public Result getGoodsList() {
        List<Goods> goodsList = administratorsService.getGoodsList();
        return Result.success("商品列表信息",goodsList);
    }

//    根据商品id获取商品信息包括剩余库存
    @GetMapping("/getgoodsinfobyid")
    public Result getGoodsInfoById(@RequestParam("goodsId")Integer goodsId) {
        Goods goodsInfoById = administratorsService.getGoodsInfoById(goodsId);
        return Result.success("商品信息",goodsInfoById);
    }

//    修改商品信息
    @PostMapping("/updategoodsinfo")
    public Result updateGoodsInfo(@RequestBody GoodsDTO goodsDTO) {
        Integer integer = administratorsService.updateGoodsInfo(goodsDTO);
        if (integer == 1) {
            return Result.success("修改成功",integer);
        }
        return Result.fail("修改失败",integer);
    }

//    获取用户列表
    @GetMapping("/getuserlist")
    public Result getUserList() {
        List<User> userList = administratorsService.getUserList();
        return Result.success("用户列表",userList);
    }


//    获取分销商列表和分销商排名
    @GetMapping("/getdistributorlist")
    public Result getDistributorList() {
        HashMap<String, Object> distributorList = administratorsService.getDistributorList();
        return Result.success("分销商列表",distributorList);
    }

//    获取订单列表
    @GetMapping("/getorderlist")
    public Result getOrderList() {
        List<Orders> orderList = administratorsService.getOrderList();
        return Result.success("订单列表",orderList);
    }



}
