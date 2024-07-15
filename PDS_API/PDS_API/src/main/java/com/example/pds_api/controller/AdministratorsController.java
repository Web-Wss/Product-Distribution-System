package com.example.pds_api.controller;

import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.GoodsDTO;
import com.example.pds_api.service.AdministratorsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdministratorsController {

    @Resource
    private AdministratorsService administratorsService;



//    管理员登录
    @PostMapping("/login")
    public Result login(@RequestParam("phone")String phone,
                        @RequestParam("password")String password) {
        HashMap<String, Object> login = administratorsService.login(phone, password);
        return Result.success("管理员登录",login);
    }


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


//    获取订单列表根据用户id
    @GetMapping("/getorderlistbyuserid")
    public Result getOrderListByUserId(@RequestParam("userId")Integer userId) {
        List<Orders> orderListByUserId = administratorsService.getOrderListByUserId(userId);
        return Result.success("获取用户订单列表根据用户id",orderListByUserId);
    }

//    修改订单进度根据订单id
    @PostMapping("/updateorderstatusbyorderid")
    public Result updateOrderStatusByOrderId(@RequestParam("ordersId")Integer ordersId,
                                            @RequestParam("orderStatus")Integer orderStatus) {
        Integer integer = administratorsService.updateOrderStatusByOrderId(ordersId, orderStatus);
        return Result.success("修改成功",integer);
    }

//    修改订单已完成进度
    @PostMapping("/updateorderstatuscomplete")
    public Result updateOrderStatusComplete(@RequestParam("ordersId")Integer ordersId,
                                            @RequestParam("payType")Integer payType) {
        Integer integer = administratorsService.updateOrderStatusComplete(ordersId, payType);
        if (integer == 1) {
            return Result.success("修改成功",integer);
        }
        return Result.success("修改失败",integer);
    }


//    获取系统相关信息
    @GetMapping("/getsysteminfo")
    public Result getSystemInfo() {
        HashMap<String, Object> systemInfo = administratorsService.getSystemInfo();
        return Result.success("系统相关信息",systemInfo);
    }

//    修改站点信息
    @PostMapping("/updatesiteinfo")
    public Result updateSiteInfo(@RequestParam("path")String path) {
        Integer integer = administratorsService.updateSiteInfo(path);
        return Result.success("修改成功",integer);
    }


//    编辑通知信息
    @PostMapping("/updatenoticeinfo")
    public Result updateNoticeInfo(@RequestParam("noticeContent")String noticeContent) {
        Integer integer = administratorsService.updateNoticeInfo(noticeContent);
        return Result.success("修改成功",integer);
    }

//    获取满减规则信息
    @GetMapping("/getreductioninfo")
    public Result getReductionInfo() {
        List<Reduction> reductionInfo = administratorsService.getReductionInfo();
        return Result.success("满减规则信息",reductionInfo);
    }

//    编辑满减信息
    @PostMapping("/updatereductioninfo")
    public Result updateReductionInfo(@RequestParam("reductionId")Integer reductionId,
                                     @RequestParam("fullConditionPrice") BigDecimal fullConditionPrice,
                                     @RequestParam("fullReductionAmount")BigDecimal fullReductionAmount) {
        Integer integer = administratorsService.updateReductionInfo(reductionId, fullConditionPrice, fullReductionAmount);
        return Result.success("修改成功",integer);
    }


//    修改登录密码
    @PostMapping("/updatepassword")
    public Result updatePassword(@RequestParam("password")String password) {
        Integer integer = administratorsService.updatePassword(password);
        return Result.success("修改成功",integer);
    }



}
