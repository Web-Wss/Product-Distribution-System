package com.example.pds_api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.GoodsDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface AdministratorsService extends IService<Administrators> {

//    管理员登录
    HashMap<String, Object> login(String phone, String password);

//    获取经营看板数据
    HashMap<String, Object> getDashboardData();

//    获取商品列表信息
    List<Goods> getGoodsList();

//    根据商品id获取商品信息包含剩余库存
    Goods getGoodsInfoById(Integer goodsId);

//    修改商品信息
    Integer updateGoodsInfo(GoodsDTO goodsDTO);

//    获取用户列表
    List<User> getUserList();

//    获取分销商列表和分销商排名
    HashMap<String, Object> getDistributorList();

//    获取订单列表
    List<Orders> getOrderList();

//    获取订单列表根据用户id
    List<Orders> getOrderListByUserId(Integer userId);

//    修改订单进度根据订单id
    Integer updateOrderStatusByOrderId(Integer ordersId, Integer orderStatus);

//    修改订单已完成进度
    Integer updateOrderStatusComplete(Integer ordersId,  Integer pay);

//    获取系统信息
    HashMap<String, Object> getSystemInfo();

//    修改站点信息
    Integer updateSiteInfo(String path);

//    编辑通知信息
    Integer updateNoticeInfo(String noticeContent);

//    获取满减规则信息
    List<Reduction> getReductionInfo();

//    编辑满减信息
    Integer updateReductionInfo(Integer reductionId, BigDecimal fullConditionPrice, BigDecimal fullReductionAmount);

//    修改登录密码
    Integer updatePassword(String password);


}
