package com.example.pds_api.service;

import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.Orders;

import java.util.HashMap;
import java.util.List;

public interface DistributorService {


    //    获取数据看板数据
    Object getDashboardData(Integer distributorId);

    //    获取订单列表根据分销商id
    Object getOrderListByDistributorId(Integer distributorId);

    //    获取营销推广信息
    HashMap<String, Object> getMarketingPromotionInfo(Integer distributorId);

    //    获取佣金明细
    List<Orders> getCommissionDetail(Integer distributorId);

    //    修改密码
    Integer updatePassword(LoginDTO loginDTO);
}
