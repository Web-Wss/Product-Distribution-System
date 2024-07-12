package com.example.pds_api.controller;


import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.Orders;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.DistributorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/distributor")
public class DistributorController {

    @Resource
    private DistributorService distributorService;


//    分销商登录
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        HashMap<String, Object> login = distributorService.login(loginDTO);
        if (login == null) {
            return Result.fail("登录失败");
        }
        return Result.success("登录成功",login);
    }

//    获取数据看板数据
    @GetMapping("/getdashboarddata")
    public Result getDashboardData(@RequestParam("distributorId")Integer distributorId) {
        return Result.success(distributorService.getDashboardData(distributorId));
    }

//    获取订单列表根据分销商id
    @GetMapping("/getorderlistbydistributorid")
    public Result getOrderListByDistributorId(@RequestParam("distributorId")Integer distributorId) {
        return Result.success(distributorService.getOrderListByDistributorId(distributorId));
    }

//    获取营销推广信息
    @GetMapping("/getmarketingpromotioninfo")
    public Result getMarketingPromotionInfo(@RequestParam("distributorId") Integer distributorId) {
        HashMap<String, Object> marketingPromotionInfo = distributorService.getMarketingPromotionInfo(distributorId);
        return Result.success("获取成功",marketingPromotionInfo);
    }

//    获取佣金明细
    @GetMapping("/getcommissiondetail")
    public Result getCommissionDetail(@RequestParam("distributorId") Integer distributorId) {
        List<Orders> commissionDetail = distributorService.getCommissionDetail(distributorId);
        return Result.success("获取佣金明细",commissionDetail);
    }


//    修改密码
    @PostMapping("/updatepassword")
    public Result updatePassword(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO);
        Integer integer = distributorService.updatePassword(loginDTO);
        if (integer == 0) {
            return Result.fail("修改失败");
        }
        return  Result.success("修改成功",integer);
    }



}
