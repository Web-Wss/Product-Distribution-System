package com.example.pds_api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.Distributor;
import com.example.pds_api.model.Orders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface DistributorService extends IService<Distributor> {

//    分销商登录
    HashMap<String, Object> login(@RequestBody LoginDTO loginDTO);

//    获取数据看板数据
    HashMap<String, Object> getDashboardData(@RequestParam("distributorId") Integer distributorId);

//    获取订单列表根据分销商id
    List<Orders> getOrderListByDistributorId(@RequestParam("distributorId") Integer distributorId);

//    获取营销推广信息
    HashMap<String, Object> getMarketingPromotionInfo(@RequestParam("distributorId") Integer distributorId);

//    获取佣金明细
    List<Orders> getCommissionDetail(@RequestParam("distributorId") Integer distributorId);

//    修改密码
    Integer updatePassword(@RequestBody LoginDTO loginDTO);


}
